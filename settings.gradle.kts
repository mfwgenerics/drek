rootProject.name = "drek"

include(
    ":frontend",
    ":server"
)

dependencyResolutionManagement {
    repositories {
        mavenCentral()
        maven("https://elide-snapshots.storage-download.googleapis.com/repository/v3/")
    }

    versionCatalogs {
        create("libs") {
            from(files("./gradle/elide.versions.toml"))
        }
    }
}
