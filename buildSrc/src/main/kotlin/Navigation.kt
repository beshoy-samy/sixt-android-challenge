import org.gradle.kotlin.dsl.DependencyHandlerScope

object Navigation {

    private const val version = "2.3.5"

    private const val navigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
    private const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:$version"

    internal val dependencies =
        mapOf(
            "implementation" to listOf(navigationFragmentKtx, navigationUiKtx)
        )
}

fun DependencyHandlerScope.withNavigationComponent() {

    Navigation.dependencies.keys.forEach { libraryKey ->

        val libs = Navigation.dependencies[libraryKey]!!

        libs.forEach { add(libraryKey, it) }
    }

}