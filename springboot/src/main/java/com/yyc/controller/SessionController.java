package com.yyc.controller;

import com.yyc.config.Result;
import com.yyc.entity.Session;
import com.yyc.service.ISessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private ISessionsService sessionsService;

    //添加场次信息
    @PostMapping("/addSession")
    public Result<?> addSession(@RequestBody Session session){
        sessionsService.save(session);
        return Result.success();
    }
}
