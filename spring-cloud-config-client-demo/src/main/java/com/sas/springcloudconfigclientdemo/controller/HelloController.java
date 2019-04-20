package com.sas.springcloudconfigclientdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * User: jinzhijie
 * Date: 2019/4/20
 * Time: 15:46
 * To change this template use File | Settings | File Templates.
 */
@RestController
public class HelloController {

    /*@Value("${spring.dataSource.name}")
    private String name;*/

    @Value("${test}")
    private String name;

//    @Resource
//    private CellInfo cellInfo;

    @RequestMapping(value = "hi")
    public String handleHello() {
        return "hello" + name;
    }
}
