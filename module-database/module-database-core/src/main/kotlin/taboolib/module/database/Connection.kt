package taboolib.module.database

import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.Configuration
import java.io.File
import java.sql.Connection
import java.sql.ResultSet

fun <T> ResultSet.use(func: ResultSet.() -> T): T {
    return try {
        func(this)
    } catch (ex: Exception) {
        throw ex
    } finally {
        close()
    }
}

fun <T> Connection.use(func: Connection.() -> T): T {
    return try {
        func(this)
    } catch (ex: Exception) {
        throw ex
    } finally {
        close()
    }
}