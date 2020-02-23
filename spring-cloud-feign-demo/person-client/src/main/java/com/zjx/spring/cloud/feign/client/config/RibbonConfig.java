package com.zjx.spring.cloud.feign.client.config;

import com.zjx.spring.cloud.feign.client.ribbon.FirstServerForverRule;

public class RibbonConfig {
    public FirstServerForverRule getFirstServerForverRule(){
        return new FirstServerForverRule();
    }
}
