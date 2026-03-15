# Core Java - Programs

## Gradle Build System

### How to Set up a Gradle Project
 * If you don't have Gradle available on your system, you will not be able to generate the Gradle Wrapper or build the project using Gradle commands until it is installed.
 * Download and install Gradle from https://gradle.org/install/ and add it to your system PATH.
 * Once installed, run gradle wrapper in your project directory to generate the wrapper files.
 * After that, you can use ./gradlew or gradlew.bat to build your project without needing a system-wide Gradle installation.


### How to Convert An Existing Java Project to Gradle
 * To fully convert your CoreJava project to a Gradle project, you need the following in addition to build.gradle:
   * Gradle Wrapper: This allows anyone to build the project without needing Gradle pre-installed. It consists of:
   * gradlew (Unix shell script)
   * gradlew.bat (Windows batch script)
   * gradle/wrapper/gradle-wrapper.jar
   * gradle/wrapper/gradle-wrapper.properties
 * Standard Directory Structure: Make sure that your current structure matches with this : src/main/java, src/main/resources, src/test/java.
 * (Optional) .gitignore: To ignore build and Gradle wrapper files in version control.

## Java LTS Features
 * [java5/README.md](src/main/java/com/navr/learn/java5/README.md)
 * [java8/README.md](src/main/java/com/navr/learn/java8/README.md)
 * [java11/README.md](src/main/java/com/navr/learn/java11/README.md)
 * [java17/README.md](src/main/java/com/navr/learn/java17/README.md)
 * [java21/README.md](src/main/java/com/navr/learn/java21/README.md)
 