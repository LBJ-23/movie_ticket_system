package com.yyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyc.entity.Movie;

import java.util.List;

public interface IMovieService extends IService<Movie> {

    List<Movie> getEarlyMovie(String now);

    List<Movie> getLateMovie(String now);

    List<Movie> getAllMovie();
}
