package com.yyc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yyc.config.Result;
import com.yyc.entity.Manager;
import com.yyc.entity.Movie;
import com.yyc.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //    分页、条件查询
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
}
