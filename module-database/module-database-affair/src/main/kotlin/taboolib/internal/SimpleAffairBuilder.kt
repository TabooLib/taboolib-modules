package taboolib.internal

import taboolib.module.database.*

/**
 * TabooLib
 * taboolib.internal.SimpleAffairBuilder
 *
 * @author 坏黑
 * @since 2022/2/15 6:15 PM
 */
@Internal
class SimpleAffairBuilder : AffairBuilder() {

    override fun <T : DataUnit> insert(func: T): AffairTask {
        TODO("Not yet implemented")
    }

    override fun <T : DataUnit> update(bind: Class<out T>, func: BehaviorUpdate<T>.() -> Unit): AffairTask {
        TODO("Not yet implemented")
    }

    override fun <T : DataUnit> drop(bind: Class<out T>, func: BehaviorFind<T>.() -> Unit): AffairTask {
        TODO("Not yet implemented")
    }

    override fun <T : DataUnit> find(bind: Class<out T>, vararg rows: String, func: BehaviorFind<T>.() -> Unit): T? {
        TODO("Not yet implemented")
    }

    override fun <T : DataUnit> findAll(bind: Class<out T>, vararg rows: String, limit: Int, func: BehaviorFind<T>.() -> Unit): T? {
        TODO("Not yet implemented")
    }
}