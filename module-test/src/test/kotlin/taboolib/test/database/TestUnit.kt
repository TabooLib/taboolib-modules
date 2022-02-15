package taboolib.test.database

import taboolib.module.database.*
import java.time.LocalDate

@Table("user")
class User(
    @Id
    val id: Long,
    val name: String,
    @Ext(alias = "uuid", unique = true)
    val uniqueId: String,
    val time: LocalDate,
) : DataUnit

@Table("user_data")
class UserData(
    @Id
    val id: Long,
    @Ext(key = true)
    val user: Long,
    @Ext(key = true, length = 64)
    val key: String,
    @Ext(length = 64)
    val value: String,
    @Ext(alias = "mode")
    val status: Boolean,
) : DataUnit

@Table("quest")
class Quest(
    @Id
    val id: Long,
    @Ext(key = true)
    val user: Long,
    @Ext(key = true, length = 64)
    val quest: String,
    @Ext(alias = "mode")
    val status: Boolean,
) : DataUnit

@Table("quest_data")
class QuestData(
    @Id
    val id: Long,
    @Ext(key = true)
    val quest: Long,
    @Ext(key = true, length = 64)
    val key: String,
    @Ext(length = 64)
    val value: String,
    @Ext(alias = "mode")
    val status: Boolean,
) : DataUnit

@Table("variables")
class Variable(
    @Id
    val id: Long,
    @Ext(unique = true, length = 64)
    val name: String,
    @Ext(length = 64)
    val data: String,
    @Ext(alias = "mode")
    val status: Boolean,
) : DataUnit