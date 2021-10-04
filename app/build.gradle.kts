import AppModules.withCarsModule
import AppModules.withCoreModule

plugins {
    id(Build.Plugins.androidApplicationPlugin)
    kotlin(Build.Plugins.kotlinAndroidPlugin)
    kotlin(Build.Plugins.kotlinKaptPlugin)
    id(Build.Plugins.hiltAndroidPlugin)
}

android {

    compileSdk = Config.AndroidProject.compileSdkVersion

    defaultConfig {
        applicationId = "com.sixt.challenge"
        minSdk = Config.AndroidProject.minSdkVersion
        targetSdk = Config.AndroidProject.targetSdkVersion
        versionCode = Config.AndroidProject.versionCode
        versionName = Config.AndroidProject.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {
            isMinifyEnabled = Config.isReleaseMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    withHilt()
    withNavigationComponent()
    withCoreModule()
    withCarsModule()
    withTestDependencies()
}

kapt {
    correctErrorTypes = true
}