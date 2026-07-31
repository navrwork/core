# Pattern Matching with instanceof in Java

## What it is

Pattern matching with `instanceof` lets you check the type of an object and, at the same time, declare a variable of that specific type in one step.

Before Java 16, you often had to write code like this:

```java
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.toUpperCase());
}
```

With pattern matching, the same logic becomes shorter and clearer:

```java
if (obj instanceof String s) {
    System.out.println(s.toUpperCase());
}
```

## Why it is useful

This feature reduces boilerplate and makes code easier to read.

It helps by:

- avoiding repeated type casts
- making the type check and variable declaration more compact
- reducing the chance of mistakes when casting

## When it was introduced

Pattern matching for `instanceof` was introduced as a preview feature in Java 14 and became a standard feature in Java 16.

## Practical examples

### 1. Checking a string value

```java
Object value = "Hello";

if (value instanceof String text) {
    System.out.println(text.toUpperCase());
}
```

### 2. Working with different shapes

```java
Object shape = new Circle();

if (shape instanceof Circle circle) {
    circle.draw();
}
```

### 3. Using it in a method

```java
public static void printLength(Object obj) {
    if (obj instanceof String text) {
        System.out.println("Length: " + text.length());
    } else {
        System.out.println("Not a string");
    }
}
```

## Important note

The pattern variable is only available in the `if` block where the type check succeeds. Outside that scope, it is not available.

## Summary

Pattern matching with `instanceof` makes Java code shorter and safer by combining type checking and type casting into a single expression.
