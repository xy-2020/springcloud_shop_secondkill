package com.wcq.service;

import com.wcq.entity.User;

public interface IUserService {

    int register(User user);

    User login(String username, String password);

    User queryByUserName(String username);

    int updatePasswordByUserName(String username, String password);

}