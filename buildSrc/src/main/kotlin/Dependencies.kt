import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.withCoil() =
    add("implementation", "io.coil-kt:coil:1.3.2")