# New Features in Java 17
Java 17, released on September 15, 2021, is the latest long-term support (LTS) release for the Java SE platform. This version introduces several new features and improvements that enhance the language's capabilities and performance.

## Key Features

### Restore Always-Strict Floating-Point Semantics (JEP 306)

This feature ensures that floating-point operations are consistently strict, providing the same results across all platforms. This change eliminates the need for the strictfp keyword, which was previously required to enforce strict floating-point behavior.

### Enhanced Pseudo-Random Number Generators (JEP 356)

Java 17 introduces new interfaces and implementations for pseudo-random number generators (PRNGs). These enhancements make it easier to use different algorithms interchangeably and support stream-based programming. For example:
```java
public IntStream getPseudoInts(String algorithm, int streamSize) {
return RandomGeneratorFactory.of(algorithm)
.create()
.ints(streamSize, 0, 100);
}
```

### New macOS Rendering Pipeline (JEP 382)

This JEP implements a new Java 2D internal rendering pipeline for macOS, using the Apple Metal API instead of the deprecated OpenGL API. This change improves the performance and compatibility of Java applications on macOS.

### Pattern Matching for Switch (Preview) (JEP 406)

Pattern matching for switch expressions and statements enhances the expressiveness of the language and reduces boilerplate code. This feature allows patterns to appear in case labels, making switch statements more powerful. For example:
```java
public String checkObject(Object obj) {
return switch (obj) {
case Human h -> "Name: %s, age: %s and profession: %s".formatted(h.name(), h.age(), h.profession());
case Circle c -> "This is a circle";
case Shape s -> "It is just a shape";
case null -> "It is null";
default -> "It is an object";
};
}
```

### Sealed Classes (JEP 409)

Sealed classes restrict which other classes or interfaces may extend or implement them. This feature improves the maintainability and security of the code by controlling the inheritance hierarchy. For example:

```java
public abstract sealed class Animal permits Dog, Cat, Rabbit {}
```

### Foreign Function and Memory API (Incubator) (JEP 412)

This API allows Java developers to access code and manage memory outside the JVM, replacing the JNI API. It improves security and performance by providing a more efficient way to interact with native libraries.

### Vector API (Second Incubator) (JEP 414)

The Vector API enables developers to perform vector computations that leverage specialized CPU hardware for parallel processing. This feature is useful for scientific applications, image processing, and other performance-critical tasks. For example:
```java
public void newVectorComputation(float[] a, float[] b, float[] c) {
for (var i = 0; i < a.length; i += SPECIES.length()) {
var m = SPECIES.indexInRange(i, a.length);
var va = FloatVector.fromArray(SPECIES, a, i, m);
var vb = FloatVector.fromArray(SPECIES, b, i, m);
var vc = va.mul(vb);
vc.intoArray(c, i, m);
}
}
```

### Conclusion

Java 17 brings several new features and improvements that enhance the language's capabilities, performance, and security. These changes make Java more powerful and efficient for developers, ensuring it remains a popular choice for enterprise applications
