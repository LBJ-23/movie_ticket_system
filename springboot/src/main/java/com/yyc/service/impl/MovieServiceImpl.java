package com.yyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyc.dao.MovieDao;
import com.yyc.entity.Movie;
import com.yyc.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieDao, Movie> implements IMovieService {

    @Autowired
    private MovieDao movieDao;


    @Override
    public List<Movie> getEarlyMovie(String now) {
        return movieDao.getEarlyMovie(now);
    }

    @Override
    public List<Movie> getLateMovie(String now) {
        return movieDao.getLateMovie(now);
    }

    @Override
    public List<Movie> getAllMovie() {
        return movieDao.getAllMovie();
    }
}
