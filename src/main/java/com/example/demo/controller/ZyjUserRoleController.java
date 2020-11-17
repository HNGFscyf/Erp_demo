package com.example.demo.controller;



import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.R;
import com.example.demo.entity.ZyjRoleMenu;
import com.example.demo.service.ZyjRoleMenuService;
import com.example.demo.service.ZyjUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-11-12
 */
@Api(value = "角色权限管理",tags = {"角色权限管理"})
@RestController
@RequestMapping("/zyjUserRole")
public class ZyjUserRoleController {
         @Autowired
         private ZyjUserRoleService zyjUserRoleService;
         @Autowired
         private ZyjRoleMenuService zyjRoleMenuService;

                /**
                 * 对该角色添加菜单
                 * @param roleId
                 * @param menuIds
                 * @return
                 */
            @PostMapping("/addrRoleMenu")
            @ApiOperation(value = "对该角色添加菜单")
            @ApiImplicitParams({
                    @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "int"),
                    @ApiImplicitParam(name = "menuIds", value = "字符串数组", required = true, paramType = "query", dataType = "String")
            })
            public R addrRoleMenu(Integer roleId,String menuIds){
                List<ZyjRoleMenu> list = zyjUserRoleService.findUserRole(roleId);
                JSONArray json = JSONObject.parseArray(menuIds);
                //创建一个数组对象 长度和json数组一样 即json.size()
                Integer[] a = new Integer[json.size()];
                //然后将之转换成我们需要的数组就好了
                Integer[] array = json.toArray(a);
                if (list.size()>0){
                    zyjUserRoleService.deleteRoleMenu(roleId);
                    List<Integer> lists = Arrays.asList(array);
                    List<ZyjRoleMenu> zyjRoleMenus=new ArrayList<>();
                    for (Integer integer : lists) {
                        ZyjRoleMenu zyjRoleMenu =new ZyjRoleMenu();
                        zyjRoleMenu.setRoleId(roleId);
                        zyjRoleMenu.setMenuId(integer);
                        zyjRoleMenus.add(zyjRoleMenu);
                    }
                    zyjRoleMenuService.saveBatch(zyjRoleMenus,roleId);
                }else {
                    List<Integer> lists = Arrays.asList(array);
                    List<ZyjRoleMenu> zyjRoleMenus=new ArrayList<>();
                    for (Integer integer : lists) {
                        ZyjRoleMenu zyjRoleMenu =new ZyjRoleMenu();
                        zyjRoleMenu.setRoleId(roleId);
                        zyjRoleMenu.setMenuId(integer);
                        zyjRoleMenus.add(zyjRoleMenu);
                    }
                    zyjRoleMenuService.saveBatch(zyjRoleMenus,roleId);
                }
                return R.ok("添加成功");
            }
}

