package taboolib.module.database.table

import taboolib.common.LifeCycle
import taboolib.common.io.findInstance
import taboolib.common.platform.Awake
import taboolib.module.database.Platform

object Types {

    internal val types = mutableMapOf<String, MutableList<Type<*>>>()

    fun registerType(type: Type<*>, platform: Class<out Platform>) {
        types.computeIfAbsent(platform.name) { mutableListOf() }.add(type)
    }

    inline fun <reified T : Platform> registerType(type: Type<*>) {
        registerType(type, T::class.java)
    }

    fun getTypes(platform: Class<out Platform>): List<Type<*>> {
        return types[platform.name] ?: emptyList()
    }

    inline fun <reified T : Platform> getTypes(): List<Type<*>> {
        return getTypes(T::class.java)
    }

    fun getFromJavaType(javaType: Class<*>, platform: Class<out Platform>): Type<*>? {
        return getTypes(platform).firstOrNull { it.javaType == javaType }
    }

    @Awake(LifeCycle.INIT)
    internal fun init() {
        TypeSQL::class.java.declaredClasses.filter { Type::class.java.isAssignableFrom(it) }.forEach {
            Types.registerType<Platform.SQL>(it.findInstance().get() as Type<*>)
        }
        TypeSQLite::class.java.declaredClasses.filter { Type::class.java.isAssignableFrom(it) }.forEach {
            Types.registerType<Platform.SQLite>(it.findInstance().get() as Type<*>)
        }
    }
}