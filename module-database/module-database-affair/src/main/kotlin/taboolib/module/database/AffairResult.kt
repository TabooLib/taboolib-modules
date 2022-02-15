package taboolib.module.database

/**
 * TabooLib
 * taboolib.module.database.AffairResult
 *
 * @author 坏黑
 * @since 2022/2/15 6:23 PM
 */
abstract class AffairResult {

    abstract class Success : AffairResult()

    abstract class Failure : AffairResult()
}