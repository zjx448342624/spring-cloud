package com.zjx.springcloudhystrixclientdemo;

import java.util.Random;
import java.util.concurrent.*;

public class FutureDemo {



    public static void main(String[] args) {
        Random random = new Random();
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
            for(int i = 0 ; i < 10 ; i++){

                Future<String> submit = service.submit(() -> {

                    int value = random.nextInt(200);

                    System.out.println("hello world() cocts" + value + "ms");

                    Thread.sleep(value);

                    return "Hello World";

                });


                    submit.get(100, TimeUnit.MILLISECONDS);
            }
        } catch (Exception e) {
            System.out.println("超时保护");
        }

        service.shutdown();

    }

}
