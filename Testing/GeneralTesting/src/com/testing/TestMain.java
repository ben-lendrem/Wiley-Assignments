package com.testing;

import com.exceptions.CustomException;

public class TestMain {
    public static void main(String[] args) {
        try {
            ThrowingMethod();
        } catch (CustomException e) {
            System.out.println("We got one!!!");
        }
    }


    private static void ThrowingMethod() throws RuntimeException {
        try {
            int x = 6 / 1;
            System.out.println("Made it past the exception");
        } catch (ArithmeticException e) {
            e.printStackTrace();
            throw new CustomException(e.getMessage(), e);
        }
        System.out.println("Past the exception");
    }
}
