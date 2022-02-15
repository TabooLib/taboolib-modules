package taboolib.internal

import taboolib.common.reflect.ReflexClass
import taboolib.module.database.DataAffairs
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import javax.sql.DataSource

/**
 * TabooLib
 * taboolib.internal.SimpleAffairProxy
 *
 * @author 坏黑
 * @since 2022/2/15 10:04 PM
 */
@Internal
class SimpleAffairProxy<T : DataAffairs>(val dataSource: DataSource, val affairClass: Class<T>) : InvocationHandler {

    val structure = ReflexClass.of(affairClass).structure

    @Suppress("UNCHECKED_CAST")
    fun newProxy(): T {
        return Proxy.newProxyInstance(affairClass.classLoader, arrayOf(affairClass), this) as T
    }

    override fun invoke(proxy: Any, method: Method, args: Array<out Any>): Any? {
        println("method ${method.name}")
        return null
    }
}