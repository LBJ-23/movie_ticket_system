package com.yyc.controller;

import com.yyc.config.Result;
import com.yyc.entity.User;
import com.yyc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/saveUser")
    public Result<?> saveUser(@RequestBody User user){

        userService.save(user);
        return Result.success();

    }
}
