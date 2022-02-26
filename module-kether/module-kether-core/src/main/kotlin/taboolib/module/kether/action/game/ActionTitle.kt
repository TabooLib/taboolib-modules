package taboolib.module.kether.action.game

import taboolib.common.platform.ProxyPlayer
import taboolib.common5.Coerce
import taboolib.library.kether.ParsedAction
import taboolib.library.kether.QuestContext
import taboolib.library.kether.actions.LiteralAction
import taboolib.module.kether.*
import java.util.concurrent.CompletableFuture

/**
 * @author IzzelAliz
 */
class ActionTitle(
    val title: ParsedAction<*>,
    val subTitle: ParsedAction<*>,
    val fadeIn: ParsedAction<*>,
    val stay: ParsedAction<*>,
    val fadeOut: ParsedAction<*>,
) : ScriptAction<Void>() {

    override fun run(frame: QuestContext.Frame): CompletableFuture<Void> {
        return frame.newFrame(title).run<Any>().thenAccept { t ->
            frame.newFrame(subTitle).run<Any>().thenAccept { s ->
                frame.newFrame(fadeIn).run<Any>().thenAccept { fadeIn ->
                    frame.newFrame(stay).run<Any>().thenAccept { stay ->
                        frame.newFrame(fadeOut).run<Any>().thenAccept { fadeOut ->
                            val viewer = frame.script().sender as? ProxyPlayer ?: error("No player selected.")
                            val title = t.toString().trimIndent().replace("@sender", viewer.name)
                            val subTitle = s.toString().trimIndent().replace("@sender", viewer.name)
                            viewer.sendTitle(title, subTitle, Coerce.toInteger(fadeIn), Coerce.toInteger(stay), Coerce.toInteger(fadeOut))
                        }
                    }
                }
            }
        }
    }

    internal object Parser {

        @KetherParser(["title"])
        fun parser() = scriptParser {
            val title = it.nextParsedAction()
            it.mark()
            val subTitle = try {
                it.expect("subtitle")
                it.nextParsedAction()
            } catch (ignored: Exception) {
                it.reset()
                ParsedAction(LiteralAction<String>(""))
            }
            var fadeIn = literalAction(0)
            var stay = literalAction(20)
            var fadeOut = literalAction(0)
            it.mark()
            try {
                it.expects("by", "with")
                fadeIn = it.nextParsedAction()
                stay = it.nextParsedAction()
                fadeOut = it.nextParsedAction()
            } catch (ignored: Exception) {
                it.reset()
            }
            ActionTitle(title, subTitle, fadeIn, stay, fadeOut)
        }
    }
}