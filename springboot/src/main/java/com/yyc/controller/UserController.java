package com.yyc.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.entity.User;
import com.yyc.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addUser")
    public Result<?> addUser(@RequestBody User user){
        if(user.getRemainer() == null){
            user.setRemainer(0.00);
        }
        userService.save(user);
        return Result.success();
    }

    @GetMapping("/userFindPage")
    public Result<?> userFindPage(@RequestParam Integer pageNum,
                                  @RequestParam Integer pageSize,
                                  @RequestParam String type,
                                  @RequestParam String search){
        QueryWrapper<User> q = Wrappers.<User>query();
        if (search!=""){
            q.like(type,search);
        }
        Page<User> userPage = userService.page(new Page<>(pageNum, pageSize),q);

        return Result.success(userPage);
    }

    @PutMapping("/updateUser")
    public Result<?> updateUser(@RequestBody User user){
        userService.updateById(user);
        return  Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> delUser(@PathVariable Integer id){
        userService.removeById(id);
        return Result.success();
    }
}
