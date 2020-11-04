package domain.keyboards

import cats.Monad

trait ParseAlgebra[F[_]] {
    def parseToShortcuts(shortcuts: String)(implicit F:Monad[F]): F[Either[ParseError, Seq[Shortcut]]]
    def parseToAction(a: String)(implicit F: Monad[F]): F[Either[ParseError, Action]]
}
