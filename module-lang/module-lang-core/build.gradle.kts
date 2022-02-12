dependencies {
    compileOnly("org.tabooproject.taboolib:common-util:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-event:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-adapter:${project.properties["version-common"]}")
    compileOnly(project(":module-chat")) // ColorTranslator
    compileOnly(project(":module-configuration:module-configuration-core"))
}