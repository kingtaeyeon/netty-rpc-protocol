package com.lh.rpc.frame.service;

import com.lh.rpc.frame.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Override
    public String saveUser(String name) {
        log.info("begin save user:{}", name);
        return "save User success: "+ name;
    }
}
