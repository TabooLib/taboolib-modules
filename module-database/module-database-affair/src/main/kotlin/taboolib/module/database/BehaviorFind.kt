package taboolib.module.database

/**
 * TabooLib
 * taboolib.module.database.BehaviorFind
 *
 * @author 坏黑
 * @since 2022/2/15 6:05 PM
 */
abstract class BehaviorFind<T : DataUnit>(val unit: T) : Behavior<T>() {

    infix fun Any.eq(any: Any?) = where { eq(any) }
}