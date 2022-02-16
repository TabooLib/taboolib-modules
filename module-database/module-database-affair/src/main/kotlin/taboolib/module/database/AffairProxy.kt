package taboolib.module.database

import taboolib.internal.SimpleAffairProxy
import javax.sql.DataSource

inline fun <reified T : DataAffairs> affairProxy(host: DataSourceContainer): T {
    return SimpleAffairProxy(host.dataSource(), T::class.java).newProxy()
}

inline fun <reified T : DataAffairs> affairProxy(dataSource: DataSource): T {
    return SimpleAffairProxy(dataSource, T::class.java).newProxy()
}