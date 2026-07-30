# Java 8 Features
Java 8, released on March 18, 2014, introduced several significant features and enhancements to the Java programming language, JVM, tools, and libraries. These features aimed to improve code efficiency, readability, and performance.

## Lambda Expressions

Lambda expressions provide a clear and concise way to represent a method of a functional interface using an expression. They enable functional programming in Java and simplify the code by eliminating boilerplate code. For example:
```java
// Using Lambda Expression
Calculator sum = (a, b) -> a + b;
int result = sum.operate(2, 3);
System.out.println(result); // Output: 5
```
Lambda expressions are particularly useful in collection libraries for iterating, filtering, and extracting data.

## Method References

Method references are a shorthand notation of a lambda expression to call a method. They are used to refer to methods by their names and can be used for static methods, instance methods, and constructors. For example:
```java
// Using Method Reference
List<Integer> numbers = Arrays.asList(1, 2, 4, 9, 8, 7, 3);
numbers.stream().sorted(Integer::compareTo).forEach(System.out::println);
```
Method references improve code readability and reduce verbosity.

## Functional Interfaces

A functional interface is an interface that contains only one abstract method. It can have any number of default and static methods. Functional interfaces are also known as Single Abstract Method (SAM) interfaces. Examples include Runnable, Callable, and custom interfaces.

## Stream API

The Stream API allows functional-style operations on collections of objects. It supports operations like filtering, mapping, and reducing, and can be executed in parallel for better performance. For example:
```java
List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
long count = strings.stream().filter(string -> string.isEmpty()).count();
System.out.println("Empty Strings: " + count); // Output: 2
```
The Stream API enhances data manipulation and processing capabilities.

## Optional Class

The Optional class is used to handle null values safely and avoid NullPointerException. It provides methods to check the presence of a value and perform actions accordingly. For example:
```java
Optional<Integer> valueOptional = Optional.ofNullable(null);
Integer value = valueOptional.orElse(-1);
System.out.println(value); // Output: -1
```
The Optional class makes the code more robust and less error-prone.

## Date/Time API

Java 8 introduced a new Date and Time API under the java.time package. It provides classes like LocalDate, LocalTime, LocalDateTime, and ZonedDateTime for handling date and time operations. These classes are immutable and thread-safe. For example:
```java
LocalDateTime currentTime = LocalDateTime.now();
System.out.println("Current DateTime: " + currentTime);
```
The new Date/Time API simplifies date and time manipulation and improves code reliability.

## Default Methods

Default methods allow interfaces to have methods with implementations. This feature enables backward compatibility and allows existing interfaces to be extended without breaking existing implementations. For example:
```java
public interface Vehicle {
default void message() {
System.out.println("I am a vehicle!");
}
}
```
Default methods provide flexibility in interface design.

## Nashorn JavaScript Engine

Nashorn is a JavaScript engine introduced in Java 8 to execute JavaScript code dynamically on the JVM. It provides a command-line tool jjs to run JavaScript code and allows embedding JavaScript code within Java applications. For example:
```java
ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
ScriptEngine nashorn = scriptEngineManager.getEngineByName("nashorn");
nashorn.eval("print('Hello, World!')");
```
Nashorn enhances Java's capability to interact with JavaScript.

Java 8 brought many other enhancements, including improvements to the Collection API, Concurrency API, and security features. These features collectively make Java 8 a powerful and efficient tool for developers.
