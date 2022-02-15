package taboolib.module.database

import java.io.File

data class SQLite(val file: File) : Host() {

    override val url: String = "jdbc:sqlite:" + file.path
}