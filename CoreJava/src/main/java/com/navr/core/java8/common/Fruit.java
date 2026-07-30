package com.navr.core.java8.common;

import java.util.Objects;

public class Fruit {

    private String name;

    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Fruit(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }
    
    // Getters and setters
    
    @Override
    public String toString() {return name + "Object/" + amount;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return Objects.equals(name, fruit.name);
    }

    @Override
    public int hashCode() { return Objects.hash(name);}
}