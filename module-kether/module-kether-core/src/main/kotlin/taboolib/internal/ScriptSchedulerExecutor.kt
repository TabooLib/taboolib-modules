package taboolib.internal

import taboolib.common.platform.function.isPrimaryThread
import taboolib.common.platform.function.submit
import java.util.concurrent.Executor

@Internal
object ScriptSchedulerExecutor : Executor {

    override fun execute(command: Runnable) {
        if (isPrimaryThread) {
            command.run()
        } else {
            submit { command.run() }
        }
    }
}