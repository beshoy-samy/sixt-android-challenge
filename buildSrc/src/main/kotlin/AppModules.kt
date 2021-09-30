import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object AppModules {

    fun DependencyHandlerScope.withNetworkModule() =
        add("api", project(":network"))

    fun DependencyHandlerScope.withCoreModule() =
        add("implementation", project(":core"))

}