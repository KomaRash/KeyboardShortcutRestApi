package infrastructure

import domain.keyboards.{Action, KeyboardShortcut, KeyboardShortcutRequest, ShortcutResponse}
import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._

package object endpoints {
  lazy implicit val shortcutRequestDecoder:Decoder[KeyboardShortcutRequest]=deriveDecoder[KeyboardShortcutRequest]
  lazy implicit val actionEncoder:Encoder[Action]=deriveEncoder[Action]
  lazy implicit val shortcutKeyboardEncoder:Encoder[KeyboardShortcut]=deriveEncoder[KeyboardShortcut]
  lazy implicit val shortcutResponseEncoder:Encoder[ShortcutResponse]=deriveEncoder[ShortcutResponse]
}
