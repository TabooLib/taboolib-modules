package taboolib.module.database

import java.io.File

/**
 * TabooLib
 * taboolib.module.database.Platform
 *
 * @author 坏黑
 * @since 2022/2/16 6:55 PM
 */
interface Platform {

    data class SQL(
        val host: String = "localhost",
        val port: Int = 3306,
        val user: String = "root",
        val password: String = "root",
        val database: String = "test",
    ) : DataSourceContainer(), Platform {

        val flags = arrayListOf("characterEncoding=utf-8", "useSSL=false")

        fun addFlag(key: String, value: String) {
            flags += "$key=$value"
        }

        fun removeFlag(key: String) {
            flags.removeIf { it.startsWith("$key=", ignoreCase = true) }
        }

        override fun url() = "jdbc:mysql://$host:$port/$database${if (flags.isEmpty()) "" else "?${flags.joinToString("&")}"}"
    }

    data class SQLite(val file: File? = null) : DataSourceContainer(), Platform {

        override fun url() = "jdbc:sqlite:" + (file?.path ?: error("no file"))
    }
}