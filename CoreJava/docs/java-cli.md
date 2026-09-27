# Java command-line basics

Run these examples from the `CoreJava` project directory in Git Bash. They use
`src/main/java/com/navr/core/java11/SingleFileProgram.java`, whose fully qualified class name is
`com.navr.core.java11.SingleFileProgram`. The commands use forward-slash paths and standard shell
syntax supported by Git Bash on Windows.

## Compile and run a Java class

`javac` is the Java compiler. It accepts the source-file path and compiles it into a `.class` file.
The `-d` option sets the destination directory for generated class files; here, `out` is that directory.
The compiler creates package subdirectories under the destination as needed.

```bash
javac -d out src/main/java/com/navr/core/java11/SingleFileProgram.java
```

`java` starts the JVM. The `-cp` option (short for `--class-path`) tells it where to find compiled
classes; `out` is the classpath entry in this example. The final argument is the class to run,
written as its fully qualified name (package plus class name), without `.class` or `.java`.

```bash
java -cp out com.navr.core.java11.SingleFileProgram
```

## Run a single source file

Java 11 and later can compile and run a source file directly, without a separate `javac` step.
In source-file launch mode, the path ending in `.java` identifies the source to compile and run.

```bash
java src/main/java/com/navr/core/java11/SingleFileProgram.java
```

Pass any program arguments after the source-file path; they are received by `main(String[] args)`.

## Compile for a different Java release

The `--release <version>` option tells `javac` to enforce the specified Java release's language rules
and Java SE API, and to generate class files compatible with that release. Replace `11` below with
the target release. The `-d out` option still selects where the generated class files go.

```bash
javac --release 11 -d out src/main/java/com/navr/core/java11/SingleFileProgram.java
```

Run the resulting class using the normal classpath command above. The compiler must support the selected release; for example, JDK 21 can compile with `--release 11`.

## Check a compiled class-file version

`javap` is the JDK class-file disassembler. Its `-verbose` option displays detailed class metadata,
including the class-file `major version`. Run it against the `.class` file:

```bash
javap -verbose out/com/navr/core/java11/SingleFileProgram.class | grep "major version"
```

Common major versions include 52 for Java 8, 55 for Java 11, 61 for Java 17, and 65 for Java 21.
This is the version the class file targets; it may differ from the JDK version used to run `javap`.

## Create, inspect, and run a JAR

After compiling classes into `out`, use the JDK `jar` tool to package them into an executable JAR:

```bash
jar --create --file app.jar --main-class com.navr.core.java11.SingleFileProgram -C out .
```

`--create` makes a new archive, `--file` names it, and `--main-class` sets the application's entry
point in the manifest. `-C out .` switches to the `out` directory and adds its contents, preserving
the package directory structure. Run the JAR with:

```bash
java -jar app.jar
```

List the files in the archive:

```bash
jar --list --file app.jar
```

Check whether a particular class is present by matching its path inside the JAR:

```bash
jar --list --file app.jar | grep -Fx 'com/navr/core/java11/SingleFileProgram.class'
```

The class is present if the command prints its path (and exits with status `0`); no output means it
was not found. `grep -F` treats the path as a literal string, and `-x` requires the entire JAR entry
to match.

Extract the archive into the current directory:

```bash
jar --extract --file app.jar
```

## Other useful JDK commands

Check which Java runtime and compiler are on `PATH`:

```bash
java -version
javac -version
```

`jshell` starts the interactive Java REPL, useful for trying expressions and small snippets without
creating a source file:

```bash
jshell
```