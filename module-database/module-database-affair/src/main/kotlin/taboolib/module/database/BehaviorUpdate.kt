package taboolib.module.database

/**
 * TabooLib
 * taboolib.module.database.BehaviorUpdate
 *
 * @author 坏黑
 * @since 2022/2/15 6:05 PM
 */
abstract class BehaviorUpdate<T : DataUnit> : Behavior<T>() {

    abstract fun set(set: ActionSet<T>.() -> Unit)

    abstract class ActionSet<T : DataUnit>(val unit: T) {

        abstract infix fun Any.to(any: Any?)
    }
}