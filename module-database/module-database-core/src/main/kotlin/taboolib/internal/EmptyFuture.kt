package taboolib.internal

import taboolib.module.database.Future
import java.sql.ResultSet

@Internal
object EmptyFuture : Future<ResultSet> {

    override fun <C> call(func: ResultSet.() -> C): C {
        TODO("Not yet implemented")
    }
}