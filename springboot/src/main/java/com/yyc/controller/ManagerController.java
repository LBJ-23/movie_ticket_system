package com.yyc.controller;

import com.yyc.config.Result;
import com.yyc.entity.Manager;
import com.yyc.service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
