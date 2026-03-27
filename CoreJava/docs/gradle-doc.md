# Gradle Build System

## How to Set up a Gradle Project
* If you don't have Gradle available on your system, you will not be able to generate the Gradle Wrapper or build the project using Gradle commands until it is installed.
* Download and install Gradle from https://gradle.org/install/ and add it to your system PATH.
* Once installed, run gradle wrapper in your project directory to generate the wrapper files.
* After that, you can use ./gradlew or gradlew.bat to build your project without needing a system-wide Gradle installation.


## How to Convert An Existing Java Project to Gradle
* To fully convert your Java project to a Gradle project, you need the following in addition to build.gradle:
    * Gradle Wrapper: This allows anyone to build the project without needing Gradle pre-installed. It consists of:
    * gradlew (Unix shell script)
    * gradlew.bat (Windows batch script)
    * gradle/wrapper/gradle-wrapper.jar
    * gradle/wrapper/gradle-wrapper.properties
* Standard Directory Structure: Make sure that your current structure matches with this : src/main/java, src/main/resources, src/test/java.
* (Optional) .gitignore: To ignore build and Gradle wrapper files in version control.


## Why Gradle Wrapper
* No Local Installation Required: Builds work on any machine by auto-downloading the needed Gradle version on first use.
* Version Standardization: Locks the project to one Gradle version, avoiding mismatches across IDEs, servers, or developers.
* Easy Setup: New developers can start building immediately without installing Gradle, improving onboarding and CI/CD integration.
* Consistency: Ensures all developers use the same Gradle version, reducing build issues and improving reliability.
* Cross-Platform: Provides scripts for both Unix and Windows, making it easy to use across different operating systems.
* Overall, the Gradle Wrapper enhances build reliability, consistency, and ease of use across development environments.


## Upgrading Gradle Version
* Option #1: to upgrade the Gradle version used by your project, you can follow these steps:
    1. Open the gradle/wrapper/gradle-wrapper.properties file in your project.
    2. Locate the distributionUrl property, which specifies the URL for the Gradle distribution.
    3. Update the version number in the URL to the desired Gradle version. For example, if you want to upgrade to Gradle 8.10, change it to:
       distributionUrl=https\://services.gradle.org/distributions/gradle-8.10-bin.zip
    4. Save the changes to the gradle-wrapper.properties file.
    5. Run the command ./gradlew wrapper (or gradlew.bat wrapper on Windows) in your terminal to regenerate the wrapper files with the new version.
    6. After this, you can use ./gradlew or gradlew.bat to build your project with the updated Gradle version.
* Option #2:
    1. Alternatively, you can use the command line to upgrade the Gradle version by running:
       ./gradlew wrapper --gradle-version 8.10
    2. This command will automatically update the gradle-wrapper.properties file and regenerate the wrapper files with the specified Gradle version.


## Gradle: Basic Build Commands
|                                                      Command | Description                                                                                                                                                                   | 
|-------------------------------------------------------------:|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ./gradle wrapper<br/> ./gradle wrapper --gradle-version 8.10 | Run one of these commands in your project directory. <br/> Files generated: gradlew.bat, gradlew, gradle/wrapper/gradle-wrapper.jar, gradle/wrapper/gradle-wrapper.properties |
|                                        ./gradlew clean build | Clean (removes prior outputs) first and then do a build.                                                                                                                      |
|                                              ./gradlew build | Compiles code, runs tests, and packages the application.                                                                                                                      |
|                                               ./gradlew test | Runs the test suite.                                                                                                                                                          |
|                                                ./gradlew run | Executes the main class of the application.                                                                                                                                   |
|                                                ./gradlew jar | Creates a JAR file from the compiled code.                                                                                                                                    |
|                                       ./gradlew dependencies | Displays the dependency tree for the project.                                                                                                                                 |
|                                      ./gradlew build -x test | Builds the project but skips running tests.                                                                                                                                   |
|                                    ./gradlew build --dry-run | Simulates the build process without executing tasks.                                                                                                                          |

## Gradle vs Maven Comparison

| Aspect              | Gradle                                      | Maven                                      |
|---------------------|---------------------------------------------|--------------------------------------------|
| Configuration      | Groovy/Kotlin DSL (build.gradle)           | XML (pom.xml)                             |
| Flexibility        | Highly flexible, customizable scripts      | Convention-over-configuration, less flexible |
| Performance        | Faster builds, incremental compilation     | Slower, full rebuilds often required      |
| Dependencies       | Declarative, supports dynamic versions     | Declarative, strict versioning            |
| Plugins            | Rich ecosystem, easy to write custom ones  | Extensive plugins, but less extensible    |
| Learning Curve     | Steeper, requires scripting knowledge      | Easier for beginners, XML-based           |
| Community/Support  | Growing, backed by Google/Android          | Mature, widely used in enterprise         |
| Use Case           | Complex builds, multi-language projects    | Standard Java projects, CI/CD pipelines   |