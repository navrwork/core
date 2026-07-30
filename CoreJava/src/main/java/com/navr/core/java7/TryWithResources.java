package com.navr.core.java7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResources {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
            System.out.println("Inside try-with-resources block ...");
        } catch (IOException ioEx) {
            System.err.println("TryWithResources: IOException ioEx=" + ioEx.getMessage());
        }
    }
}
