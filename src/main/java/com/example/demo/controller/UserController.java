package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Vo.UserVo;
import com.example.demo.common.Assert;
import com.example.demo.common.R;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(value = "用户管理",tags = {"用户管理"})
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/findList")
    public R findList(){
        List<User> list = userService.findList();
        return R.ok().put("data",list);
    }

    /**
     * 常用分页方法
     * @param current
     * @param size
     * @param userName
     * @return
     */
    @ApiOperation(value = "分页查询用户列表",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("/pageList")
    public R pageList(Integer current,Integer size,String userName){
        UserVo userVo = userService.pageList(current, size,userName);
        return R.ok().put("data",userVo);
    }
   /* @GetMapping("/mybatisPageList")
    @ApiOperation(value = "Mybatisplus自带插件分页查询用户列表",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "一页几条", required = true, paramType = "query", dataType = "int")
    })
    public R mybatisPageList(Integer pageNo,Integer pageSize,User user){
        IPage<User> page = userService.mybatisPlusPage(pageNo, pageSize, user);
        return R.ok().put("data",page);
    }*/

    /**
     * 添加用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "添加用户",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "loginName", value = "登录名称", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userSex", value = "性别 0 ：男 1：女", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userPassword", value = "登录密码", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userMobile", value = "手机号", required = true, paramType = "query", dataType = "int")

    })
    public R addUser(User user)  {
        try {
            Assert.isBlank(user.getLoginName(),"登录名不能为空");
            Assert.isBlank(user.getUserName(),"用户名不能为空");
            Assert.isBlank(user.getUserPassword(),"密码不能为空");
            Assert.isBlank(user.getUserSex(),"性别不能为空");
            userService.addUser(user);
            return R.ok("添加成功");
        } catch (Exception e) {
            return R.error(e.getMessage());
        }

    }
}
