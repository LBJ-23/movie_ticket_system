package com.yyc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyc.dao.SessionDao;
import com.yyc.entity.Session;
import com.yyc.service.ISessionsService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends ServiceImpl<SessionDao, Session> implements ISessionsService {
}
