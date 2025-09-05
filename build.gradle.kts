import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.2.10"
    id("org.jetbrains.kotlin.plugin.compose") version "2.2.10"
    id("org.jetbrains.compose") version "1.8.2"
    id("com.android.library") version "8.11.1"

    id("com.vanniktech.maven.publish") version "0.34.0"
}

group = "io.github.iamcalledrob"
version = "1.0.5"

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvmToolchain(17)

    jvm()
    androidTarget()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                implementation("androidx.graphics:graphics-shapes:1.1.0-beta01")
                implementation(compose.foundation)
            }
        }

        val nonAndroidMain by creating {
            dependsOn(commonMain.get())
        }

        jvmMain.get().dependsOn(nonAndroidMain)
        iosMain.get().dependsOn(nonAndroidMain)
        wasmJsMain.get().dependsOn(nonAndroidMain)
    }
}

android {
    namespace = "io.github.iamcalledrob.smoothRoundedCornerShape"
    compileSdk = 36
}

val githubAccount = "iamcalledrob"
val githubRepository = "smooth-rounded-corner-shape"
val githubUrl = "https://github.com/$githubAccount/$githubRepository"
val email = "me@iamcalledrob.com"
val projectDescription = "SmoothRoundedCornerShape"

mavenPublishing {
    publishToMavenCentral()

    signAllPublications()

    coordinates(groupId = group.toString(), artifactId = githubRepository, version = version.toString())

    configure(
        KotlinMultiplatform(
            javadocJar = JavadocJar.Empty(),
            sourcesJar = true,
            androidVariantsToPublish = listOf("debug", "release"),
        )
    )

    pom {
        name.set(githubRepository)
        description.set(projectDescription)
        url.set(githubUrl)
        licenses {
            license {
                url.set("$githubUrl/blob/master/LICENSE.TXT")
            }
        }
        developers {
            developer {
                id.set(githubAccount)
                name.set(githubAccount)
                url.set(githubUrl)
            }
        }
        scm {
            url.set(githubUrl)
            connection.set("scm:git:git://github.com/$githubAccount/$githubRepository.git")
            developerConnection.set("scm:git:ssh://git@github.com/$githubAccount/$githubRepository.git")
        }
    }
}