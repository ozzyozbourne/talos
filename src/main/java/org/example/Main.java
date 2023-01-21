package org.example;

import lombok.val;

import java.io.IOException;

import static org.example.framework.Constants.PATH_TEST_RC;


public class Main {
    public static void main(String[] args) throws IOException {

            simple message = simple.newBuilder().setId(10).setIsSimple(true).setName("qwe")
                    .addSampleLists(1).addSampleLists(2).addSampleLists(3).build();
            System.out.println(message);
            val path = PATH_TEST_RC + "grpcjsonout/simple.json";


    }


}