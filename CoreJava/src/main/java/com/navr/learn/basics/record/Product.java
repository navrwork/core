package com.navr.learn.basics.record;

import java.math.BigDecimal;

/**
 * Record classes are immutable, final data carriers with no inheritance support.
 * They eliminate the need for libraries like Lombok by auto-generating constructors, getters, and accessor methods.
 *
 * <pre>
 * What Records Provide:
 * Records automatically generate:
 * ✓ Constructor
 * ✓ Getters
 * ✓ equals() & hashCode()
 * ✓ toString()
 *
 * When to Use Records:
 * ✓ Simple data transfer objects
 * ✓ Immutable data holders
 * ✓ Request/Response DTOs
 *
 *  When NOT to Use Records:
 * ❌ Need mutability
 * ❌ Need complex inheritance
 * ❌ Legacy code (Java < 16)
 *
 * Verdict: For modern Java projects, records are the preferred way to create DTOs!
 * </pre>
 *
 * @param name
 * @param price
 * @Param type
 */
public record Product(String name,
                      BigDecimal price,
                      String type) {

    //
    // Add validation block
    //
    public Product {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Product price must be a positive value");
        }
    }

    // Note: Non-canonical record constructor must delegate to another constructor
    public Product(String name,
                   BigDecimal price) {
        this(name, price, "GENERAL");
    }
}
