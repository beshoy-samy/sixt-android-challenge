import org.gradle.kotlin.dsl.DependencyHandlerScope

object Coroutines {

    const val version = "1.5.2"

    fun DependencyHandlerScope.withCoroutinesCore() {
        add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core:${version}")
    }

}

fun DependencyHandlerScope.withCoil() =
    add("implementation", "io.coil-kt:coil:1.3.2")