package com.zjx.springcloudhystrixclientdemo.controller;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class HystrixDemoController {

    private final static Random random = new Random();


    /**
     * 当{@link #helloWorld()} 方法调用超时或失败时，
     * fallback 方法{@link #errorContent()} 作为替代返回
     * @return
     */
    @GetMapping("/hello-world")
    @HystrixCommand(
            fallbackMethod = "errorContent",
            commandProperties = {
                    @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "100")
            }
    )
    public String helloWorld() throws  Exception{

        int value = random.nextInt(200);

        System.out.println("hello world() cocts" + value + "ms");

        Thread.sleep(value);

        return "Hello World";
    }


    private class HelloWorldCommand extends com.netflix.hystrix.HystrixCommand<String> {

        protected HelloWorldCommand(){
            super(HystrixCommandGroupKey.Factory.asKey("HelloWorld"),
                    100);
        }

        @Override
        protected String run() throws Exception {
            int value = random.nextInt(200);

            System.out.println("hello world() cocts" + value + "ms");

            Thread.sleep(value);

            return "Hello World";
        }

        protected String getFallback(){
            return HystrixDemoController.this.errorContent();
        }

    }

    /**
     * 当{@link #helloWorld2()} 方法调用超时或失败时，
     * fallback 方法{@link #errorContent()} 作为替代返回
     * @return
     */
    @GetMapping("/hello-world2")
    public String helloWorld2() throws  Exception{
        return new HelloWorldCommand().execute();
    }

    public String errorContent(){
        return "Faulst";
    }

}
