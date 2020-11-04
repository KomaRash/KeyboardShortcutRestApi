package infrastructure.keyboards


import cats.Monad
import cats.implicits._
import domain.keyboards.{KeyboardRepAlgebra, KeyboardValidationAlgebra, ParseError, Shortcut}

class KeyboardValidation[F[_]:Monad](repository:KeyboardRepAlgebra[F])  extends KeyboardValidationAlgebra[F]{
  override def exist(keyboards: Seq[Shortcut]): F[Either[ParseError, Unit]] = {
    (repository.findByKeyboards(keyboards) ).map {
      case Some(value) => ParseError().asLeft
      case None =>().asRight
    }
  }
}
