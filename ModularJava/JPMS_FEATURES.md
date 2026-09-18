# JPMS Features Demonstration Guide

This ModularJava project demonstrates all major Java Platform Module System (JPMS) features in a practical, real-world example.

## Project Structure

```
ModularJava/
├── modules/
│   ├── moduleone/       (Service Interface Provider)
│   ├── moduletwo/       (Service Implementation Provider)
│   └── moduleapp/       (Service Consumer/Application)
├── build.gradle         (Root build configuration)
├── settings.gradle      (Module configuration)
└── README.md            (JPMS Overview)
```

## JPMS Features Demonstrated

### 1. Module Declaration (`module-info.java`)

Every module requires a `module-info.java` file at the root of `src/main/java/`:

```
modules/
└── moduleone/
    └── src/main/java/
        ├── module-info.java          ← Module declaration
        └── com/moduleone/
            ├── api/                   ← Public API (exported)
            └── internal/              ← Internal implementation (hidden)
```

---

## Feature 1: `exports` - Strong Encapsulation

**Location**: `modules/moduleone/src/main/java/module-info.java`

```java
module com.moduleone {
    exports com.moduleone.api;  // Only this package is visible
}
```

**Benefit**: Internal packages like `com.moduleone.internal` are completely hidden from other modules.

**Example**:
- ✅ `com.moduleone.api.TextTransformService` - Accessible from other modules
- ❌ `com.moduleone.internal.StringUtils` - NOT accessible (encapsulated)

---

## Feature 2: `requires` - Explicit Dependencies

**Location**: `modules/moduletwo/src/main/java/module-info.java`

```java
module com.moduletwo {
    requires com.moduleone;  // Explicit dependency
}
```

**Benefit**: 
- Clear module dependency graph
- Compile-time verification of dependencies
- Prevents "classpath hell"

**Access**: moduletwo can access all exported packages from moduleone.

---

## Feature 3: `requires transitive` - Re-exporting Dependencies

**Location**: `modules/moduleone/src/main/java/module-info.java`

```java
module com.moduleone {
    requires transitive java.logging;  // Re-export to dependents
}
```

**Benefit**: Modules depending on moduleone automatically get java.logging dependency.

**Example**: moduleapp depends on moduletwo → moduletwo depends on moduleone (transitive) → java.logging available to all.

---

## Feature 4: `uses` - Service Provider Interface Pattern

**Location**: `modules/moduleone/src/main/java/module-info.java`

```java
module com.moduleone {
    uses com.moduleone.api.TextTransformService;  // Declare service usage
}
```

**What it means**: "This module will discover and use implementations of TextTransformService via ServiceLoader"

---

## Feature 5: `provides` - Service Implementation (SPI)

**Location**: `modules/moduletwo/src/main/java/module-info.java`

```java
module com.moduletwo {
    provides com.moduleone.api.TextTransformService
        with com.moduletwo.api.UpperCaseTransformer,
             com.moduletwo.api.LowerCaseTransformer;
}
```

**What it does**: Registers implementations that can be discovered via ServiceLoader.

**Configuration file**: `modules/moduletwo/src/main/resources/META-INF/services/com.moduleone.api.TextTransformService`

```
com.moduletwo.api.UpperCaseTransformer
com.moduletwo.api.LowerCaseTransformer
```

---

## Feature 6: `opens` - Controlled Reflection Access

**Location**: `modules/moduleone/src/main/java/module-info.java`

```java
module com.moduleone {
    opens com.moduleone.internal to com.moduleapp;  // Allow reflection
}
```

**Purpose**: By default, JPMS prevents deep reflection on internal packages for security and strong encapsulation.

**Qualified opens**: Only `com.moduleapp` can use reflection on `com.moduleone.internal`.

**Use cases**:
- Testing frameworks that need reflection
- Dependency injection containers
- Serialization libraries

---

## Service Loader Pattern in Action

### Consumer: moduleapp

**File**: `modules/moduleapp/src/main/java/module-info.java`

```java
module com.moduleapp {
    requires com.moduleone;
    requires com.moduletwo;
    uses com.moduleone.api.TextTransformService;
}
```

**Usage Code**: `modules/moduleapp/src/main/java/com/moduleapp/main/Application.java`

```java
ServiceLoader<TextTransformService> loader = 
    ServiceLoader.load(TextTransformService.class);

loader.stream()
    .map(ServiceLoader.Provider::get)
    .forEach(service -> {
        System.out.println("Provider: " + service.getProviderName());
        System.out.println("Transformed: " + service.transform("Hello"));
    });
```

**Runtime Output**:
```
Found Service: UpperCaseTransformer
  Priority: 100
  Transformed: "HELLO WORLD"

Found Service: LowerCaseTransformer
  Priority: 50
  Transformed: "hello world"
```

