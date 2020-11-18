package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Vo.UserVo;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    List<User> mybatisPlusPage(Page<User> page,@Param("user") User user);

    /**
     * 登录时查询用户是否存在
     * @param loginName
     * @return
     */
    User findByLoginName(@Param("loginName") String loginName);

    /**
     * 查询该用户部门及下级部门的账号
     * @param page
     * @param queryWrapper
     * @return
     */
    UserVo pageList(IPage<User> page, @Param("userGroupIds") String userGroupIds, @Param("userName") String userName);
}
