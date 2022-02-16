package taboolib.module.database

import com.zaxxer.hikari.HikariDataSource
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import java.sql.Connection
import java.util.concurrent.CopyOnWriteArrayList

abstract class DataSourceContainer : DatabaseURL() {

    private val dataSource by lazy {
        Database.INSTANCE.createDataSource(this).apply { dataSources += this as HikariDataSource }
    }

    fun dataSource() = dataSource

    fun <T> useConnection(block: Connection.() -> T) = dataSource.connection.use(block)

    companion object {

        private val dataSources = CopyOnWriteArrayList<HikariDataSource>()

        @Awake(LifeCycle.DISABLE)
        fun closeAll() {
            dataSources.forEach { it.close() }
        }
    }
}