package com.yyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyc.dao.CinemaDao;
import com.yyc.entity.Cinema;
import com.yyc.service.ICinemaService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaServiceImpl extends ServiceImpl<CinemaDao, Cinema> implements ICinemaService {

    @Autowired
    private CinemaDao cinemaDao;

    @Override
    public List<Object> getArea() {
        return cinemaDao.getArea();
    }

    @Override
    public List<Cinema> getAllCinema() {
        return cinemaDao.getAllCinema();
    }


}
