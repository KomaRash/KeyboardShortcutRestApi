package domain.keyboards

import java.util.Locale.Category

trait KeyboardRepAlgebra[F[_]] {
  def findByKeyboards(keyboards: Seq[Shortcut]): F[Option[KeyboardShortcut]]
  def addKeyboards(keyboardShortcut: KeyboardShortcut):F[KeyboardShortcut]
  def getByCategory(category: String):F[List[KeyboardShortcut]]
}
