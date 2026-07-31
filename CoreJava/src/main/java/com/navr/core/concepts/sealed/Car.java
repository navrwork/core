package com.navr.core.concepts.sealed;

public sealed class Car extends Vehicle permits Sedan, SUV {
}
