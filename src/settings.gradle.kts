pluginManagement {
    repositories {
        google()  // Подключение репозитория Google
        mavenCentral()  // Центральный репозиторий Maven
        gradlePluginPortal()  // Портал плагины Gradle
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()  // Репозиторий Google
        mavenCentral()  // Центральные репозитории Maven
    }
}

rootProject.name = "BinaryInfoChecker"  // Имя корневого проекта

include(":app")  // Подключение модуля 'app'

// Дополнительно можно подключить другие модули, если проект большой:
// include(":core")
// include(":features:featureOne")
// include(":features:featureTwo")