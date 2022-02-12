package taboolib.module.lang

import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer

fun ProxyCommandSender.sendLang(node: String, vararg args: Any) {
    val file = getLocaleFile()
    if (file == null) {
        sendMessage("{$node}")
    } else {
        val type = file.nodes[node]
        if (type != null) {
            type.send(this, *args)
        } else {
            sendMessage("{$node}")
        }
    }
}

fun ProxyCommandSender.asLangText(node: String, vararg args: Any): String {
    return asLangTextOrNull(node, *args) ?: "{$node}"
}

fun ProxyCommandSender.asLangTextOrNull(node: String, vararg args: Any): String? {
    val file = getLocaleFile()
    return if (file != null) (file.nodes[node] as? TypeText)?.asText(this, *args) else null
}

fun ProxyCommandSender.asLangTextList(node: String, vararg args: Any): List<String> {
    val file = getLocaleFile() ?: return listOf("{$node}")
    return when (val type = file.nodes[node]) {
        is TypeText -> {
            val text = type.asText(this, *args)
            if (text != null) listOf(text) else emptyList()
        }
        is TypeList -> {
            type.asTextList(this, *args)
        }
        else -> {
            listOf("{$node}")
        }
    }
}

fun ProxyCommandSender.getLocale(): String {
    return if (this is ProxyPlayer) Language.getLocale(this) else Language.getLocale()
}

fun ProxyCommandSender.getLocaleFile(): LanguageFile? {
    val locale = getLocale()
    val file = Language.getLanguageFile()
    return file.entries.firstOrNull { it.key.equals(locale, true) }?.value ?: file[Language.defaultLanguageCode] ?: file.values.firstOrNull()
}