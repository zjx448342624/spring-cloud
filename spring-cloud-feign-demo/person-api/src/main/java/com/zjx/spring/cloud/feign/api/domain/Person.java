package com.zjx.spring.cloud.feign.api.domain;

public class Person {

    private String name;

    private long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
