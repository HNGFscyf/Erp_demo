package com.example.demo.controller;


import com.example.demo.Vo.RoleVo;
import com.example.demo.common.R;
import com.example.demo.entity.User;
import com.example.demo.entity.ZyjRole;
import com.example.demo.service.ZyjRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-11-12
 */
@Api(value = "角色管理",tags = {"角色管理"})
@RestController
@RequestMapping("/zyjRole")
public class ZyjRoleController {
    @Autowired
    private ZyjRoleService zyjRoleService;

    /**
     * 角色分页查询
     * @param current
     * @param size
     * @return
     */
            @GetMapping("/findRoleList")
            @ApiOperation(value = "分页查询角色列表",response = ZyjRole.class)
            @ApiImplicitParams({
                    @ApiImplicitParam(name = "current", value = "第几页", required = true, paramType = "query", dataType = "int"),
                    @ApiImplicitParam(name = "size", value = "一页几条", required = true, paramType = "query", dataType = "int")
            })
            public R findRoleList(Integer current,Integer size){
                RoleVo roleList = zyjRoleService.findRoleList(current, size);
                return R.ok().put("data",roleList);
            }
}

