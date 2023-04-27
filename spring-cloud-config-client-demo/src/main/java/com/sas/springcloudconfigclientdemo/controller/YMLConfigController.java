package com.sas.springcloudconfigclientdemo.controller;

import com.alibaba.fastjson.JSON;
import com.sas.springcloudconfigclientdemo.config.YMLConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class YMLConfigController {

    @Resource
    private YMLConfig config;


    @RequestMapping(value = "yml")
    public String showYml() {
        return JSON.toJSONString(config);
    }

}
