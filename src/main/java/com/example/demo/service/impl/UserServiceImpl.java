package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import javax.swing.text.html.parser.Entity;
import java.sql.Wrapper;
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
    public UserVo pageList(Integer current, Integer size,String userName) {
        UserVo userVo=new UserVo();
        IPage page=new Page<>(current,size);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        User user=new User();
        user.setUserName(userName);
        user.setDelFlag(0);
        queryWrapper.setEntity(user);
        userMapper.selectPage(page, queryWrapper);
        userVo.setCurrent(current);
        userVo.setSize(size);
        userVo.setTotal(page.getTotal());
        userVo.setPages(page.getPages());
        userVo.setUserList(page.getRecords());
        return userVo;
    }
    @Override
     public IPage<User> mybatisPlusPage(Integer pageNo,Integer pageSize,User user){
         Page<User> page=new Page<>(pageNo,pageSize);
         page.setRecords(userMapper.mybatisPlusPage(page,user));
         return page;
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
        user.setUserStatus(0);
        user.setDelFlag(0);
        userMapper.insert(user);
    }
    /**
     * 重置密码
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int restartPassword(User user) {
       return userMapper.updateById(user);
    }

    /**
     * 登录时查询用户是否存在
     * @param loginName
     * @return
     */
    @Override
    public User findByLoginName(String loginName) {
        User byLoginName = userMapper.findByLoginName(loginName);
        return byLoginName;
    }
}
