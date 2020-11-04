package infrastructure.keyboards

import cats.Monad
import cats.data.EitherT
import domain.keyboards.{Action, KeyboardRepAlgebra, ParseAlgebra, ParseError, Shortcut}
import cats.syntax.either._
class KeyboardParseInterpreter[F[_]] extends ParseAlgebra[F]{
  override def parseToShortcuts(shortcuts: String)(implicit F:Monad[F]): F[Either[ParseError, Seq[Shortcut]]] = {
//проверки
    F.pure( shortcuts.split("\\s?\\+").toSeq.asRight)
  }

  override def parseToAction(action: String)(implicit F:Monad[F]): F[Either[ParseError, Action]] = {
    action.split(".").toList  match {
      case category::action::Nil=>F.pure(Action(category,action).asRight)
      case _=>F.pure(ParseError().asLeft)
    }

  }
}
