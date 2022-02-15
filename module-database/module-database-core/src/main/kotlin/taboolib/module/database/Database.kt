package taboolib.module.database

import com.zaxxer.hikari.HikariConfig
import taboolib.common.boot.SimpleServiceLoader
import taboolib.common.env.RuntimeDependency
import javax.sql.DataSource

@RuntimeDependency(value = "!com.zaxxer:HikariCP:4.0.3", test = "!hikari501.HikariDataSource", relocate = ["!com.zaxxer.hikari", "!hikari403"])
abstract class Database {

    abstract fun createDataSource(host: Host, hikariConfig: HikariConfig? = null): DataSource

    abstract fun createHikariConfig(host: Host): HikariConfig

    companion object {

        @JvmField
        val INSTANCE: Database = SimpleServiceLoader.load(Database::class.java)

        fun createDataSource(host: Host, hikariConfig: HikariConfig? = null) = INSTANCE.createDataSource(host, hikariConfig)
    }
}