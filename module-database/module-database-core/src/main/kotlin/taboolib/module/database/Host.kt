package taboolib.module.database

import taboolib.common.io.newFile
import taboolib.common.reflect.Reflex.Companion.unsafeInstance
import taboolib.library.configuration.ConfigurationSection
import java.io.File

/**
 * TabooLib
 * taboolib.module.database.Host
 *
 * @author 坏黑
 * @since 2022/2/16 3:35 PM
 */
@Suppress("UNCHECKED_CAST")
abstract class Host<T>(private val clazz: Class<T>) {

    operator fun invoke(func: T.() -> Unit): T {
        return (clazz.unsafeInstance() as T).apply(func)
    }

    object SQL : Host<Platform.SQL>(Platform.SQL::class.java) {

        operator fun invoke(host: String, port: Int, user: String, password: String, database: String, func: Platform.SQL.() -> Unit = {}): Platform.SQL =
            Platform.SQL(host, port, user, password, database).apply(func)

        operator fun invoke(section: ConfigurationSection, func: Platform.SQL.() -> Unit = {}): Platform.SQL {
            val host = section.getString("host", "localhost")!!
            val user = section.getString("user", "root")!!
            val password = section.getString("password", "root")!!
            val database = section.getString("database", "test")!!
            return invoke(host, section.getInt("port"), user, password, database).apply(func)
        }
    }

    object SQLite : Host<Platform.SQLite>(Platform.SQLite::class.java) {

        operator fun invoke(file: File, name: String) = Platform.SQLite(newFile(file, name))

        operator fun invoke(name: String) = Platform.SQLite(newFile(name))

        operator fun invoke(file: File) = Platform.SQLite(file)
    }
}