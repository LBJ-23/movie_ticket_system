package com.yyc.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.dao.ManagerDao;
import com.yyc.entity.Manager;
import com.yyc.service.IManagerService;
import org.apache.catalina.manager.ManagerServlet;
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
    @PostMapping
    public Result<?> save(@RequestBody Manager manager){
        managerService.save(manager);
        return Result.success();
    }

//    分页、条件查询
    @GetMapping("/findPage")
    public Result<?> findPage(@RequestParam Integer pageNum,
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
}
