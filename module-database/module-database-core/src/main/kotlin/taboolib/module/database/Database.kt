package taboolib.module.database

import com.zaxxer.hikari.HikariConfig
import taboolib.common.boot.SimpleServiceLoader
import taboolib.common.env.RuntimeDependency
import javax.sql.DataSource

@RuntimeDependency(value = "!com.zaxxer:HikariCP:4.0.3", test = "!hikari501.HikariDataSource", relocate = ["!com.zaxxer.hikari", "!hikari403"])
abstract class Database {

    abstract fun createConfig(host: DataSourceContainer): HikariConfig

    abstract fun createDataSource(host: DataSourceContainer, config: HikariConfig? = null): DataSource

    companion object {

        @JvmField
        val SQL = Host.SQL

        @JvmField
        val SQLITE = Host.SQLite

        @JvmField
        val INSTANCE: Database = SimpleServiceLoader.load(Database::class.java)
    }
}