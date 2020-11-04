package domain.keyboards

trait KeyboardValidationAlgebra[F[_]] {
  def exist(keyboards:Seq[Shortcut]):F[Either[ParseError,Unit]]
}
