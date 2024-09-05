plugins {
    kotlin("multiplatform") version "1.9.23"
    id("org.jetbrains.compose") version "1.6.11"
    id("com.android.library") version "8.2.0"
    id("maven-publish")
}

group = "com.github.iamcalledrob"
version = "1.0.0"

repositories {
    mavenCentral()
    google()
}


kotlin {
    jvmToolchain(17)

    jvm("desktop")

    androidTarget {
        publishLibraryVariants("release", "debug")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation("androidx.graphics:graphics-shapes:1.0.1")

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
            }
        }
    }
}

android {
    namespace = "com.github.iamcalledrob.smoothRoundedCornerShape"
    compileSdk = 34
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["kotlin"])
        }
    }
}