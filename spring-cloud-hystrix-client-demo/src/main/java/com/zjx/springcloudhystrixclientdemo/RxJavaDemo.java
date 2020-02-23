package com.zjx.springcloudhystrixclientdemo;

import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.schedulers.Schedulers;

import java.util.Random;

public class RxJavaDemo {

    public static void main(String[] args) {


        Single.just("hello world") // 发布数据
                .subscribeOn(Schedulers.immediate()) //订阅的线程池 immediate == Thread.currentThread();
                .subscribe(new Observer<String>(){
                    @Override
                    public void onCompleted() { // 正常结束
                        System.out.println("执行结束！");
                    }

                    @Override
                    public void onError(Throwable throwable) { // 异常流程(结束)
                        System.out.println("熔断保护！");
                    }

                    @Override
                    public void onNext(String s) { //数据消费
                        Random random = new Random();

                        int value = random.nextInt(200);
                        System.out.println(value);
                        if(value >   100){
                            throw new RuntimeException("TimeOut!");
                        }

                        System.out.println("hello world() cocts" + value + "ms");
                    }
                });
    }


}
