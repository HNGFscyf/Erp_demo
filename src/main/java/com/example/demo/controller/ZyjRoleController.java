package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Vo.RoleVo;
import com.example.demo.common.BaseController;
import com.example.demo.common.R;
import com.example.demo.entity.User;
import com.example.demo.entity.ZyjMenu;
import com.example.demo.entity.ZyjRole;
import com.example.demo.service.ZyjMenuService;
import com.example.demo.service.ZyjRoleService;
import com.example.demo.util.CastUtil;
import com.example.demo.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

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
public class ZyjRoleController extends BaseController {
    @Autowired
    private ZyjRoleService zyjRoleService;
    @Autowired
    private ZyjMenuService zyjMenuService;

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
            @PostMapping("/addRole")
            @ApiOperation(value = "新增角色",response = ZyjRole.class)
            public R addRole(){
                ZyjRole zyjRole=new ZyjRole();
                zyjRole.setDelFlag(Constant.DEL_FLAG_0);
                zyjRole.setCreatedBy(CastUtil.castInt(getUserId()));
                zyjRole.setCreatedTime(new Date());
                zyjRoleService.save(zyjRole);
                return R.ok("添加成功");
            }
}

