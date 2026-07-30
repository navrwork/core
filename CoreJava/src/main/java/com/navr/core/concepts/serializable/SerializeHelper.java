package com.navr.core.concepts.serializable;

import java.io.*;

public class SerializeHelper {
    public static void serializeUser(UserWithCustomSerVerUID userObj) throws IOException, NoSuchFieldException, IllegalAccessException {
        long serVerUID = ObjectStreamClass.lookup(UserWithDefaultSerVerUID.class).getSerialVersionUID();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"));
        oos.writeObject(userObj);
        oos.close();
        System.out.printf("serializeUser: serialVersionUID=%d, user=%s\n", serVerUID, userObj);
    }

    public static void deserializeUser(UserWithCustomSerVerUID user) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"));
        user = (UserWithCustomSerVerUID) ois.readObject(); // serialVersionUID check done here.
        ois.close();
        System.out.printf("deserializeUser: Deserialized user=%s%n", user);
    }

    public static void serializeUser1(User1 userObj) throws IOException, NoSuchFieldException, IllegalAccessException {
        long serVerUID = ObjectStreamClass.lookup(UserWithDefaultSerVerUID.class).getSerialVersionUID();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.ser"));
        oos.writeObject(userObj);
        oos.close();
        System.out.printf("serializeUser1: completed. serialVersionUID=%d, user=%s\n", serVerUID, userObj);
    }

    public static void deserializeUser1() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"));
        User1 user = (User1) ois.readObject(); // serialVersionUID check done here.
        ois.close();
        System.out.printf("deserializeUser1: completed. Deserialized user=%s%n", user);
    }

    public static void deserializeUser2() throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.ser"));
        User2 user = (User2) ois.readObject(); // serialVersionUID check done here.
        ois.close();
        System.out.printf("deserializeUser2: completed. Deserialized user=%s%n", user);
    }
}
