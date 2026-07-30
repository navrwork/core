package com.navr.core.concepts.serializable;

import java.io.IOException;

public class User12Main {
    public static void main(String[] args) {
        try {
            serializeUser1AndSDeserializeUser2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Serialize User1 obj and try to deserialize as User2 instance.
     */
    private static void serializeUser1AndSDeserializeUser2() throws IOException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        User1 user1 = new User1();
        user1.setFirstName("John");
        user1.setLastName("Doe");
        SerializeHelper.serializeUser1(user1);
        SerializeHelper.deserializeUser2(); // ClassCastException: class User1 cannot be cast to class User2 ...
    }
}
