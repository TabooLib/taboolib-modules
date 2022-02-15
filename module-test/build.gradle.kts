dependencies {
    testImplementation("org.xerial:sqlite-jdbc:3.36.0.3")
    testImplementation("org.slf4j:slf4j-api:1.7.25")
    testImplementation("com.zaxxer:HikariCP:4.0.3-test")
    testImplementation("org.yaml:snakeyaml:1.28")
    testImplementation("com.electronwill.night-config:core:3.6.5")
    testImplementation("org.tabooproject.taboolib:common-util:${project.properties["version-common"]}")
    testImplementation("org.tabooproject.taboolib:common-core:${project.properties["version-common"]}")
    testImplementation("org.tabooproject.taboolib:common-core-impl:${project.properties["version-common"]}")
    testImplementation(project(":module-configuration:module-configuration-core"))
    testImplementation(project(":module-database:module-database-core"))
    testImplementation(project(":module-database:module-database-affair"))
}