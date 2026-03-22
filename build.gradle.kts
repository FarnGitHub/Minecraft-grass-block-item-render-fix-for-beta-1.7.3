plugins {
    id("java")
    id("xyz.wagyourtail.unimined") version "1.4.2-SNAPSHOT"
}

base.archivesName = project.properties["mod_jar_name"] as String
group = "no.group"
version = project.properties["mod_version"] as String

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    maven("https://maven.wagyourtail.xyz/releases")
}

val minecraftVersion = project.properties["minecraft_version"] as String

unimined.minecraft {
    version (minecraftVersion)
    side("client") // a trick because we named them based on the sides

    mappings {
        retroMCP(project.properties["rmcp_mapping"] as String)
    }

    jarMod {
        transforms(project.properties["mod_transform_map"] as String)
    }

}

dependencies {
}

tasks.withType<JavaCompile> {
    if (JavaVersion.current().isJava9Compatible) {
        options.release.set(8)
    }
}

tasks.jar {
    manifest {
        attributes(
            "JarModAgent-Transforms" to "grassblockitemrenderfix.transform",
            "JarModAgent-Refmaps" to "grassblockitemrenderfix-refmap.json"
        )
    }
}