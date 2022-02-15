import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    compileOnly("org.tabooproject.taboolib:common-core:${project.properties["version-common"]}")
    compileOnly(project(":module-configuration:module-configuration-core"))
    compileOnly(project(":module-database:module-database-core"))
}

shrinking {
    shadow = true
}

tasks {
    withType<ShadowJar> {
        relocate("com.zaxxer.hikari", "hikari403")
    }
}