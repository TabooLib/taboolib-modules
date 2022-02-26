package taboolib.test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import taboolib.common.io.runningClasses
import taboolib.module.database.Database
import taboolib.module.database.affairProxy
import taboolib.test.database.TestAffairs
import javax.sql.DataSource

/**
 * TabooLib
 * taboolib.test.TestDatabase
 *
 * @author 坏黑
 * @since 2022/2/15 9:47 PM
 */
class TestDatabase {

    lateinit var dataSource: DataSource
    lateinit var affairs: TestAffairs

    @BeforeEach
    internal fun setUp() {
        dataSource = Database.SQLITE("test.db").dataSource()
        affairs = affairProxy(dataSource)
    }

    @Test
    fun testClasses() {
        assert(runningClasses.isNotEmpty())
        assert(runningClasses.contains(TestDatabase::class.java))
    }

    @Test
    fun testAffair() {
    }
}