package com.sas.feignserverdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: jinzhijie
 * Date: 2019/4/19
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class ServiceHiController {

    @Autowired
    private FeignServiceHi feignServiceHi;

    /** 调用feign声明的服务 */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(){
        return   feignServiceHi.sayHi();
    }
}
