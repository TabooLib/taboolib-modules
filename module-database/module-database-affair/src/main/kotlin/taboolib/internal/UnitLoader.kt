package taboolib.internal

import taboolib.common.LifeCycle
import taboolib.common.inject.Bind
import taboolib.common.inject.Injector
import taboolib.common.io.InstGetter
import taboolib.common.platform.Awake
import taboolib.common.reflect.ReflexClass
import taboolib.module.database.DataUnit
import taboolib.module.database.Proxy
import taboolib.module.database.Table

@Internal
@Awake
@Bind([Table::class, Proxy::class], type = [DataUnit::class], target = Bind.Target.CLASS)
object UnitLoader : Injector(LifeCycle.INIT) {

    override fun preInject(clazz: Class<*>, instance: InstGetter<*>) {
        var createTable = false
        val name = when {
            clazz.isAnnotationPresent(Table::class.java) -> {
                createTable = true
                clazz.getAnnotation(Table::class.java).value
            }
            clazz.isAnnotationPresent(Proxy::class.java) -> clazz.getAnnotation(Proxy::class.java).value
            else -> error("out of case")
        }
        ReflexClass.of(clazz).structure.fields.forEach { field ->
        }
    }
}