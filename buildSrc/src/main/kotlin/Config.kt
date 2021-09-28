import org.gradle.api.JavaVersion

object Config {

    const val kotlinVersion = "1.5.30"
    val javaVersion = JavaVersion.VERSION_1_8

    object AndroidProject {
        const val ndkVersion = "23.0.7599858"
        const val compileSdkVersion = 30
        const val minSdkVersion = 21
        const val targetSdkVersion = 30
        const val versionCode = 1
        const val versionName = "1.0"
    }

    const val isReleaseMinifyEnabled = false

}