package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Vo.UserVo;
import com.example.demo.entity.User;


import java.util.List;


public interface UserService {
    List<User> findList();
    UserVo pageList(Integer current,Integer size,String userName);
    IPage<User> mybatisPlusPage(Integer pageNo,Integer pageSize,User user);
    void addUser(User user);
}
