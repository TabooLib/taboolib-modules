dependencies {
    compileOnly("org.tabooproject.taboolib:common-util:${project.property("version-common")}")
    compileOnly("org.tabooproject.taboolib:common-adapter:${project.property("version-common")}")
    compileOnly(project(":module-effect:module-effect-core"))
}