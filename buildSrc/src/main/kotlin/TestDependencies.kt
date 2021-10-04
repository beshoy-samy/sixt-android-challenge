import org.gradle.kotlin.dsl.DependencyHandlerScope

object TestDependencies {

    internal object AndroidVersions {
        const val junit = "1.1.3"
        const val espresso = "3.4.0"
    }

    internal object Versions {
        const val junit = "4.13.2"
    }

    object Android {
        const val junit = "androidx.test.ext:junit:${AndroidVersions.junit}"
        const val espresso = "androidx.test.espresso:espresso-core:${AndroidVersions.espresso}"
    }

    private const val junit = "junit:junit:${Versions.junit}"
    private const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Coroutines.version}"
    private const val turbine = "app.cash.turbine:turbine:0.6.1"

    internal val testDependencies = listOf(junit, coroutines, turbine)

    internal val androidTestDependencies = listOf(Android.junit, Android.espresso)

    internal val dependencies = mapOf(
        "testImplementation" to testDependencies,
        "androidTestImplementation" to androidTestDependencies
    )
}

fun DependencyHandlerScope.withTestDependencies() {

    TestDependencies.dependencies.keys.forEach { libraryKey ->

        val libs = TestDependencies.dependencies[libraryKey]!!

        libs.forEach { add(libraryKey, it) }
    }

}

fun DependencyHandlerScope.withCoreTestDependencies() {

    TestDependencies.testDependencies.forEach { library ->
        add("testImplementation", library)
    }

}

fun DependencyHandlerScope.withAndroidTestDependencies() {

    TestDependencies.androidTestDependencies.forEach { library ->
        add("androidTestImplementation", library)
    }

}