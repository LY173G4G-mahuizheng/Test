package com.example.demo.lambda;

public class simplelambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println("hello"));
    }
}
