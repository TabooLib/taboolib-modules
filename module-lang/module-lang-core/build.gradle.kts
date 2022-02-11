dependencies {
    compileOnly("org.tabooproject.taboolib:common-util:${project.property("version-common")}")
    compileOnly("org.tabooproject.taboolib:common-event:${project.property("version-common")}")
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.property("version-common")}")
    compileOnly("org.tabooproject.taboolib:common-adapter:${project.property("version-common")}")
    compileOnly(project(":module-chat")) // ColorTranslator
    compileOnly(project(":module-configuration:module-configuration-core"))
}