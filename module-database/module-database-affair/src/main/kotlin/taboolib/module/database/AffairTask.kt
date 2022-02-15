package taboolib.module.database

/**
 * TabooLib
 * taboolib.module.database.AffairTask
 *
 * @author 坏黑
 * @since 2022/2/15 6:14 PM
 */
abstract class AffairTask {

    abstract fun onSuccess(result: AffairResult.Success.() -> Unit)

    abstract fun onFailure(result: AffairResult.Failure.() -> Unit)

    abstract fun mapSuccess(result: AffairResult.Success.() -> Unit)

    abstract fun <T : DataUnit> valueOr(default: T): T

    abstract fun <T : DataUnit> valueOrThrow(): T

    abstract fun <T : DataUnit> valueOrNull(): T?

    abstract fun isFailure(): Boolean

    abstract fun isSuccessful(): Boolean
}