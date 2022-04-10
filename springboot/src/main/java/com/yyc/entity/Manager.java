package com.yyc.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Manager {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Date birthday;
    private String ascription;
    private String phonenum;
    private String address;
}
