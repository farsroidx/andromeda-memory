plugins {
    // android
    id("com.android.library")
    // jetbrains
    id("org.jetbrains.kotlin.android")
    // maven
    id("maven-publish")
}

android {

    namespace  = "ir.farsroidx.m31"
    compileSdk = 34

    defaultConfig {
        multiDexEnabled           = true
        minSdk                    = 21
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {

        release {

            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "r8-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // Android-X
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.multidex:multidex:2.0.1")

    // Base Module noinspection GradleDynamicVersion
    implementation("com.github.farsroidx:andromeda-foundation:1.0.0")

    // Koin
    implementation("io.insert-koin:koin-android:3.5.6")

}

publishing {

    publications {

        register<MavenPublication>("release") {

            groupId    = "ir.farsroidx.m31"
            artifactId = "andromeda-memory-cache"
            version    = "1.0.0"

            afterEvaluate {

                from(
                    components["release"]
                )
            }
        }
    }
}