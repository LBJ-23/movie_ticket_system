package com.yyc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yyc.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieDao extends BaseMapper<Movie> {
}
