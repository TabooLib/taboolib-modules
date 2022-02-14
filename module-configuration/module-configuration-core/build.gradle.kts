import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    implementation("com.electronwill.night-config:core-conversion:6.0.0")
    compileOnly("org.yaml:snakeyaml:1.28")
    compileOnly("com.typesafe:config:1.4.1")
    compileOnly("com.electronwill.night-config:core:3.6.5")
    compileOnly("com.electronwill.night-config:toml:3.6.5")
    compileOnly("com.electronwill.night-config:json:3.6.5")
    compileOnly("com.electronwill.night-config:hocon:3.6.5")
    compileOnly("org.tabooproject.taboolib:common-util:${project.properties["version-common"]}")
    // Optional
    compileOnly("org.tabooproject.taboolib:common-adapter:${project.properties["version-common"]}")
    compileOnly("org.tabooproject.taboolib:common-environment:${project.properties["version-common"]}")
    compileOnly(project(":module-chat"))
}

shrinking {
    shadow = true
}

tasks {
    withType<ShadowJar> {
        dependencies {
            include(dependency("com.electronwill.night-config:core-conversion:6.0.0"))
        }
        relocate("com.electronwill.nightconfig.core.conversion", "taboolib.library.configuration")
        minimize()
    }
}