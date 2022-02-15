package taboolib.module.database

import com.zaxxer.hikari.HikariDataSource
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.library.configuration.ConfigurationSection
import taboolib.module.configuration.Configuration
import java.io.File
import java.util.concurrent.CopyOnWriteArrayList
import javax.sql.DataSource

/**
 * @author sky
 * @since 2018-05-14 19:07
 */
abstract class Host {

    abstract val url: String?

    fun dataSource(): DataSource =
        Database.createDataSource(this).apply { dataSources += this as HikariDataSource }

    companion object {

        private val dataSources = CopyOnWriteArrayList<HikariDataSource>()

        @Awake(LifeCycle.DISABLE)
        private fun release() {
            dataSources.forEach { it.close() }
        }
    }
}