---

## Module Dependencies Diagram

```
┌──────────────────────────────────────┐
│     moduleapp (Consumer)             │
│  - requires com.moduleone            │
│  - requires com.moduletwo            │
│  - uses TextTransformService         │
└──────────┬──────────────────────┬────┘
           │                      │
           ▼                      ▼
    ┌──────────────────┐  ┌──────────────────┐
    │ moduleone        │  │ moduletwo        │
    │ Service Interface│  │ Service Provider │
    │                  │  │                  │
    │ exports:         │  │ provides:        │
    │ -TextTransform   │◄─┤ -UpperCase...    │
    │  Service         │  │ -LowerCase...    │
    │ uses:            │  │                  │
    │ -TextTransform   │  │ requires:        │
    │  Service         │  │ -com.moduleone   │
    └──────────────────┘  └──────────────────┘
           ▲
           │ requires transitive
           │ java.logging
```

---

## JPMS Features Summary

| Feature | File Location | Example | Purpose |
|---------|---|---|---|
| **exports** | module-info.java | `exports com.moduleone.api;` | Make package public API |
| **requires** | module-info.java | `requires com.moduleone;` | Declare dependency |
| **requires transitive** | module-info.java | `requires transitive java.logging;` | Re-export dependency |
| **opens** | module-info.java | `opens com.moduleone.internal to com.moduleapp;` | Allow reflection access |
| **uses** | module-info.java | `uses TextTransformService;` | Declare service usage |
| **provides** | module-info.java | `provides Service with Implementation;` | Implement service interface |
| **Service Provider Config** | META-INF/services | `com.moduleone.api.TextTransformService` | Register implementations |

---

## Building and Testing

### Build all modules:
```bash
./gradlew build
```

### Run specific module tests:
```bash
./gradlew :modules:moduleone:test
./gradlew :modules:moduletwo:test
./gradlew :modules:moduleapp:test
```

### View project structure:
```bash
./gradlew projects
```

### Run the application:
```bash
./gradlew :modules:moduleapp:run
```

---

## Key JPMS Concepts Demonstrated

### 1. **Strong Encapsulation**
- Internal packages are completely hidden
- Only explicitly exported packages are visible
- Compile-time verification of visibility rules

### 2. **Explicit Dependencies**
- Module graph is clear and verifiable
- Circular dependencies are detected at startup
- No more classpath hell

### 3. **Service Provider Interface (SPI)**
- Loose coupling between service interface and implementation
- Implementations can be swapped without changing code
- ServiceLoader discovers implementations at runtime

### 4. **Reflection Control**
- `opens` allows fine-grained reflection access
- Security boundary between modules
- Only specified modules can reflect on internal classes

### 5. **Module Composition**
- moduleapp composes both moduleone and moduletwo
- Clear dependency hierarchy
- Reusable modular components

---

## Practical Benefits

✅ **Smaller Runtime**: Only load required modules
✅ **Better Security**: Control over what's accessible
✅ **Clear Architecture**: Module boundaries are explicit
✅ **Easier Testing**: Modules can be tested in isolation
✅ **Plugin Systems**: Service SPI enables plugin architectures
✅ **Version Management**: Better handling of multiple versions
✅ **Documentation**: Module structure is self-documenting

---

## Common Patterns

### Pattern 1: API Module
```java
module com.api {
    exports com.api.contracts;  // Only interfaces/contracts
}
```

### Pattern 2: Implementation Module
```java
module com.impl {
    requires com.api;
    provides com.api.contracts.Service with com.impl.ServiceImpl;
}
```

### Pattern 3: Aggregator Module
```java
module com.app {
    requires com.api;
    requires com.impl;
    uses com.api.contracts.Service;
}
```

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| "module not found" | Add `requires` directive in module-info.java |
| "package not visible" | Add `exports` in provider module |
| Reflection fails | Add `opens` directive for the package |
| ServiceLoader finds nothing | Create META-INF/services file with implementation class names |
| Circular dependency error | Refactor to break the cycle (use intermediate module) |

---

## Learning Resources

- [Official JPMS Tutorial](https://docs.oracle.com/javase/tutorial/modules/)
- [Project Jigsaw Documentation](https://openjdk.java.net/projects/jigsaw/)
- Examine `module-info.java` files in this project
- Run tests to see services in action

---

## Next Steps

1. **Add more service implementations** in moduletwo
2. **Create a plugin module** that provides additional services
3. **Experiment with qualified exports** to restrict access
4. **Test reflection restrictions** with opens directive
5. **Build a multi-layer application** with multiple modules

---

This project serves as a comprehensive reference for implementing production-grade modular Java applications using JPMS!
