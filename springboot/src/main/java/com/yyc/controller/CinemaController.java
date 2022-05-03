package com.yyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.entity.Cinema;
import com.yyc.entity.Movie;
import com.yyc.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private ICinemaService cinemaService;

    //添加电影数据
    @PostMapping("/saveCinema")
    public Result<?> saveCinema(@RequestBody Cinema cinema){
        cinemaService.save(cinema);
        return Result.success();
    }

    //分页查询
    @GetMapping("/cinemaFindPage")
    public Result<?> cinemaFindPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String type,
                                   @RequestParam String search){

        QueryWrapper<Cinema> q = Wrappers.<Cinema>query();
        if (search!=""){
            q.like(type,search);
        }
        Page<Cinema> cinemaPage = cinemaService.page(new Page<>(pageNum, pageSize),q);

        return Result.success(cinemaPage);
    }
    //    更新数据
    @PutMapping("/updateCinema")
    public Result<?> updateCinema(@RequestBody Cinema cinema){
        cinemaService.updateById(cinema);
        return Result.success();
    }
    //    删除数据
    @DeleteMapping("/delCinema/{id}")
    public Result<?> delCinema(@PathVariable Integer id){
        cinemaService.removeById(id);
        return Result.success();
    }
    //获得电影院所有地区信息
    @GetMapping("/getArea")
    public Result<?> getArea(){
        System.out.println("1");
        List<Object> area = cinemaService.getArea();
        return Result.success(area);
    }
    //电影院分页
    @GetMapping("/cinemaListFindPage")
    public Result<?> cinemaListFindPage(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam String area,
                                    @RequestParam String search){

        QueryWrapper<Cinema> q = Wrappers.<Cinema>query();
        if(area!=""&& !area.equals("全部")){
            System.out.println("in");
            q.like("area",area);
        }
        if (search!=""){
            q.like("cinema_name",search);
        }
        Page<Cinema> cinemaPage = cinemaService.page(new Page<>(pageNum, pageSize), q);

        return Result.success(cinemaPage);
    }
    //获取单个电影院信息
    @GetMapping("/getCinema/{id}")
    public Result<?> getCinema(@PathVariable Integer id){
        Cinema cinema = cinemaService.getById(id);
        return Result.success(cinema);
    }
    //获取所有电影信息
    @GetMapping("/getAllCinema")
    public Result<?> getAllCinema(){
        List<Cinema> cinema = cinemaService.getAllCinema();
        return Result.success(cinema);
    }


}
