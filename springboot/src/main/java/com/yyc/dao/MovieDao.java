package com.yyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyc.entity.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieDao extends BaseMapper<Movie> {

    @Select("select * from movie where released_time <= #{now} order by released_time desc")
    List<Movie> getEarlyMovie(String now);

    @Select("select * from movie where released_time > #{now} order by released_time asc")
    List<Movie> getLateMovie(String now);
}
