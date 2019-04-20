package com.sas.brd.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitBean {

    public String name;

    public InitBean() {
    }

    public InitBean(String name) {
        this.name = name;
    }

    public void print() {
        System.out.println("name=" + name);
    }

    @Override
    public String toString() {
        return "InitBean{" +
                "name='" + name + '\'' +
                '}';
    }
}
