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
import java.util.logging.SimpleFormatter;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private IMovieService movieService;

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
        Date now = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        String fnow = sdf.format(now);
        System.out.println(sdf.format(now));
        QueryWrapper<Movie> q = Wrappers.<Movie>query();

        return Result.success();
    }
}
