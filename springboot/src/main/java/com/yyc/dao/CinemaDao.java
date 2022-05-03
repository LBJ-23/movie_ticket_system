package com.yyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyc.entity.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface CinemaDao extends BaseMapper<Cinema> {

    @Select("select distinct area from cinema" )
    List<Object> getArea();

    @Select("select * from cinema")
    List<Cinema> getAllCinema();
}
