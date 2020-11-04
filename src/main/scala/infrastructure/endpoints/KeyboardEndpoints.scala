package infrastructure.endpoints

import cats.Monad
import cats.effect.ConcurrentEffect
import cats.implicits._
import domain.keyboards.{KeyboardShortcut, KeyboardShortcutRequest, ShortcutResponse}
import infrastructure.keyboards.KeyboardService
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.{EntityDecoder, HttpRoutes}
class KeyboardEndpoints[F[_]:ConcurrentEffect:Monad](service:KeyboardService[F]) extends Http4sDsl[F]{

  implicit val shortcutResponse:EntityDecoder[F,ShortcutResponse]=jsonOf[F,ShortcutResponse]
  implicit val keyboardResponse:EntityDecoder[F,List[KeyboardShortcut]]=jsonOf[F,List[KeyboardShortcut]]
  implicit val requestDecoder: EntityDecoder[F, KeyboardShortcutRequest] = jsonOf[F,KeyboardShortcutRequest]
  private def addShortcuts:HttpRoutes[F]=HttpRoutes.of[F] {
    case request@POST -> Root / "add" => {
      (for {
        shortcuts <- request.as[KeyboardShortcutRequest]
        result <- service.add(shortcuts)
      } yield result).flatMap {
        case Left(_) => Ok(ShortcutResponse(false))
        case Right(_) => Ok(ShortcutResponse(true))
      }
    }
  }
  private def getCategory:HttpRoutes[F]=HttpRoutes.of[F]{
      case GET->Root/"category"/category_name=>
        service.getByCategory(category_name).flatMap(Ok(_))
    }
  def endpoints=getCategory<+>addShortcuts
}
object KeyboardEndpoints{
  def endpoints[F[_]:ConcurrentEffect:Monad](service: KeyboardService[F]) ={
    new KeyboardEndpoints[F](service).endpoints
  }
}
