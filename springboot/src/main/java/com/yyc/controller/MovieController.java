package com.yyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.entity.Movie;
import com.yyc.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

    //获取现在时间并转换格式
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
    String fnow = sdf.format(now);

    //添加电影信息
    @PostMapping("/addMovie")
    public Result<?> addMovie(@RequestBody Movie movie){
        movieService.save(movie);
        return Result.success();
    }

    //分页查询
    @GetMapping("/movieFindPage")
    public Result<?> movieFindPage(@RequestParam Integer pageNum,
                                     @RequestParam Integer pageSize,
                                     @RequestParam String type,
                                     @RequestParam String search){

        QueryWrapper<Movie> q = Wrappers.<Movie>query();
        if (search!=""){
            q.like(type,search);
        }
        Page<Movie> moviePage = movieService.page(new Page<>(pageNum, pageSize),q);

        return Result.success(moviePage);
    }
    //    更新数据
    @PutMapping("/updateMovie")
    public Result<?> updateMovie(@RequestBody Movie movie){
        System.out.println("in");
        movieService.updateById(movie);
        return Result.success();
    }
    //    删除数据
    @DeleteMapping("/delMovie/{id}")
    public Result<?> delMovie(@PathVariable Integer id){
        movieService.removeById(id);
        return Result.success();
    }
    //获取上映时间比现在早的电影
    @GetMapping("/getEarlyMovie")
    public Result<?> getEarlyMovie(){
        System.out.println(sdf.format(now));
        List<Movie> movie = movieService.getEarlyMovie(fnow);
        return Result.success(movie);
    }
    //获取上映时间比现在早的电影
    @GetMapping("/getLateMovie")
    public Result<?> getLateMovie(){
        System.out.println(sdf.format(now));
        List<Movie> movie = movieService.getLateMovie(fnow);
        return Result.success(movie);
    }

    @GetMapping("/earlyFindPage")
    public Result<?> earlyFindPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam String type,
                                   @RequestParam String search){

        QueryWrapper<Movie> q = Wrappers.<Movie>query();
        q.le("released_time",fnow).orderByDesc("released_time");
        if (search!=""){
            q.like(type,search);
        }
        Page<Movie> moviePage = movieService.page(new Page<>(pageNum, pageSize),q);

        return Result.success(moviePage);
    }
}