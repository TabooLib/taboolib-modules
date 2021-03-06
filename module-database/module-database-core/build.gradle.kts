import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    compileOnly("com.zaxxer:HikariCP:4.0.3")
    compileOnly("org.tabooproject.taboolib:common-core:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-plugin:${project.properties["version-common"]}") // Optional
    compileOnly("org.tabooproject.taboolib:common-environment:${project.properties["version-common"]}")
    compileOnly(project(":module-configuration:module-configuration-core"))
}

shrinking {
    shadow = true
}

tasks {
    withType<ShadowJar> {
        relocate("com.zaxxer.hikari", "hikari403")
    }
}