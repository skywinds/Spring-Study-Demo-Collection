package com.sas.ss2.controller;

import com.alibaba.fastjson.JSON;
import com.sas.ss2.model.UserInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with IntelliJ IDEA.
 * Author: jinzhijie
 * Date: 2019/4/22
 * Time: 14:27
 * To change this template use File | Settings | File Templates.
 */
@Api(value = "swagger api接口测试", tags = "swagger api接口测试")
@RestController
@RequestMapping(value = "/api")
public class ApiController {

    /**
     * 注意，如果这里不加method，swagger ui里会把所有的类型都列出来；
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名字", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "content", value = "问候语", required = false, dataType = "string", paramType = "query")})
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ApiOperation(value = "获取人物信息", httpMethod = "GET", response = String.class)
    public String handleHi(String name, String content) {
        String result = "hi，" + name;
        if (StringUtils.isNotBlank(content)) {
            result += "," + content;
        }
        return result;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "string", paramType = "query")})
    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取人物信息2", httpMethod = "GET", response = UserInfo.class)
    public UserInfo handleGetUserInfo(String userId, String userName) {
        return new UserInfo("123", "jinzhijie");
    }
}
