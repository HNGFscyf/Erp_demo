package com.example.demo.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.BaseController;
import com.example.demo.common.R;
import com.example.demo.entity.ZyjRoleMenu;
import com.example.demo.service.ZyjRoleMenuService;
import com.example.demo.util.CastUtil;
import com.example.demo.util.Constant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-11-13
 */
@Api(value = "角色菜单管理",tags = {"角色菜单管理"})
@RestController
@RequestMapping("/zyjRoleMenu")
public class ZyjRoleMenuController extends BaseController {
    @Autowired
    private ZyjRoleMenuService zyjRoleMenuService;

    @GetMapping("/updateRoleMenu")
    @ApiOperation(value = "修改角色菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "menuIds", value = "字符串数组", required = true, paramType = "query", dataType = "String")
    })
    public R updateRoleMenu(Integer roleId, String menuIds) {
        QueryWrapper<ZyjRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ZyjRoleMenu::getRoleId, roleId);
        List<ZyjRoleMenu> list = zyjRoleMenuService.list(queryWrapper);
        JSONArray json = JSONObject.parseArray(menuIds);
        //创建一个数组对象 长度和json数组一样 即json.size()
        Integer[] a = new Integer[json.size()];
        //然后将之转换成我们需要的数组就好了
        Integer[] integers = json.toArray(a);
        //Integer[] integers = new Integer[]{1, 2, 3,4,5,6,7,8};
        List<Integer> webMenuIdLists1 = Arrays.asList(integers);
        Collection<Long> deleteDataRoleMenuIdLists = new ArrayList<>();
        Collection<Integer> selectDataMenuIdLists = new ArrayList<>();
        Collection<Integer> webMenuIdLists = new ArrayList(webMenuIdLists1);
        if(null != list) {
            for (int i = 0; i < list.size(); i++) {
                Long roleMenuid = list.get(i).getRoleMenuid();
                Integer menuId = list.get(i).getMenuId();
                if (!webMenuIdLists.contains(menuId)) {
                    deleteDataRoleMenuIdLists.add(roleMenuid);
                }
                selectDataMenuIdLists.add(menuId);
            }
        }
        // 求差集：结果
        webMenuIdLists.removeAll(selectDataMenuIdLists);
        List<ZyjRoleMenu> zyjRoleMenuInserts=new ArrayList<>();
        for (Integer menuId : webMenuIdLists) {
            ZyjRoleMenu zyjRoleMenu=new ZyjRoleMenu();
            zyjRoleMenu.setMenuId(menuId);
            zyjRoleMenu.setRoleId(roleId);
            zyjRoleMenu.setDelFlag(Constant.DEL_FLAG_0);
            zyjRoleMenuInserts.add(zyjRoleMenu);
        }
        if(null != deleteDataRoleMenuIdLists && deleteDataRoleMenuIdLists.size()>0) {
            zyjRoleMenuService.removeByIds(deleteDataRoleMenuIdLists);
        }
        if(null != zyjRoleMenuInserts && zyjRoleMenuInserts.size()>0){
            zyjRoleMenuService.saveBatch(zyjRoleMenuInserts);
        }
        return R.ok("保存成功");
    }
   /* public R updateRoleMenu(Long roleId,String menuIds) {
        QueryWrapper<ZyjRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ZyjRoleMenu::getRoleId, roleId).eq(ZyjRoleMenu::getDelFlag, Constant.DEL_FLAG_0);
        List<ZyjRoleMenu> list = zyjRoleMenuService.list(queryWrapper);
        Integer[] list1 = new Integer[list.size()];
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                list1[i] = list.get(i).getMenuId();
            }
        }
       JSONArray json = JSONObject.parseArray(menuIds);
       //创建一个数组对象 长度和json数组一样 即json.size()
       Integer[] a = new Integer[json.size()];
       //然后将之转换成我们需要的数组就好了
       Integer[] integers = json.toArray(a);
        //Integer[] integers = new Integer[]{1, 2, 3};
        List<Integer> list2 = Arrays.asList(list1);
        List<Integer> list3 = Arrays.asList(integers);
        // 求差集：结果
        Collection aa = new ArrayList(list2);
        Collection bb = new ArrayList(list3);
        if (list2.size()>list3.size()){
            aa.removeAll(bb);
            List<ZyjRoleMenu> zyjRoleMenus=new ArrayList<>();
            //转型进行查询
            Object[] ins = aa.toArray();
            Integer[] i = new Integer[ins.length];
             for(int k = 0; k < ins.length; k++){
                    i[k] = Integer.parseInt(ins[k].toString());
                 }
            QueryWrapper<ZyjRoleMenu> queryWrapper1=new QueryWrapper<>();
             queryWrapper1.in("menu_id",i);
             queryWrapper1.lambda().eq(ZyjRoleMenu::getRoleId,roleId);
             queryWrapper1.lambda().eq(ZyjRoleMenu::getDelFlag,Constant.DEL_FLAG_0);
            List<ZyjRoleMenu> list4 = zyjRoleMenuService.list(queryWrapper1);
            for (ZyjRoleMenu menu : list4) {
                ZyjRoleMenu zyjRoleMenu=new ZyjRoleMenu();
                zyjRoleMenu.setRoleMenuid(menu.getRoleMenuid());
                zyjRoleMenu.setDelFlag(Constant.DEL_FLAG_1);
                zyjRoleMenus.add(zyjRoleMenu);
            }
            zyjRoleMenuService.updateBatchById(zyjRoleMenus);
            //System.out.println("最终结果：" + aa);
        }else if(list2.size() < list3.size()){
            bb.removeAll(aa);
            List<ZyjRoleMenu> zyjRoleMenus=new ArrayList<>();
            for (Object o : bb) {
                ZyjRoleMenu zyjRoleMenu=new ZyjRoleMenu();
                zyjRoleMenu.setMenuId((Integer) o);
                zyjRoleMenu.setRoleId(CastUtil.castInt(roleId));
                zyjRoleMenu.setDelFlag(Constant.DEL_FLAG_0);
                zyjRoleMenu.setCreatedBy(CastUtil.castInt(getUserId()));
                zyjRoleMenu.setCreatedTime(new Date());
                zyjRoleMenus.add(zyjRoleMenu);
            }
            zyjRoleMenuService.saveBatch(zyjRoleMenus);
            //System.out.println("最终结果：" + bb);
       }else {
            return R.ok("保存成功");
        }
       return R.ok("保存成功");
    }*/
}

