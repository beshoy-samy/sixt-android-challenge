import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object AppModules {

    fun DependencyHandlerScope.withNetworkModule() =
        add("api", project(":network"))

}

object Cars {

    fun DependencyHandlerScope.withCarsModelModule() =
        add("implementation", project(":cars:cars-model"))

    fun DependencyHandlerScope.withCarsDomainModule() =
        add("implementation", project(":cars:cars-domain"))

}