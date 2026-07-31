# Sealed Classes in Java

## What sealed classes are

Sealed classes are a Java language feature that lets you restrict which classes can extend a given class. In other words, the author of a base type can define a closed set of allowed subclasses.

A sealed class is declared with the `sealed` keyword and lists its permitted subclasses with `permits`.

```java
public sealed class Shape permits Circle, Rectangle, Triangle {
}

public final class Circle extends Shape {
}

public final class Rectangle extends Shape {
}

public final class Triangle extends Shape {
}
```

## Why they are needed

Sealed classes are useful when you want a hierarchy to be intentionally closed.

They help with:

- Better design control: you can clearly define the only valid subtypes.
- Safer code: unrelated classes cannot accidentally extend the base type.
- Easier maintenance: the allowed variants are visible in one place.
- Stronger compiler support: tools and the compiler can reason more precisely about the possible cases.

They are especially useful for modeling domain concepts such as types of payments, state machines, commands, or expressions.

## When they were introduced

Sealed classes were introduced as a preview feature in Java 15 and became a finalized feature in Java 17.

## Practical examples

### 1. Payment types

A payment system may allow only a small set of valid payment methods.

```java
public sealed interface PaymentMethod permits CardPayment, CashPayment, BankTransfer {
}
```

### 2. Expression trees

A simple calculator or parser can limit expressions to numbers, additions, and multiplications.

```java
public sealed abstract class Expr permits Number, Add, Multiply {
}
```

### 3. UI or workflow states

A workflow can only exist in a known set of states such as draft, in review, approved, or rejected.

```java
public sealed abstract class WorkflowState permits Draft, InReview, Approved, Rejected {
}
```

## Subclass rules

A sealed class or interface does not require its subclasses to be `final`. Instead, each direct subclass must be one of the following:

- `final`: it cannot be extended further
- `sealed`: it defines its own restricted set of permitted subclasses
- `non-sealed`: it opens the hierarchy again and allows normal extension outside the original sealed set

The direct subclasses must also be explicitly listed in the parent type's `permits` clause.

```java
public sealed class Shape permits Circle, Rectangle, Square {
}

public final class Circle extends Shape {
}

public final class Rectangle extends Shape {
}

public non-sealed class Square extends Shape {
}
```

In this example, `Circle` and `Rectangle` are closed, while `Square` is open for further extension.

A `non-sealed` class is effectively like a normal class for that branch of the hierarchy. It can be extended by other classes outside the original sealed set, so it removes the inheritance restriction for that specific class and its descendants.

## Summary

Sealed classes are a practical way to make inheritance more deliberate and safer. They are especially helpful when a hierarchy should be closed and when you want the compiler to help enforce that rule.
