package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Vo.UserVo;
import com.example.demo.common.ShiroUtils;
import com.example.demo.entity.User;
import com.example.demo.entity.ZyjGroup;
import com.example.demo.entity.ZyjUserRole;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.ZyjGroupMapper;
import com.example.demo.mapper.ZyjUserRoleMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.Constant;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ZyjUserRoleMapper zyjUserRoleMapper;
    @Autowired
    private ZyjGroupMapper groupMapper;
    @Override
    public List<User> findList() {
        return userMapper.selectList(null);
    }

    @Override
    public UserVo pageList(Integer current, Integer size,String keyWord,Integer groupId) {
        UserVo userVo=new UserVo();
        IPage page=new Page<>(current,size);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        String userGroupIds="";
        List<ZyjGroup> treeGroup = groupMapper.getTreeGroup(groupId);
        for (ZyjGroup zyjGroup : treeGroup) {
           userGroupIds+=zyjGroup.getGroupId().toString()+",";
        }
        String  newString = userGroupIds.substring(0,userGroupIds.length()-1);
        queryWrapper.in("user_groupid",newString);
        queryWrapper.lambda().eq(false,User::getUserName,keyWord).eq(User::getDelFlag,Constant.DEL_FLAG_0);
        userMapper.selectPage(page,queryWrapper);
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
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        //新密码
        String newPassword = ShiroUtils.sha256(user.getUserPassword(), salt);
        user.setUserPassword(newPassword);
        user.setUserSalt(salt);
        user.setUserStatus(0);
        user.setDelFlag(0);
        userMapper.insert(user);
        Long userId = user.getUserId();
        ZyjUserRole zyjUserRole=new ZyjUserRole();
        zyjUserRole.setRoleId(Integer.valueOf(user.getRoleId().toString()));
        zyjUserRole.setUserId(Integer.valueOf(userId.toString()));
        zyjUserRole.setCreatedBy(user.getCreatedBy());
        zyjUserRole.setCreatedTime(user.getCreatedTime());
        zyjUserRole.setDelFlag(0);
        zyjUserRoleMapper.insert(zyjUserRole);
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
