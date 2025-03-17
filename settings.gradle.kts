pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
//        maven {
//            url to "https://jitpack.io"
//        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
//        maven {
//            url to "https://jitpack.io"
//        }
    }
}

rootProject.name = "ExcellentAndroid"
include(":app")
include(":ExcellentMvvm")
include(":common")
include(":Architecture")
