package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Vo.UserVo;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> findList() {
        return userMapper.selectList(null);
    }

    @Override
    public UserVo pageList(Integer current, Integer size) {
        UserVo userVo=new UserVo();
        IPage page=new Page<>(current,size);
        userMapper.selectPage(page, null);
        userVo.setCurrent(current);
        userVo.setSize(size);
        userVo.setTotal(page.getTotal());
        userVo.setUserList(page.getRecords());
        return userVo;
    }

    /**
     * 添加用户
     * @param user
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addUser(User user) {
        //对密码加密
        String md5Password = DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes());
        user.setUserPassword(md5Password);
        user.setDelFlag(0);
        userMapper.insert(user);
    }
}
