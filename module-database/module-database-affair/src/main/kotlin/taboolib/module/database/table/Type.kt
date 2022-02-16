package taboolib.module.database.table

import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * TabooLib
 * taboolib.module.database.table.Types
 *
 * @author 坏黑
 * @since 2022/2/16 4:40 PM
 */
abstract class Type<T>(val type: String, val javaType: Class<T>) {

    abstract fun getResult(resultSet: ResultSet, index: Int): T?

    abstract fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: T)
}

class Char(val value: String)

class Text(val value: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Parameter