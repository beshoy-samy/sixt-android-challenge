import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.withCoroutinesCore() {
    add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}

fun DependencyHandlerScope.withCoil() =
    add("implementation", "io.coil-kt:coil:1.3.2")