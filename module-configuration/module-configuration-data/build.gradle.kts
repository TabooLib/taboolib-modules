dependencies {
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.property("version-common")}")
    compileOnly("org.tabooproject.taboolib:common-scheduler:${project.property("version-common")}")
    compileOnly(project(":module-configuration:module-configuration-core"))
}