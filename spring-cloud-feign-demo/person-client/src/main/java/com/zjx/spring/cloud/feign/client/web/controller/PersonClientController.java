package com.zjx.spring.cloud.feign.client.web.controller;

import com.zjx.spring.cloud.feign.api.domain.Person;
import com.zjx.spring.cloud.feign.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


/**
 * {@link PersonClientController} 实现 {@PersonService }
 */
@RestController
public class PersonClientController {

    private final PersonService personService;

    @Autowired
    public PersonClientController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/person/save")
    public boolean save(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/person/all")
    public Collection<Person> findAll() {
        return personService.findAll();
    }
}
