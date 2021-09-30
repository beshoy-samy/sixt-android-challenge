import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object AppModules {

    fun DependencyHandlerScope.withNetworkModule() =
        add("api", project(":network"))

    fun DependencyHandlerScope.withCoreModule() =
        add("implementation", project(":core"))

}

object Cars {

    fun DependencyHandlerScope.withCarsModelModule() =
        add("implementation", project(":cars:cars-model"))

    fun DependencyHandlerScope.withCarsDomainModule() =
        add("implementation", project(":cars:cars-domain"))

}