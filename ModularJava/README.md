# ModularJava - Java Platform Module System Guide

## Overview

This project demonstrates the **Java Platform Module System (JPMS)**, also known as **Project Jigsaw**, introduced in Java 9. JPMS provides a way to organize code into modules with explicit dependencies and controlled visibility, improving code organization, maintainability, and performance.

## What is JPMS?

The Java Platform Module System is a major feature that enables you to partition applications into smaller, loosely coupled components called **modules**. Each module explicitly declares its dependencies and exports only the public APIs it intends to share.

### Key Concepts

#### 1. **Modules**
A module is a self-describing collection of code and data with an explicit set of dependencies on other modules and an explicit list of the packages it makes available to other modules.

#### 2. **module-info.java**
Every module must have a `module-info.java` file at its root describing:
- The module name
- Packages it exports
- Modules it requires
- Services it provides/consumes

```java
module com.moduleone {
    // Requires other modules
    // requires java.base; (implicit, always included)
    
    // Exports public packages
    // exports com.moduleone.api;
    
    // Opens packages for reflection (if needed)
    // opens com.moduleone.internal;
}
```

#### 3. **Module Path**
Similar to the classpath, but instead of individual JAR files, the module path contains module directories or modular JAR files.

#### 4. **Exports and Requires**
- **exports**: Makes a package available to other modules
- **requires**: Declares a dependency on another module
- **requires transitive**: Re-exports dependencies (for APIs that expose types from dependencies)
- **opens**: Allows runtime reflection on specific packages

## Benefits of JPMS

✅ **Strong Encapsulation**: Only explicitly exported packages are visible to other modules
✅ **Clear Dependencies**: Module dependencies are explicit and verifiable at compile-time
✅ **Reduced Classpath**: Smaller runtime footprint by including only necessary modules
✅ **Version Conflicts**: Reduces jar hell by allowing better dependency management
✅ **Performance**: Faster startup and reduced memory footprint
✅ **Security**: Better control over what code is accessible to other modules

## Setting Up JPMS in Your Project

### Project Structure

```
ModularJava/
├── settings.gradle
├── build.gradle
├── modules/
│   ├── moduleone/
│   │   ├── build.gradle
│   │   ├── src/
│   │   │   ├── main/java/
│   │   │   │   ├── com/moduleone/
│   │   │   │   │   ├── api/
│   │   │   │   │   │   └── PublicAPI.java
│   │   │   │   │   ├── internal/
│   │   │   │   │   │   └── InternalClass.java
│   │   │   │   └── module-info.java
│   │   │   └── test/java/
│   │   └── ...
│   └── moduletwo/
│       ├── build.gradle
│       ├── src/
│       │   ├── main/java/
│       │   │   ├── com/moduletwo/
│       │   │   └── module-info.java
│       │   └── test/java/
│       └── ...
└── ...
```

### 1. Create module-info.java

For each module, create `src/main/java/module-info.java`:

```java
module com.moduleone {
    // Re-export transitive dependencies
    requires transitive java.logging;
    
    // Require other project modules
    requires com.moduletwo;
    
    // Export public APIs
    exports com.moduleone.api;
    
    // Allow reflection on specific packages (use cautiously)
    opens com.moduleone.internal to com.moduletwo;
}
```

### 2. Configure Gradle for JPMS

Update your `build.gradle`:

```gradle
plugins {
    id 'java'
}

java {
    sourceCompatibility = JavaVersion.VERSION_11  // Minimum Java 9
    targetCompatibility = JavaVersion.VERSION_11
}

compileJava {
    // Ensure module-info.java is compiled
    options.compilerArgs.add('--enable-preview')
}

jar {
    // Ensure module-info.class is included in JAR
    manifest {
        attributes 'Automatic-Module-Name': moduleName
    }
}
```

### 3. Organize Packages

- **Exported packages**: Public API interfaces and classes (e.g., `com.moduleone.api`)
- **Internal packages**: Not exported, hidden from other modules (e.g., `com.moduleone.internal`)

```java
// In com.moduleone.api.PublicAPI - can be accessed by other modules
public class PublicAPI {
    public void publicMethod() {}
}

// In com.moduleone.internal.InternalHelper - NOT accessible to other modules
class InternalHelper {
    void helperMethod() {}
}
```

## Example: Multi-Module Project

### Module A (com.app.core)

**module-info.java:**
```java
module com.app.core {
    exports com.app.core.api;
}
```

