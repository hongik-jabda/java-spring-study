package org.example;

public class Main {
    public static void main(String[] args) {
        String c = new String("hello");
        String d = new String("hello");
        System.out.println(c == d); // false
        System.out.println(c.equals(d)); // true, 왜냐면 두 객체의 내용이 같아서

        System.out.println("Hello world!");
    }
}