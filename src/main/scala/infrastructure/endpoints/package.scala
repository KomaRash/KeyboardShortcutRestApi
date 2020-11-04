package infrastructure

import domain.keyboards.{Action, KeyboardShortcut, KeyboardShortcutRequest, ShortcutResponse}
import io.circe.{Codec, Decoder, Encoder}
import io.circe.generic.semiauto._

package object endpoints {
   lazy implicit val shortcutRequestCodec:Decoder[KeyboardShortcutRequest]=deriveDecoder[KeyboardShortcutRequest]
  lazy implicit val actionCodec:Codec[Action]=deriveCodec[Action]
  lazy implicit val shortcutKeyboardCodec:Codec[KeyboardShortcut]=deriveCodec[KeyboardShortcut]
  lazy implicit val shortcutResponseCodec:Codec[ShortcutResponse]=deriveCodec[ShortcutResponse]
}
