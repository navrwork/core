package com.navr.learn.basics.serializable;

import java.io.*;

public class SerializeMain {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        testSerialize_Pass_On_Deserialize();
        testSerializeUser_Fail_On_Deserialize();
    }

    private static void testSerialize_Pass_On_Deserialize() throws IOException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        UserWithCustomSerVerUID user = new UserWithCustomSerVerUID();
        user.setName("aaa");
        System.out.println("testSerialize_Pass_On_Deserialize starts ..");
        SerializeHelper.serializeUser(user); // all good.
        SerializeHelper.deserializeUser(user); // all good.
    }

    private static void testSerializeUser_Fail_On_Deserialize() throws IOException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        UserWithCustomSerVerUID user = new UserWithCustomSerVerUID();
        user.setName("zzz");
        SerializeHelper.serializeUser(user); // all good.
        System.out.println("testSerializeUser_Fail_On_Deserialize starts ..");
        UserWithCustomSerVerUID.class.getDeclaredField("serialVersionUID").setLong(null, 1L); // Modify serialVersionUID
        try {
            SerializeHelper.deserializeUser(user); // This should fail due to serialVersionUID mismatch
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
