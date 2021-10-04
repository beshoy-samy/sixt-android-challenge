import org.gradle.kotlin.dsl.DependencyHandlerScope

object GoogleServices {

    internal object Versions {
        const val maps = "17.0.1"
    }

    internal const val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"
}

fun DependencyHandlerScope.withGoogleMaps() {
    add("implementation", GoogleServices.maps)
    add("implementation", "com.google.maps.android:maps-ktx:3.2.0")
    add("implementation", "com.google.maps.android:maps-utils-ktx:3.2.0")
}