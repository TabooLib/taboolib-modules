package taboolib.internal

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import taboolib.common.platform.function.releaseResourceFile
import taboolib.module.configuration.Configuration
import taboolib.module.database.*

/**
 * TabooLib
 * taboolib.internal.SimpleDatabase
 *
 * @author 坏黑
 * @since 2022/2/15 4:20 PM
 */
@Internal
class SimpleDatabase : Database() {

    val settingsFile: Configuration = try {
        Configuration.loadFromFile(releaseResourceFile("datasource.yml"))
    } catch (_: Throwable) {
        Configuration.loadFromInputStream(javaClass.classLoader.getResourceAsStream("datasource.yml")!!)
    }

    override fun createConfig(host: DataSourceContainer): HikariConfig {
        val config = HikariConfig()
        config.jdbcUrl = host.url()
        when (host) {
            is PlatformSQL -> {
                config.driverClassName = settingsFile.getString("DefaultSettings.DriverClassName", "com.mysql.jdbc.Driver")
                config.username = host.user
                config.password = host.password
            }
            is PlatformSQLite -> {
                config.driverClassName = "org.sqlite.JDBC"
            }
            else -> {
                error("Unsupported host: $host")
            }
        }
        config.isAutoCommit = settingsFile.getBoolean("DefaultSettings.AutoCommit", true)
        config.minimumIdle = settingsFile.getInt("DefaultSettings.MinimumIdle", 1)
        config.maximumPoolSize = settingsFile.getInt("DefaultSettings.MaximumPoolSize", 10)
        config.validationTimeout = settingsFile.getLong("DefaultSettings.ValidationTimeout", 5000)
        config.connectionTimeout = settingsFile.getLong("DefaultSettings.ConnectionTimeout", 30000)
        config.idleTimeout = settingsFile.getLong("DefaultSettings.IdleTimeout", 600000)
        config.maxLifetime = settingsFile.getLong("DefaultSettings.MaxLifetime", 1800000)
        if (settingsFile.contains("DefaultSettings.ConnectionTestQuery")) {
            config.connectionTestQuery = settingsFile.getString("DefaultSettings.ConnectionTestQuery")
        }
        if (settingsFile.contains("DefaultSettings.DataSourceProperty")) {
            settingsFile.getConfigurationSection("DefaultSettings.DataSourceProperty")?.getKeys(false)?.forEach { key ->
                config.addDataSourceProperty(key, settingsFile.getString("DefaultSettings.DataSourceProperty.$key"))
            }
        }
        return config
    }

    override fun createDataSource(host: DataSourceContainer, config: HikariConfig?) = HikariDataSource(config ?: createConfig(host))
}