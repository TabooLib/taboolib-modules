dependencies {
    compileOnly("com.google.guava:guava:17.0")
    compileOnly("org.apache.commons:commons-lang3:3.5")
    compileOnly("org.tabooproject.taboolib:common-core:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-util:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-adapter:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-openapi:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-scheduler:${project.properties["version-common"]}")
    compileOnly(project(":module-configuration:module-configuration-annotation"))
    compileOnly(project(":module-configuration:module-configuration-core"))
    compileOnly(project(":module-lang:module-lang-core"))
    compileOnly(project(":module-chat"))
}

shrinking {
    packages = listOf("taboolib.module.kether.action")
}