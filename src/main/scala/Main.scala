import cats.effect._
import cats.effect.concurrent.Ref
import cats.effect.{Blocker, ConcurrentEffect, ContextShift, ExitCode, IO, IOApp, Resource, Timer}
import cats.implicits._
import domain.keyboards.KeyboardShortcut
import infrastructure.endpoints.KeyboardEndpoints
import infrastructure.keyboards.KeyboardService
import infrastructure.repository.KeyboardRepository
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.{Server => H4Server}

object Main extends IOApp{
  override def run(args: List[String]): IO[ExitCode] = {
    stream[IO].use(_ => IO.never).as(ExitCode.Success)
  }
  def stream[F[_]: ConcurrentEffect: ContextShift: Timer]:Resource[F, H4Server[F]]=for{
    rep<-Resource.liftF(Ref.of[F,Map[String,List[KeyboardShortcut]]](Map.empty))
    repository= new KeyboardRepository[F](rep)
    service=KeyboardService.service(repository)
    endpoints=KeyboardEndpoints.endpoints(service)
    server <- BlazeServerBuilder[F]
      .bindHttp(8080,"127.0.0.1")
      .withHttpApp(endpoints.orNotFound)
      .resource
  }yield server
}
