plugins {
    id(Build.Plugins.androidLibraryPlugin)
    kotlin(Build.Plugins.kotlinAndroidPlugin)
    kotlin(Build.Plugins.kotlinKaptPlugin)
    id(Build.Plugins.hiltAndroidPlugin)
}

android {
    compileSdk = Config.AndroidProject.compileSdkVersion
    ndkVersion = Config.AndroidProject.ndkVersion

    defaultConfig {
        minSdk = Config.AndroidProject.minSdkVersion
        targetSdk = Config.AndroidProject.targetSdkVersion
    }

    buildTypes {

        release {
            isMinifyEnabled = Config.isReleaseMinifyEnabled
        }

    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }
}

dependencies {
    withNetworkDependencies()
    withHilt()
}

kapt {
    correctErrorTypes = true
}