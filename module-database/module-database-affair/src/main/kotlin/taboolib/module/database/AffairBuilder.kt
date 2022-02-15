package taboolib.module.database

import taboolib.common.boot.SimpleServiceLoader

/**
 * TabooLib
 * taboolib.module.database.AffairBuilder
 *
 * @author 坏黑
 * @since 2022/2/15 6:12 PM
 */
abstract class AffairBuilder {

    abstract fun <T : DataUnit> insert(func: T): AffairTask

    abstract fun <T : DataUnit> update(bind: Class<out T>, func: BehaviorUpdate<T>.() -> Unit): AffairTask

    abstract fun <T : DataUnit> drop(bind: Class<out T>, func: BehaviorFind<T>.() -> Unit): AffairTask

    abstract fun <T : DataUnit> find(bind: Class<out T>, vararg rows: String, func: BehaviorFind<T>.() -> Unit): T?

    abstract fun <T : DataUnit> findAll(bind: Class<out T>, vararg rows: String, limit: Int, func: BehaviorFind<T>.() -> Unit): T?

    companion object {

        @JvmField
        val INSTANCE: AffairBuilder = SimpleServiceLoader.load(AffairBuilder::class.java)
    }
}