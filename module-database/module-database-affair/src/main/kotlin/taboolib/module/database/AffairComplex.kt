package taboolib.module.database

/**
 * TabooLib
 * taboolib.module.database.AffairComplex
 *
 * @author 坏黑
 * @since 2022/2/15 11:38 PM
 */
open class AffairComplex {

    fun <T : DataUnit> insert(func: T): AffairTask {
        return AffairBuilder.INSTANCE.insert(func)
    }

    inline fun <reified T : DataUnit> update(noinline func: BehaviorUpdate<T>.() -> Unit): AffairTask {
        return AffairBuilder.INSTANCE.update(T::class.java, func)
    }

    inline fun <reified T : DataUnit> drop(noinline func: BehaviorFind<T>.() -> Unit): AffairTask {
        return AffairBuilder.INSTANCE.drop(T::class.java, func)
    }

    inline fun <reified T : DataUnit> find(vararg rows: String, noinline func: BehaviorFind<T>.() -> Unit): T? {
        return AffairBuilder.INSTANCE.find(T::class.java, *rows, func = func)
    }

    inline fun <reified T : DataUnit> findAll(vararg rows: String, limit: Int = -1, noinline func: BehaviorFind<T>.() -> Unit): T? {
        return AffairBuilder.INSTANCE.findAll(T::class.java, *rows, limit = limit, func = func)
    }
}