package com.yyc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yyc.entity.Cinema;

import java.util.List;


public interface ICinemaService extends IService<Cinema> {

    List<Object> getArea();

    List<Cinema> getAllCinema();

}
