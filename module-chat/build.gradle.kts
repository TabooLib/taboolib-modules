import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    implementation("net.md-5:bungeecord-chat:1.17")
    compileOnly("com.google.code.gson:gson:2.8.7")
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
        archiveBaseName.set("${archiveBaseName.get()}-shaded")
        dependencies {
            include(dependency("net.md-5:bungeecord-chat:1.17"))
        }
    }
}