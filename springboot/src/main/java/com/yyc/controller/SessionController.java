package com.yyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.entity.Movie;
import com.yyc.entity.Session;
import com.yyc.service.ISessionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    //获取时间并转换格式
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat stdf = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss ");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String FNow = stdf.format(now);
    String FToday = sdf.format(now);
    Calendar cal = Calendar.getInstance();


    @Autowired
    private ISessionsService sessionsService;

    //添加场次信息
    @PostMapping("/addSession")
    public Result<?> addSession(@RequestBody Session session){
        sessionsService.save(session);
        return Result.success();
    }
    //场次信息分页
    @GetMapping("/sessionFindPage")
    public Result<?> sessionFindPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam Integer date,
                                     @RequestParam Integer movie,
                                     @RequestParam Integer cinema){
        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String tmo =  sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String atmo = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String aatmo = sdf.format(cal.getTime());
        QueryWrapper<Session> q = Wrappers.<Session>query();
        q.like("cinema_id",cinema);
        if(movie!=0){
           q.like("movie_id",movie) ;
        }
        if(date==1){
            q.ge("start",FToday).lt("start",tmo);
        }
        if(date==2){
            q.ge("start",tmo).lt("start",atmo);
        }
        if(date==3){
            q.ge("start",atmo).lt("start",aatmo);
        }
        if(date==4){
            q.ge("start",aatmo);
        }
        if(date==5){
            q.lt("start",FToday);
        }
        Page<Session> sessionPage = sessionsService.page(new Page<>(pageNum, pageSize), q);
        Map<String, Object> map = sessionsService.getMap(q);
        return Result.success(sessionPage);
    }
    //更新数据
    @PutMapping("/updateSession")
    public Result<?> updateSession(@RequestBody Session session){
        System.out.println("in");
        sessionsService.updateById(session);
        return Result.success();
    }
    //删除数据
    @DeleteMapping("/delSession/{id}")
    public Result<?> delSession(@PathVariable Integer id){
        sessionsService.removeById(id);
        return Result.success();
    }
    //根据条件查询场次信息
    @GetMapping("/getSessionList")
    public Result<?> getSessionList(@RequestParam Integer movieId,
                                    @RequestParam Integer cinemaId,
                                    @RequestParam Integer date){

        cal.setTime(now);
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String tmo =  sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String atmo = sdf.format(cal.getTime());
        cal.add(Calendar.DAY_OF_YEAR, +1);
        String aatmo = sdf.format(cal.getTime());
        QueryWrapper<Session> q = Wrappers.<Session>query();
        if(date==1){
            q.ge("start",FNow).lt("start",tmo);
        }
        if(date==2){
            q.ge("start",tmo).lt("start",atmo);
        }
        if(date==3){
            q.ge("start",atmo).lt("start",aatmo);
        }
        q.eq("cinema_id",cinemaId);
        q.eq("movie_id",movieId);
        List<Session> sessionList = sessionsService.list(q);
        return Result.success(sessionList);
    }
}
