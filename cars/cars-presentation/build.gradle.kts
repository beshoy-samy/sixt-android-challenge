import AppModules.withCoreModule
import Cars.withCarsDomainModule

plugins {
    id(Build.Plugins.androidLibraryPlugin)
    kotlin(Build.Plugins.kotlinAndroidPlugin)
    kotlin(Build.Plugins.kotlinKaptPlugin)
    id(Build.Plugins.hiltAndroidPlugin)
    id(Build.Plugins.mapsPlatformSecretPlugin)
}

android {
    compileSdk = Config.AndroidProject.compileSdkVersion

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

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    withAndroidX()
    withLifeCycleKtx()
    withHilt()
    withNavigationComponent()
    withCoreModule()
    withCarsDomainModule()
    withGoogleMaps()
}

kapt {
    correctErrorTypes = true
}