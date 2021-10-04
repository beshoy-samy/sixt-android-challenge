import org.gradle.kotlin.dsl.DependencyHandlerScope

object Network {

    internal object Versions {
        const val retrofit = "2.9.0"
        const val moshi = "1.12.0"
        const val okhttp = "4.9.1"
    }

    internal const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val moshiConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    internal const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    private const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    private const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    private const val okhttpInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    internal val dependencies = mapOf(
        "implementation" to listOf(moshiConverter, okhttp, okhttpInterceptor),
        "api" to listOf(retrofit, moshi),
        "kapt" to listOf(moshiCodeGen)
    )

}

fun DependencyHandlerScope.withNetworkDependencies() {

    Network.dependencies.keys.forEach { libraryKey ->

        val libs = Network.dependencies[libraryKey]!!

        libs.forEach { add(libraryKey, it) }
    }

}

fun DependencyHandlerScope.withRetrofit() {
    add("implementation", Network.retrofit)
}

fun DependencyHandlerScope.withMoshi() {
    add("implementation", Network.moshi)

}