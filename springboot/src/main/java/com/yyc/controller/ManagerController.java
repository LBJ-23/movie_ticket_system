package com.yyc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.entity.Manager;
import com.yyc.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/managers")
public class ManagerController {

//    注入业务层
    @Autowired
    private IManagerService managerService;


//    新增管理员数据
    @PostMapping("/saveManager")
    public Result<?> saveManage(@RequestBody Manager manager){
        System.out.println(manager);

        managerService.save(manager);
        return Result.success();
    }

//    分页、条件查询
    @GetMapping("/managerFindPage")
    public Result<?> managerFindPage(@RequestParam Integer pageNum,
                              @RequestParam Integer pageSize,
                              @RequestParam String type,
                              @RequestParam String search){

        QueryWrapper<Manager> q = Wrappers.<Manager>query();
        if (search!=""){
            q.like(type,search);
        }
        Page<Manager> managerPage = managerService.page(new Page<>(pageNum, pageSize),q);

        return Result.success(managerPage);
    }
//    更新数据
    @PutMapping("/updateManager")
    public Result<?> updateManager(@RequestBody Manager manager){
        managerService.updateById(manager);
        return Result.success();
    }
//    删除数据
    @DeleteMapping("/{id}")
    public Result<?> delManager(@PathVariable Integer id){
        managerService.removeById(id);
        return Result.success();
    }
//    登录
    @PostMapping("/login")
    public Result<?> login(@RequestBody Manager manager,@RequestParam String type){
        QueryWrapper<Manager> q = Wrappers.<Manager>query();
        q.eq(type,manager.getUsername()).eq("password",manager.getPassword());
        Manager res = managerService.getOne(q);
        if(res == null){
            return Result.error("0","账号/用户名/电话号码错误或密码错误");
        }
        return Result.success();
    }
}
