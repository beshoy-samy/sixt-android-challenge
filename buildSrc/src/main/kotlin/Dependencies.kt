import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.withCoroutinesCore() {
    add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.2")
}