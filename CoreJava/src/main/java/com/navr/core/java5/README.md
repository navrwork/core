# Java 5 Features

Java 5 introduced several significant features, including generics, enhanced for-loops, variable arguments, and annotations, which greatly improved the language's usability and type safety.

# Key Features of Java 5

## Generics
Generics allow developers to define classes, interfaces, and methods with a placeholder for types, enabling compile-time type checking and eliminating the need for most type casts. This feature enhances type safety and code reusability by allowing collections to be defined with specific types. 

### WildCard
|     Wildcard Type | Adding Elements           | Reading Elements | Use Case                                                                                             | 
|------------------:|---------------------------|------------------|------------------------------------------------------------------------------------------------------|
|           List<?> | Not allowed (except null) | Read as Object   | Read-only for unknown types                                                                          |
| List<? extends T> | Not allowed (except null) | Read as T        | List<? extends T> lets you read as T but no write allowed. Read-only for subtypes of T.              |
|   List<? super T> | Can add T and subtypes    | Read as Object   | List<? super T> lets you write T but reading gives you Object. Write data of type T or its subtypes. |

### Generics Are Invariant
 * Java generics are invariant by default, meaning a generic type like List&lt;String&gt; is not considered a subtype of List&lt;Object&gt;, even though String is a subtype of Object. 
This strict behavior prevents type safety issues due to type erasure at runtime.
 * Invariant: Exact type match only—no sub/supertype relation (List&lt;Integer&gt; unrelated to List&lt;Number&gt;).

### Covariance vs. Contravariance 
 * Invariant: Exact type match only—no sub/supertype relation (List&lt;Integer&gt; unrelated to List&lt;Number&gt;).
 * Covariant (? extends T): Read-only, subtypes assignable (e.g., List&lt;? extends Number&gt; accepts List&lt;Integer&gt;).
 * Contravariant (? super T): Write-only, supertypes assignable (e.g., List&lt;? super Integer&gt; accepts List&lt;Number&gt;).

### PECS - "Producer Extends, Consumer Super"
 * Use the PECS mnemonic: "Producer Extends, Consumer Super" to remember Java generics wildcards.
 * Note: View producers/consumers from the collection's viewpoint, not yours. "Producer" means the collection produces (gives you) data via get(), so use ? extends. "Consumer" means the collection consumes (takes) your data via add(), so use ? super.
 * Producer (read-only): Collection "produces" items for you → use ? extends T. Safe to read as T, can't add arbitrary items.

## Enhanced For-Loop
Also known as the "for-each" loop, this feature simplifies the syntax for iterating over arrays and collections. It provides a cleaner and more readable way to traverse elements without the need for an explicit iterator. 


## Variable Arguments (Varargs)
This feature allows methods to accept a variable number of arguments, making it easier to create methods that can handle different numbers of parameters. It is declared using three dots (...) after the parameter type. 


## Annotations
Annotations provide a way to add metadata to Java code. They can be used to give information to the compiler or to be processed at runtime by frameworks and libraries. This feature supports better code documentation and can be used for various purposes, such as configuration and validation. 

## Static Imports
This feature allows the use of static members of a class without needing to qualify them with the class name. It simplifies code and improves readability, especially when using constants or utility methods. 

These features collectively enhance Java's capabilities, making it more powerful and easier to use for developers. They also align with modern programming practices, promoting cleaner and more maintainable code.
