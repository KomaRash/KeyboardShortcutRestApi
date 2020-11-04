package infrastructure.repository

import cats.Monad
import cats.effect.concurrent.Ref
import domain.keyboards.{KeyboardRepAlgebra, KeyboardShortcut, Shortcut}
import cats.implicits._
import cats.syntax._
class KeyboardRepository[F[_]:Monad](keyboards: Ref[F, Map[String, List[KeyboardShortcut]]]) extends KeyboardRepAlgebra[F]{

  override def addKeyboards(keyboardShortcut: KeyboardShortcut):F[KeyboardShortcut]= {
    val key=keyboardShortcut.action.category
    keyboards.modify(map=>
      (map+(key->(keyboardShortcut::map.getOrElse(key,Nil))),keyboardShortcut))
  }
  override def getByCategory(category: String): F[List[KeyboardShortcut]] = {
    keyboards.get.map(_.getOrElse(category,Nil))
  }

  override def findByKeyboards(s: Seq[Shortcut]): F[Option[KeyboardShortcut]] = {
    keyboards.get.flatMap(map=> map.
      find {
        keyValue=>keyValue.
                  exists { list=>list.
                    exists(_.binding.equals(s))
                  }
      }.flatMap(_._2.find(_.binding.equals(s))).pure[F])
    }
}
