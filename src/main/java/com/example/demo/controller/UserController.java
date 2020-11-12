package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.Vo.UserVo;
import com.example.demo.common.R;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "分页查询用户列表",response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, paramType = "query", dataType = "int")
    })
    @GetMapping("/pageList")
    public R pageList(Integer current,Integer size){
        UserVo userVo = userService.pageList(current, size);
        return R.ok().put("data",userVo);
    }

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
    public R addUser(@Validated User user){
        userService.addUser(user);
        return R.ok("添加成功");
    }
}
