package taboolib.module.database

import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import javax.sql.DataSource

/**
 * TabooLib
 * taboolib.module.database.Query
 *
 * @author sky
 * @since 2021/6/23 2:02 下午
 */
open class Query(val table: Table<*, *>, var dataSource: DataSource) {

    val tasks = ArrayList<QueryTask>()
    var autoGeneratedKeys = Statement.RETURN_GENERATED_KEYS

    open fun createTable(checkExists: Boolean = true) {
        tasks += executeUpdate(table.generateCreateQuery(checkExists))
    }

    open fun select(func: ActionSelect.() -> Unit) {
        val action = ActionSelect(table.name).also(func)
        tasks += executeQuery(action.query, action)
    }

    open fun update(func: ActionUpdate.() -> Unit = {}) {
        val action = ActionUpdate(table.name).also(func)
        tasks += executeUpdate(action.query, action)
    }

    open fun delete(func: ActionDelete.() -> Unit = {}) {
        val action = ActionDelete(table.name).also(func)
        tasks += executeUpdate(action.query, action)
    }

    open fun insert(vararg keys: String, func: ActionInsert.() -> Unit = {}) {
        val action = ActionInsert(table.name, arrayOf(*keys)).also(func)
        tasks += executeUpdate(action.query, action)
    }

    open fun executeQuery(query: String, action: Action? = null): QueryTask {
        return QueryTask(object : Future<ResultSet> {

            override fun <C> call(func: ResultSet.() -> C): C {
                return dataSource.connection.use {
                    val prepareStatement = prepareStatement(query, autoGeneratedKeys)
                    action?.elements?.forEachIndexed { index, any -> prepareStatement.setObject(index + 1, any) }
                    try {
                        prepareStatement.executeQuery().use { func(this) }.also { action?.runFinally(prepareStatement, this@use) }
                    } catch (ex: SQLException) {
                        System.err.println(query)
                        System.err.println("Statement parameter (${action?.elements?.size ?: 0}): ${action?.elements}")
                        throw ex
                    }
                }
            }
        })
    }

    @Suppress("UNCHECKED_CAST")
    open fun executeUpdate(query: String, action: Action? = null): QueryTask {
        return RunTask(object : Future<ResultSet> {

            override fun <C> call(func: ResultSet.() -> C): C {
                return dataSource.connection.use {
                    val prepareStatement = prepareStatement(query, autoGeneratedKeys)
                    action?.elements?.forEachIndexed { index, any -> prepareStatement.setObject(index + 1, any) }
                    try {
                        (prepareStatement.executeUpdate() as C).also { action?.runFinally(prepareStatement, this@use) }
                    } catch (ex: SQLException) {
                        System.err.println(query)
                        System.err.println("Statement parameter (${action?.elements?.size ?: 0}): ${action?.elements}")
                        throw ex
                    }
                }
            }
        })
    }

    open fun Table<*, *>.generateCreateQuery(checkExists: Boolean = true): String {
        if (columns.isEmpty()) {
            error("no column")
        }
        val attributes = arrayListOf(columns.joinToString { it.query })
        if (primaryKeyForLegacy.isNotEmpty()) {
            attributes += "PRIMARY KEY (${primaryKeyForLegacy.joinToString { "`$it`" }})"
        }
        columns.filter { it is ColumnSQL && it.options.contains(ColumnOptionSQL.KEY) }
            .map { it as ColumnSQL }
            .groupBy { it.indexType }
            .forEach { (key, value) ->
                var query = "KEY `idx_${value.joinToString("_") { it.name }}` (${value.joinToString { "`${it.name}`${if (it.desc) " desc" else ""}" }})"
                if (key != IndexType.DEFAULT) {
                    query += " USING $key"
                }
                attributes += query
            }
        columns.filter { it is ColumnSQL && it.options.contains(ColumnOptionSQL.UNIQUE_KEY) }
            .map { it as ColumnSQL }
            .groupBy { it.indexType }
            .forEach { (key, value) ->
                var query = "UNIQUE KEY `uk_${value.joinToString("_") { it.name }}` (${value.joinToString { "`${it.name}`${if (it.desc) " desc" else ""}" }})"
                if (key != IndexType.DEFAULT) {
                    query += " USING $key"
                }
                attributes += query
            }
        return "CREATE TABLE ${if (checkExists) "IF NOT EXISTS " else ""}`$name` (${attributes.joinToString()})"
    }
}