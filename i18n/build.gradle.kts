import mihon.gradle.tasks.GenerateLocalesConfigTask
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    alias(mihonx.plugins.kotlin.multiplatform)
    alias(mihonx.plugins.spotless)

    alias(libs.plugins.moko.resources)
}

kotlin {
    android {
        namespace = "tachiyomi.i18n"
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    @Suppress("UnstableApiUsage")
    dependencies {
        api(libs.moko.resources)
    }

    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
}

android {
    sourceSets {
        val main by getting
        main.res.srcDirs(
            "src/commonMain/resources"
        )
    }

    lint {
        disable.addAll(listOf("MissingTranslation", "ExtraTranslation"))
    }
}

multiplatformResources {
    resourcesPackage.set("tachiyomi.i18n")
}

tasks.register<GenerateLocalesConfigTask>("generateLocalesConfig") {
    outputDir.set(file("src/commonMain/resources"))
}