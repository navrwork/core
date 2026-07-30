package com.navr.learn.basics.serializable;

import lombok.Data;

import java.io.Serializable;

/**
 * If a class implements Serializable without explicitly declaring serialVersionUID, the JVM automatically generates a default one at runtime.
 * If class structure changes, the generated serialVersionID will change, leading to InvalidClassException
 * during deserialization if the serialized object was created with a different version of the class.
 * <br/>
 * <br/>
 * <b>The serialVersionUID rule:</b>
 * <pre>
 *     serialVersionUID (at serialization) == serialVersionUID (at deserialization)
 *         ✓ Match              →  Success ✓
 *         ✗ Mismatch           →  InvalidClassException ✗
 * </pre>
 */
@Data
public class UserWithDefaultSerVerUID implements Serializable {
    private String name;
    private long id;

    public UserWithDefaultSerVerUID(String name, long id) {
        this.name = name;
        this.id = id;
    }

}
