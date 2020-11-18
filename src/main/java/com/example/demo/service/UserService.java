package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Vo.UserVo;
import com.example.demo.entity.User;


import java.util.List;


public interface UserService {
    List<User> findList();
    UserVo pageList(Integer current,Integer size,String keyWord,Integer groupId);
    IPage<User> mybatisPlusPage(Integer pageNo,Integer pageSize,User user);
    void addUser(User user);

    /**
     * 重置密码
     * @param user
     * @return
     */
    int restartPassword(User user);
    /**
     * 登录时查询用户是否存在
     * @param loginName
     * @return
     */
    User findByLoginName(String loginName);
}
