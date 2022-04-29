package com.yyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyc.dao.ManagerDao;
import com.yyc.entity.Manager;
import com.yyc.entity.Movie;
import com.yyc.service.IManagerService;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerDao, Manager> implements IManagerService {

}
