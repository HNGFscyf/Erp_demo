package com.example.demo.service;

import com.example.demo.Vo.UserVo;
import com.example.demo.entity.User;


import java.util.List;


public interface UserService {
    List<User> findList();
    UserVo pageList(Integer current,Integer size);
    void addUser(User user);
}
