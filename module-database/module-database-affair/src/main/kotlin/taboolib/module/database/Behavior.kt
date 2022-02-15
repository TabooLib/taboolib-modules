package taboolib.module.database

/**
 * TabooLib
 * taboolib.module.database.Behavior
 *
 * @author 坏黑
 * @since 2022/2/15 6:05 PM
 */
abstract class Behavior<T : DataUnit> {

    abstract fun where(where: ActionWhere<T>.() -> Unit)

    abstract class ActionWhere<T : DataUnit>(val unit: T) {

        abstract infix fun Any.eq(any: Any?)
    }
}