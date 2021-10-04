import org.gradle.kotlin.dsl.DependencyHandlerScope

object AndroidX {

    internal object Versions {
        const val coreKtx = "1.6.0"
        const val appcompat = "1.3.1"
        const val material = "1.4.0"
        const val constraint = "2.1.0"
        const val recycler = "1.2.1"
        const val cardView = "1.0.0"
    }

    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val constraint =
        "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    private const val recycler = "androidx.recyclerview:recyclerview:${Versions.recycler}"
    private const val cardView = "androidx.cardview:cardview:${Versions.cardView}"

    internal val dependencies =
        mapOf(
            "implementation" to listOf(coreKtx, appcompat, material, constraint, recycler, cardView)
        )
}

fun DependencyHandlerScope.withAndroidX() {

    AndroidX.dependencies.keys.forEach { libraryKey ->

        val libs = AndroidX.dependencies[libraryKey]!!

        libs.forEach { add(libraryKey, it) }
    }

}

object LifeCycleKtx {

    private const val version = "2.4.0-alpha03"
    private const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
    private const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    private const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$version"

    internal val dependencies =
        mapOf(
            "implementation" to listOf(viewmodelKtx, runtimeKtx, livedataKtx)
        )
}

fun DependencyHandlerScope.withLifeCycleKtx() {

    LifeCycleKtx.dependencies.keys.forEach { libraryKey ->

        val libs = LifeCycleKtx.dependencies[libraryKey]!!

        libs.forEach { add(libraryKey, it) }
    }

}