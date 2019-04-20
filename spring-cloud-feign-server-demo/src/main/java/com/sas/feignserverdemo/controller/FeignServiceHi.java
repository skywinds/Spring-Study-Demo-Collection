package com.sas.feignserverdemo.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: jinzhijie
 * Date: 2019/4/19
 * Time: 22:01
 * To change this template use File | Settings | File Templates.
 */

@FeignClient(value = "cellInfo-server",path = "/api/cellInfo")
public interface FeignServiceHi {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHi();
}
