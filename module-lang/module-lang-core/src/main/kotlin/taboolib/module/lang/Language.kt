package taboolib.module.lang

import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.SkipTo
import taboolib.common.platform.function.getJarFile
import taboolib.internal.LanguageReader
import taboolib.module.chat.ColorTranslator
import taboolib.module.chat.colored
import taboolib.module.lang.event.PlayerSelectLocaleEvent
import taboolib.module.lang.event.SystemSelectLocaleEvent
import java.util.*
import java.util.jar.JarFile
import kotlin.collections.HashMap

/**
 * TabooLib
 * taboolib.module.lang.Language
 *
 * @author sky
 * @since 2021/6/18 10:43 下午
 */
@SkipTo(LifeCycle.INIT)
object Language {

    private var firstLoaded = false
    private val registeredTextTransfer = ArrayList<TextTransfer>()
    private val registeredLanguageFile = HashMap<String, LanguageFile>()
    private val registeredLanguageCode = HashSet<String>()
    private val registeredLanguageType = HashMap<String, Class<out Type>>()
    private val languageCodeAliases = hashMapOf(
        "zh_hans_cn" to "zh_CN",
        "zh_hant_cn" to "zh_TW",
        "en_ca" to "en_US",
        "en_au" to "en_US",
        "en_gb" to "en_US",
        "en_nz" to "en_US"
    )
    var defaultLanguageCode = "zh_CN"

    init {
        langDefaultLanguageCode()
        addColorLanguageTransfer()
        addLanguageType("text", TypeText::class.java)
    }

    @Awake(LifeCycle.INIT)
    fun reload() {
        firstLoaded = true
        registeredLanguageFile.clear()
        registeredLanguageFile.putAll(LanguageReader(Language::class.java).files)
    }

    fun addLanguageCode(vararg code: String) {
        registeredLanguageCode += code
        if (firstLoaded) {
            reload()
        }
    }

    fun addLanguageType(type: String, clazz: Class<out Type>) {
        registeredLanguageType[type] = clazz
    }

    fun addLanguageAlias(code: String, alias: String) {
        languageCodeAliases[code] = alias
    }

    fun addLanguageTransfer(transfer: TextTransfer) {
        registeredTextTransfer += transfer
    }

    fun getLanguageFile(): Map<String, LanguageFile> {
        return registeredLanguageFile
    }

    fun getLanguageCode(): Set<String> {
        return registeredLanguageCode
    }

    fun getLanguageType(): Map<String, Class<out Type>> {
        return registeredLanguageType
    }

    fun getLanguageTransfer(): List<TextTransfer> {
        return registeredTextTransfer
    }

    fun getLocale(player: ProxyPlayer): String {
        return PlayerSelectLocaleEvent(player, languageCodeAliases[player.locale.lowercase()] ?: player.locale).apply { call() }.locale
    }

    fun getLocale(): String {
        val code = Locale.getDefault().toLanguageTag().replace("-", "_").lowercase()
        return SystemSelectLocaleEvent(languageCodeAliases[code] ?: code).apply { call() }.locale
    }

    private fun langDefaultLanguageCode() {
        JarFile(getJarFile()).use { jar ->
            jar.entries().iterator().forEach {
                if (it.name.startsWith("lang/") && it.name.endsWith(".yml")) {
                    addLanguageCode(it.name.substringAfter('/').substringBeforeLast('.'))
                }
            }
        }
    }

    private fun addColorLanguageTransfer() {
        try {
            ColorTranslator.translate("")
            addLanguageTransfer(object : TextTransfer {
                override fun translate(sender: ProxyCommandSender, source: String): String {
                    return source.colored()
                }
            })
        } catch (_: NoClassDefFoundError) {
        }
    }
}