package org.example.etster;

public class B extends A{

    @Override
    void defer(){
        System.out.println("In B");
    }

    void callAdefer(){
        super.defer();
    }
}
