package domain.keyboards


case class KeyboardShortcut(binding:Seq[Shortcut],description:String,action:Action)
case class KeyboardShortcutRequest(binding:String,description: String,action: String)
case class Action(category:String,action:String)