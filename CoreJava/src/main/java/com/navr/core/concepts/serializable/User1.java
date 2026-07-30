package com.navr.core.concepts.serializable;

import lombok.Data;

import java.io.Serializable;

@Data
public class User1 implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;
    private String lastName;
}
