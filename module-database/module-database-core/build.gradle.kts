import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    compileOnly("com.zaxxer:HikariCP:5.0.1")
    compileOnly("org.tabooproject.taboolib:common-core:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-environment:${project.properties["version-common"]}")
    compileOnly(project(":module-configuration:module-configuration-annotation"))
    compileOnly(project(":module-configuration:module-configuration-core"))
}

shrinking {
    shadow = true
}

tasks {
    withType<ShadowJar> {
        relocate("com.zaxxer.hikari", "hikari501")
    }
}