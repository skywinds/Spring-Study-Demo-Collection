package com.sas.ss2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Created with IntelliJ IDEA.
 *
 * @author: jinzhijie
 * @date: 2019/4/22 15:02
 * @description: To change this template use File | Settings | File Templates.
 */
@Setter
@Getter
@ApiModel(value = "UserInfo", description = "用户信息bean")
public class UserInfo {

    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;

    @ApiModelProperty(value = "用户名称", required = true)
    private String userName;

    @ApiModelProperty(value = "用户年龄", required = false)
    private int age;

    public UserInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
