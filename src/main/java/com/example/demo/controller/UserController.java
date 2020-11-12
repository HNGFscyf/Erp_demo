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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
