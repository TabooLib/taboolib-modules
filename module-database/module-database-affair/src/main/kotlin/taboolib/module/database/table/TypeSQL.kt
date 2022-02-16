package taboolib.module.database.table

import java.math.BigDecimal
import java.sql.*
import java.sql.Date
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

object TypeSQL {

    object TypeBoolean : Type<Boolean>("boolean", Boolean::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getBoolean(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Boolean) {
            preparedStatement.setBoolean(index, parameter)
        }
    }

    @Parameter
    object TypeInt : Type<Int>("int", Int::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getInt(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Int) {
            preparedStatement.setInt(index, parameter)
        }
    }

    @Parameter
    object TypeShort : Type<Short>("smallint", Short::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getShort(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Short) {
            preparedStatement.setShort(index, parameter)
        }
    }

    @Parameter
    object TypeLong : Type<Long>("bigint", Long::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getLong(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Long) {
            preparedStatement.setLong(index, parameter)
        }
    }

    @Parameter
    object TypeFloat : Type<Float>("float", Float::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getFloat(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Float) {
            preparedStatement.setFloat(index, parameter)
        }
    }

    @Parameter
    object TypeDouble : Type<Double>("double", Double::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int) = resultSet.getDouble(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Double) {
            preparedStatement.setDouble(index, parameter)
        }
    }

    @Parameter
    object TypeDecimal : Type<BigDecimal>("decimal", BigDecimal::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): BigDecimal = resultSet.getBigDecimal(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: BigDecimal) {
            preparedStatement.setBigDecimal(index, parameter)
        }
    }

    @Parameter
    object TypeString : Type<String>("varchar", String::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): String = resultSet.getString(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: String) {
            preparedStatement.setString(index, parameter)
        }
    }

    object TypeText : Type<Text>("text", Text::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): Text = Text(resultSet.getString(index))

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Text) {
            preparedStatement.setString(index, parameter.value)
        }
    }

    object TypeBlob : Type<ByteArray>("blob", ByteArray::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): ByteArray = resultSet.getBytes(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: ByteArray) {
            preparedStatement.setBytes(index, parameter)
        }
    }

    object TypeBytes : Type<ByteArray>("bytes", ByteArray::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): ByteArray = resultSet.getBytes(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: ByteArray) {
            preparedStatement.setBytes(index, parameter)
        }
    }

    object TypeJdbcTimestamp : Type<Timestamp>("timestamp", Timestamp::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): Timestamp = resultSet.getTimestamp(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Timestamp) {
            preparedStatement.setTimestamp(index, parameter)
        }
    }

    object TypeJdbcDate : Type<Date>("date", Date::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): Date = resultSet.getDate(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Date) {
            preparedStatement.setDate(index, parameter)
        }
    }

    object TypeJdbcTime : Type<Time>("time", Time::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): Time = resultSet.getTime(index)

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Time) {
            preparedStatement.setTime(index, parameter)
        }
    }

    object TypeInstant : Type<Instant>("timestamp", Instant::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): Instant = resultSet.getTimestamp(index).toInstant()

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Instant) {
            preparedStatement.setTimestamp(index, Timestamp.from(parameter))
        }
    }

    object TypeLocalDateTime : Type<LocalDateTime>("datetime", LocalDateTime::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): LocalDateTime {
            return resultSet.getTimestamp(index).toLocalDateTime()
        }

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: LocalDateTime) {
            preparedStatement.setTimestamp(index, Timestamp.valueOf(parameter))
        }
    }

    object TypeLocalDate : Type<LocalDate>("date", LocalDate::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): LocalDate {
            return resultSet.getDate(index).toLocalDate()
        }

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: LocalDate) {
            preparedStatement.setDate(index, Date.valueOf(parameter))
        }
    }

    @Parameter
    object TypeEnum : Type<Enum<*>>("varchar", Enum::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): Enum<*> {
            val value = resultSet.getString(index)
            return javaType.enumConstants.first { it.name == value }
        }

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: Enum<*>) {
            preparedStatement.setString(index, parameter.name)
        }
    }

    object TypeUUID : Type<UUID>("char(36)", UUID::class.java) {

        override fun getResult(resultSet: ResultSet, index: Int): UUID {
            return UUID.fromString(resultSet.getString(index))
        }

        override fun setParameter(preparedStatement: PreparedStatement, index: Int, parameter: UUID) {
            preparedStatement.setString(index, parameter.toString())
        }
    }
}