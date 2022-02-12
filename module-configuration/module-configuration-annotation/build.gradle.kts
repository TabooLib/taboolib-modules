dependencies {
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-util:${project.properties["version-common"]}")
    compileOnly(project(":module-configuration:module-configuration-core"))
}