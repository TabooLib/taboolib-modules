package taboolib.module.database.table

import java.sql.PreparedStatement
import java.sql.ResultSet

object TypeSQLite {

    object TypeBoolean : Type<Boolean>("boolean", Boolean::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getBoolean(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Boolean) {
            preparedStatement.setBoolean(index, parameter)
        }
    }
}