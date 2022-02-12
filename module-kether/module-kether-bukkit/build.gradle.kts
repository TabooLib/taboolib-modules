dependencies {
    compileOnly("public:PlaceholderAPI:2.10.9")
    compileOnly("ink.ptms.core:v11800:11800:api")
    compileOnly(project(":module-kether:module-kether-core"))
}

shrinking {
    packages = listOf("taboolib.module.kether.action")
}