package taboolib.module.kether.action.transform

import taboolib.common5.Coerce
import taboolib.library.kether.ArgTypes
import taboolib.library.kether.ParsedAction
import taboolib.module.kether.KetherParser
import taboolib.module.kether.ScriptAction
import taboolib.module.kether.ScriptFrame
import taboolib.module.kether.scriptParser
import java.util.concurrent.CompletableFuture
import kotlin.math.roundToInt
import kotlin.math.roundToLong

/**
 * @author IzzelAliz
 */
class ActionRound(val number: ParsedAction<*>) : ScriptAction<Long>() {

    override fun run(frame: ScriptFrame): CompletableFuture<Long> {
        return frame.newFrame(number).run<Any>().thenApply { Coerce.toDouble(it).roundToLong() }
    }

    internal object Parser {

        /**
         * round *1.0
         */
        @KetherParser(["round"])
        fun parser() = scriptParser {
            ActionRound(it.nextParsedAction())
        }
    }
}