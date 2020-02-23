package com.zjx.springcloudsleuthdemo.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DemoController {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    private final RestTemplate restTemplate;

    @Autowired
    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/helloWorld")
    public String index(){
        String returnValue = "hellom,world";
        logger.info("{} index() : {}" , getClass().getSimpleName(),returnValue);
        return returnValue;
    }



    /**
     *  完整的调用链路：
     *
     *   spring-cloud-sleuth
     *   -> zuul
     *   -> person-client
     *   -> person-server
     * @return
     */
    @GetMapping("/to/zuul/person-client/person/all")
    public Object toZuul(){
        logger.info("spring-cloud-sleuth#toZuul()");
        String url= "http://spring-cloud-zuul/person-client/person/all";
        return restTemplate.getForObject(url,Object.class);
    }

}
