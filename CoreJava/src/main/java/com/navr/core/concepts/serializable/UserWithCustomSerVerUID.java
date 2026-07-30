package com.navr.core.concepts.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * A serializable class with a custom serialVersionUID.
 */
@Data
public class UserWithCustomSerVerUID implements Serializable {
    public static long serialVersionUID = 2026L;
    private String name;
    private transient long id;
}
