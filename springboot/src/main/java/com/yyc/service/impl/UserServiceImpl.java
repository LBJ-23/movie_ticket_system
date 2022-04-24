package com.yyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyc.dao.UserDao;
import com.yyc.entity.User;
import com.yyc.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
}
