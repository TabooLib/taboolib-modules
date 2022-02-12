package taboolib.internal

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.lang.*

@Internal
object TypeRegistry {

    @Awake(LifeCycle.INIT)
    fun init() {
        Language.addLanguageType("json", TypeJson::class.java)
        Language.addLanguageType("title", TypeTitle::class.java)
        Language.addLanguageType("sound", TypeSound::class.java)
        Language.addLanguageType("command", TypeCommand::class.java)
        Language.addLanguageType("action", TypeActionBar::class.java)
        Language.addLanguageType("actionbar", TypeActionBar::class.java)
    }
}