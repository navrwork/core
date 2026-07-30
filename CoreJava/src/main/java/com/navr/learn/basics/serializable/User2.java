package com.navr.learn.basics.serializable;

import lombok.Data;

import java.io.Serializable;

@Data
public class User2 implements Serializable {
    private static final long serialVersionUID = 2L;
    private String firstName;
    private String lastName;
}
