package taboolib.module.configuration

import com.electronwill.nightconfig.core.Config
import com.electronwill.nightconfig.core.ConfigFormat
import taboolib.common.reflect.Reflex.Companion.invokeMethod
import taboolib.internal.YamlFormat

/**
 * TabooLib
 * taboolib.module.configuration.Type
 *
 * @author mac
 * @since 2021/11/21 10:52 下午
 */
enum class Type(private val format: () -> ConfigFormat<out Config>?) {

    YAML({ YamlFormat.INSTANCE }),

    TOML({ tomlFormat }),

    JSON({ jsonFormat }),

    FAST_JSON({ fastJsonFormat }),

    HOCON({ hoconFormat });

    internal fun newFormat(): ConfigFormat<out Config> {
        return format() ?: error("$name not supported")
    }

    companion object {

        const val groupId = "com.electronwill.nightconfig"

        val tomlFormat = kotlin.runCatching {
            Class.forName("$groupId.hocon.HoconFormat").invokeMethod<ConfigFormat<*>>("instance", isStatic = true)
        }.getOrNull()

        val jsonFormat = kotlin.runCatching {
            Class.forName("$groupId.json.JsonFormat").invokeMethod<ConfigFormat<*>>("emptyTolerantInstance", isStatic = true)
        }.getOrNull()

        val fastJsonFormat = kotlin.runCatching {
            Class.forName("$groupId.json.JsonFormat").invokeMethod<ConfigFormat<*>>("minimalEmptyTolerantInstance", isStatic = true)
        }.getOrNull()

        val hoconFormat = kotlin.runCatching {
            Class.forName("$groupId.hocon.HoconFormat").invokeMethod<ConfigFormat<*>>("instance", isStatic = true)
        }.getOrNull()

        fun getType(format: ConfigFormat<*>): Type {
            return values().first { it.newFormat().javaClass == format.javaClass }
        }
    }
}