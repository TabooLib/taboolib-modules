dependencies {
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-scheduler:${project.properties["version-common"]}")
    compileOnly(project(":module-configuration:module-configuration-core"))
}