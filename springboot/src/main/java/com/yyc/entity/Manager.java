package com.yyc.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Manager {
    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Date birthday;
    private Integer ascription;
    private String phone;
    private String address;
    private String img;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBirthday() {
        return birthday;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}


