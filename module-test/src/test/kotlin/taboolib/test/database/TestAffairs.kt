package taboolib.test.database

import taboolib.module.database.*
import java.time.LocalDate
import javax.sql.DataSource

interface TestAffairs : DataAffairs {

    @Primitive
    fun save(user: User)

    @Primitive
    fun find(uniqueId: String): User?

    @Primitive
    fun updateUser(uniqueId: String, time: LocalDate)

    @Primitive
    fun updateVariable(name: String, data: String)

    @Primitive
    fun updateVariable(name: String, data: String, status: Boolean)

    @Primitive
    fun updateVariable(@Key id: Long, @Key name: String, data: String, status: Boolean)

}