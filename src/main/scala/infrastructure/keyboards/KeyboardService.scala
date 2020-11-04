package infrastructure.keyboards

import cats.Monad
import cats.data.EitherT
import cats.implicits._
import domain.keyboards._
class KeyboardService[F[_]:Monad](repository: KeyboardRepAlgebra[F],
                                  parse:ParseAlgebra[F],
                                  validation: KeyboardValidationAlgebra[F]) {
  def add(keyboardShortcutRequest: KeyboardShortcutRequest): F[Either[ParseError, KeyboardShortcut]] ={
    EitherT(for {

     binding <- parse.parseToShortcuts(keyboardShortcutRequest.binding)
     action <- parse.parseToAction(keyboardShortcutRequest.action)
    }yield (binding,keyboardShortcutRequest.description.asRight,action).
      mapN(KeyboardShortcut)).flatMap{
      key=>

        for{
        _<-EitherT(validation.exist(key.binding))
        result<-EitherT.liftF[F,ParseError,KeyboardShortcut](repository.addKeyboards(key))
      }yield result

    }.value

    }
  def getByCategory(category:String): F[List[KeyboardShortcut]] ={
    repository.getByCategory(category)
  }
}
object KeyboardService{
  def service[F[_]:Monad](repository: KeyboardRepAlgebra[F]): KeyboardService[F] ={
    new KeyboardService[F](repository,new KeyboardParseInterpreter[F],new KeyboardValidation(repository))
  }
}
