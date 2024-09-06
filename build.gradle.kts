import com.vanniktech.maven.publish.JavadocJar
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform") version "1.9.23"
    id("org.jetbrains.compose") version "1.6.11"
    id("com.android.library") version "8.2.0"

    id("signing")
    id("com.vanniktech.maven.publish") version "0.29.0"
}

group = "io.github.iamcalledrob"
version = "1.0.4"

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvmToolchain(17)

    jvm()
    androidTarget()

    applyDefaultHierarchyTemplate()

    sourceSets {
        commonMain {
            dependencies {
                implementation("androidx.graphics:graphics-shapes:1.0.1")
                implementation(compose.foundation)
            }
        }

        // Leaving some hope that androidx.graphics:graphics-shape may eventually support
        // non-desktop multiplatform targets too.
        val nonAndroidMain by creating {
            dependsOn(commonMain.get())
        }
        jvmMain.get().dependsOn(nonAndroidMain)
    }
}

android {
    namespace = "io.github.iamcalledrob.smoothRoundedCornerShape"
    compileSdk = 34
}



// --- Signing + Publishing ---
//
// Make sure the following are accessible via gradle.properties.
// If they are missing or invalid, expect cryptic and misleading errors.
// Ideally in ~/.gradle/gradle.properties so it's not checked in to source control:
// - signing.gnupg.keyId,
// - signing.gnupg.password
// - signing.gnupg.secretKeyRingFile
// - mavenCentralUsername
// - mavenCentralPassword

val githubAccount = "iamcalledrob"
val githubRepository = "smooth-rounded-corner-shape"
val githubUrl = "https://github.com/$githubAccount/$githubRepository"
val email = "me@iamcalledrob.com"
val projectDescription = "SmoothRoundedCornerShape"

mavenPublishing {
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

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
}

signing {
    println("Signing...")
    useGpgCmd()
    sign(publishing.publications)
}