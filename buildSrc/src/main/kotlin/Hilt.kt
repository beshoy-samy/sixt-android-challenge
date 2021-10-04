import org.gradle.kotlin.dsl.DependencyHandlerScope

object Hilt {

    internal const val version = "2.38.1"

    internal const val hilt = "com.google.dagger:hilt-android:${version}"
    internal const val hiltCompiler = "com.google.dagger:hilt-compiler:${version}"
}

fun DependencyHandlerScope.withHilt() {
    add("implementation", Hilt.hilt)
    add("kapt", Hilt.hiltCompiler)
}

fun DependencyHandlerScope.withJavaInject() {
    add("implementation", "javax.inject:javax.inject:1")
}