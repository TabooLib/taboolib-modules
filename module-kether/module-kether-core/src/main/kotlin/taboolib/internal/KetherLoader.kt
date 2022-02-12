package taboolib.internal

import taboolib.common.LifeCycle
import taboolib.common.inject.Bind
import taboolib.common.inject.Injector
import taboolib.common.io.InstGetter
import taboolib.common.io.taboolibPath
import taboolib.common.platform.Awake
import taboolib.common.platform.function.getOpenContainers
import taboolib.common.platform.function.pluginId
import taboolib.common.reflect.ClassMethod
import taboolib.module.kether.*
import kotlin.reflect.KClass

/**
 * TabooLibKotlin
 * taboolib.module.kether.KetherLoader
 *
 * @author sky
 * @since 2021/2/6 3:33 下午
 */
@Suppress("UNCHECKED_CAST")
@Internal
@Awake
@Bind([KetherParser::class], target = Bind.Target.METHOD)
object KetherLoader : Injector(LifeCycle.LOAD) {

    val sharedParser = ArrayList<Pair<Array<String>, String>>()
    val sharedScriptProperty = ArrayList<Pair<String, String>>()

    @Awake(LifeCycle.DISABLE)
    fun cancel() {
        getOpenContainers().forEach { remote ->
            sharedParser.forEach {
                remote.call(StandardChannel.REMOTE_REMOVE_ACTION, arrayOf(it.first, it.second))
            }
            sharedScriptProperty.forEach {
                remote.call(StandardChannel.REMOTE_REMOVE_PROPERTY, arrayOf(it.first, it.second))
            }
        }
    }

    override fun inject(clazz: Class<*>, method: ClassMethod, instance: InstGetter<*>) {
        if (method.isAnnotationPresent(KetherParser::class.java) && method.returnType == ScriptActionParser::class.java) {
            val parser = method.invoke(instance.get() ?: return) as ScriptActionParser<*>
            val annotation = method.getAnnotation(KetherParser::class.java)!!
            val value = annotation.property<Array<String>>("value")!!
            val namespace = annotation.property<String>("namespace")!!
            if (annotation.property("shared")!!) {
                sharedParser += value to namespace
                getOpenContainers().forEach {
                    it.call(StandardChannel.REMOTE_ADD_ACTION, arrayOf(pluginId, value, namespace))
                }
            }
            value.forEach {
                Kether.addAction(it, parser, namespace)
            }
        }
        if (method.isAnnotationPresent(KetherProperty::class.java) && method.returnType == ScriptProperty::class.java) {
            taboolibPath ?: return
            val property = method.invoke(instance.get() ?: return) as ScriptProperty<*>
            val annotation = method.getAnnotation(KetherProperty::class.java)!!
            val bind = annotation.property<KClass<*>>("bind")!!
            if (annotation.property("shared")!!) {
                var name = bind.java.name
                name = if (name.startsWith(taboolibPath!!)) "@${name.substring(taboolibPath!!.length)}" else name
                sharedScriptProperty += name to property.id
                getOpenContainers().forEach {
                    it.call(StandardChannel.REMOTE_ADD_PROPERTY, arrayOf(pluginId, name, property))
                }
            }
            Kether.addScriptProperty(bind.java, property)
        }
    }
}