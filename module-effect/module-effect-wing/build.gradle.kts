dependencies {
    compileOnly("org.tabooproject.taboolib:common-util:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-adapter:${project.properties["version-common"]}")
    compileOnly(project(":module-effect:module-effect-core"))
}