**com.app.core.api.CoreAPI.java:**
```java
public class CoreAPI {
    public static String getVersion() {
        return "1.0";
    }
}
```

### Module B (com.app.service)

**module-info.java:**
```java
module com.app.service {
    requires com.app.core;
    exports com.app.service.api;
}
```

**com.app.service.api.ServiceAPI.java:**
```java
import com.app.core.api.CoreAPI;

public class ServiceAPI {
    public String getServiceInfo() {
        return "Service version: " + CoreAPI.getVersion();
    }
}
```

### Module C (com.app.main)

**module-info.java:**
```java
module com.app.main {
    requires com.app.service;
    requires com.app.core;
}
```

**com.app.main.Main.java:**
```java
import com.app.service.api.ServiceAPI;
import com.app.core.api.CoreAPI;

public class Main {
    public static void main(String[] args) {
        System.out.println(new ServiceAPI().getServiceInfo());
        System.out.println("Core version: " + CoreAPI.getVersion());
    }
}
```

## Module Declaration Directives

### Requires
```java
requires java.base;              // Unconditional, static dependency
requires transitive java.logging; // Re-export to dependents
requires static org.junit;        // Optional compile-time only
```

### Exports
```java
exports com.example.api;                    // To all modules
exports com.example.api to com.other.module; // To specific modules only (qualified export)
```

### Opens
```java
opens com.example.internal;                 // Allow deep reflection to all modules
opens com.example.internal to com.test;     // Allow deep reflection to specific modules
```

### Uses and Provides
For service-based architecture:
```java
uses com.example.spi.MyService;           // This module uses a service
provides com.example.spi.MyService with com.example.impl.MyServiceImpl; // Provides implementation
```

## Build and Run

### With Gradle Wrapper

```bash
# Build the project
./gradlew build

# Run a specific module
./gradlew :modules:moduleone:run

# View module dependencies
./gradlew projects
```

### With Command Line

```bash
# Compile with module path
javac --module-path out/production/modules \
      -d out/modules \
      --module-source-path src \
      $(find src -name "*.java")

# Run with module path
java --module-path out/modules \
     -m com.app.main/com.app.main.Main
```

## Best Practices

1. **One Module Per Logical Component**: Each module should represent a cohesive functional unit
2. **Minimize Exports**: Only export what's necessary for the public API
3. **Use Internal Packages**: Hide implementation details in non-exported packages
4. **Explicit Dependencies**: Declare all module dependencies explicitly
5. **Avoid Circular Dependencies**: Design modules to have a clear dependency hierarchy
6. **Version Compatibility**: Keep modules compatible with Java 9+ (requires `module-info.java`)
7. **Test Modules Separately**: Test each module in isolation before integration testing
8. **Document APIs**: Clearly document exported packages and their intended use
9. **Use Qualified Exports**: For internal APIs, use qualified exports to specific modules
10. **Consider Service Provider Interface (SPI)**: For plugin architectures, use services

## Automatic Modules

If a JAR on the module path lacks a `module-info.class`, it becomes an **automatic module** with:
- Module name derived from JAR filename
- All packages exported
- Full access to other modules

```gradle
dependencies {
    implementation 'org.example:legacy-lib:1.0'  // Becomes automatic module
}
```

## Module Resolution

The module system performs resolution at startup:
- **Compile-time**: Checks module dependencies during compilation
- **Runtime**: Resolves module graph, fails if dependencies missing or circular

```
Missing module error:
Error: Module java.base not found, required by com.app.core

Circular dependency error:
Error: Circular dependency: com.app.a -> com.app.b -> com.app.a
```

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `module-info.java not found` | Ensure it's in `src/main/java/` root |
| `package not visible` | Add `exports` directive in source module |
| `cannot find symbol` from other module | Check `requires` in module-info.java |
| Circular dependencies | Refactor to break cycles, use services instead |
| Reflection fails | Use `opens` directive for target packages |
| Automatic modules | Add `module-info.java` to the JAR |

## Additional Resources

- [Java Module System Documentation](https://docs.oracle.com/javase/tutorial/modules/)
- [Project Jigsaw: Module System](https://openjdk.java.net/projects/jigsaw/)
- [Java 9+ Release Notes](https://www.oracle.com/java/technologies/javase-jdk9-doc.html)
- [Gradle Java Module Support](https://docs.gradle.org/current/userguide/java_module_plugin.html)

## License

This project is provided as an educational resource for learning JPMS.
