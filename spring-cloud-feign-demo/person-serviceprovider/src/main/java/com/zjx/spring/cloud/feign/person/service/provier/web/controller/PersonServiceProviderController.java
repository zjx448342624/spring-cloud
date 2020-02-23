package com.zjx.spring.cloud.feign.person.service.provier.web.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zjx.spring.cloud.feign.api.domain.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {@link com.zjx.spring.cloud.feign.api.service.PersonService } 提供者控制器
 */
@RestController
public class PersonServiceProviderController {

    private Map<Long,Person> persons = new ConcurrentHashMap<Long,Person>();

    /**
     *  保存
     * @param person {@Link Person}
     * @return 成功返回<code>true</code>
     */
    @PostMapping("/person/save")
    public boolean save(@RequestBody Person person){
        return persons.put(person.getId(),person) == null;
    }

    private final static Random random = new Random();

    /**
     *  查找所有人员
     * @return 返回所有人员
     */
    @GetMapping("/person/find/all")
    @HystrixCommand(fallbackMethod = "fallBackFindAllPerson",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "100")
    })
    public Collection<Person> findAll() throws InterruptedException {
        int value = random.nextInt(200);

        System.out.println("hello world() cocts" + value + "ms");

        Thread.sleep(value);

        return persons.values();
    }


    public Collection<Person> fallBackFindAllPerson(){
        System.out.println("fallBackFindAllPerson is invoke");
        return Collections.emptyList();
    }

}
