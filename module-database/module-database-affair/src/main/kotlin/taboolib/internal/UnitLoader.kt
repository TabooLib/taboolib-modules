package taboolib.internal

import taboolib.common.LifeCycle
import taboolib.common.inject.Bind
import taboolib.common.inject.Injector
import taboolib.common.io.InstGetter
import taboolib.common.platform.Awake
import taboolib.module.database.DataUnit
import taboolib.module.database.Proxy
import taboolib.module.database.Table

@Internal
@Awake
@Bind([Table::class, Proxy::class], type = [DataUnit::class], target = Bind.Target.CLASS)
object UnitLoader : Injector(LifeCycle.INIT) {

    override fun preInject(clazz: Class<*>, instance: InstGetter<*>) {
        super.preInject(clazz, instance)
    }
}