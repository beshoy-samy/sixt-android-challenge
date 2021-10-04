dependencyResolutionManagement {

    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)

    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "SixtChallenge"

include(":app")
include(":network")
include(":core")

include(":cars:cars-domain")
include(":cars:cars-model")
include(":cars:cars-presentation")