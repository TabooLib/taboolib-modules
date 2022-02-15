package taboolib.module.database

import taboolib.library.configuration.ConfigurationSection

/**
 * SQL 数据库地址
 *
 * @author sky
 * @since 2018-05-14 19:01
 */
data class SQL(val host: String, val port: Int, val user: String, val password: String, val database: String) : Host() {

    val flags = arrayListOf("characterEncoding=utf-8", "useSSL=false")

    override val url: String =
        "jdbc:mysql://$host:$port/$database${if (flags.isEmpty()) "" else "?${flags.joinToString("&")}"}"

    constructor(section: ConfigurationSection) : this(
        section.getString("host", "localhost")!!,
        section.getInt("port"),
        section.getString("user", "root")!!,
        section.getString("password", "root")!!,
        section.getString("database", "test")!!,
    )
}