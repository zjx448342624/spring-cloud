package com.zjx.spring.cloud.feign.api.service;

import com.zjx.spring.cloud.feign.api.domain.Person;
import com.zjx.spring.cloud.feign.api.hystrix.PersonServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

/**
 * Person 服务
 */
@FeignClient(value= "person-service",fallback = PersonServiceFallback.class) // 服务提供的应用名称
public interface PersonService {

    /**
     *  保存
     * @param person {@Link Person}
     * @return 成功返回<code>true</code>
     */
    @PostMapping("/person/save")
    boolean save(Person person);


    /**
     *  查找所有人员
     * @return 返回所有人员
     */
    @GetMapping("/person/find/all")
    Collection<Person> findAll();

}
