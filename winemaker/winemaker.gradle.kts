import ProjectVersions.openosrsVersion

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    kotlin("kapt") version "1.3.61"
}

version = "0.0.1"
project.extra["PluginName"] = "Wine Maker" // This is the name that is used in the external plugin manager panel
project.extra["PluginDescription"] = "An automatic wine maker" // This is the description that is used in the external plugin manager panel

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    annotationProcessor(Libraries.lombok)
    kapt(Libraries.pf4j)
    compileOnly("net.unethicalite:runelite-api:$openosrsVersion+")
    compileOnly("net.unethicalite:runelite-client:$openosrsVersion+")
    compileOnly("net.unethicalite.rs:runescape-api:$openosrsVersion")

    compileOnly(Libraries.guice)
    compileOnly(Libraries.javax)
    compileOnly(Libraries.lombok)
    compileOnly(Libraries.pf4j)
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "11"
            freeCompilerArgs = listOf("-Xjvm-default=enable")
        }
        sourceCompatibility = "11"
    }
    jar {
        manifest {
            attributes(mapOf(
                "Plugin-Version" to project.version,
                "Plugin-Id" to nameToId(project.extra["PluginName"] as String),
                "Plugin-Provider" to project.extra["PluginProvider"],
                "Plugin-Description" to project.extra["PluginDescription"],
                "Plugin-License" to project.extra["PluginLicense"]
            ))
        }
    }
}