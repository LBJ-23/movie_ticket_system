package com.yyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyc.dao.MovieDao;
import com.yyc.entity.Movie;
import com.yyc.service.IMovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieDao, Movie> implements IMovieService {
}
