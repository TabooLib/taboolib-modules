import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

dependencies {
    compileOnly("com.zaxxer:HikariCP:4.0.3")
    implementation(project(":module-database-core"))
}

tasks {
    withType<ShadowJar> {
        archiveClassifier.set("")
        relocate("com.zaxxer.hikari", "com.zaxxer.hikari_4_0_3")
    }
}