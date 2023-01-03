package org.example;

import lombok.val;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        val component = DaggerCarComponent.create();
        val car = component.getCar();
        System.out.println(car);
    }
}