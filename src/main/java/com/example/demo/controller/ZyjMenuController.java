package com.example.demo.controller;


import com.example.demo.common.BaseController;
import com.example.demo.common.R;
import com.example.demo.entityDto.ZyjMenuDto;
import com.example.demo.service.ZyjMenuService;
import com.example.demo.util.RedisKeys;
import com.example.demo.util.RedisUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-11-13
 */
@Api(value = "菜单管理",tags = {"菜单管理"})
@RestController
@RequestMapping("/zyjMenu")
public class ZyjMenuController extends BaseController {
    @Autowired
    private ZyjMenuService zyjMenuService;
    @Autowired
    private RedisUtils redisUtils;
    /**
     * 查询菜单列表
     * @param keyword
     * @return
     */
    @GetMapping("/findAllMenu")
    @ApiOperation(value = "查询菜单列表")
    @ApiImplicitParam(name = "keyword", value = "模糊查询参数", required = false, paramType = "query", dataType = "String")
    public R findAllMenu(String keyword){

            List<ZyjMenuDto> allMenu = zyjMenuService.findAllMenu(keyword);
            //遍历取出parentId为0的顶级菜单
            List<ZyjMenuDto> rootMenu = new ArrayList<>();
            for (ZyjMenuDto root : allMenu) {
                if (null != root && root.getMenuParentId() == 0L) {
                    ZyjMenuDto menu = new ZyjMenuDto();
                    menu.setMenuId(root.getMenuId());
                    menu.setMenuCss(root.getMenuCss());
                    menu.setMenuName(root.getMenuName());
                    menu.setMenuLevel(root.getMenuLevel());
                    menu.setMenuDesc(root.getMenuDesc());
                    menu.setMenuParentId(root.getMenuParentId());
                    menu.setMenuUrl(root.getMenuUrl());
                    menu.setSortNo(root.getSortNo());
                    menu.setMenuPermissions(root.getMenuPermissions());
                    rootMenu.add(menu);
                }
            }
            //如果顶级菜单有数据，开始查找子节点
            if (null != rootMenu && rootMenu.size() > 0) {
                for (ZyjMenuDto root : rootMenu) {
                    //子节点递归查找添加  传递父节点
                    root.setChildren(getChildren(root.getMenuId(), allMenu));
                }
            }
            allMenu.clear();
            rootMenu.sort(Comparator.comparing(ZyjMenuDto::getSortNo));
            return R.ok().put("data", rootMenu);

    }
    //递归获取children节点
    @ApiIgnore()
    private List<ZyjMenuDto> getChildren(Long pid,List<ZyjMenuDto> list) {

        List<ZyjMenuDto> children = new ArrayList<>();

        if (null != pid){

            list.forEach(data -> {
                //若遍历的数据中的父节点id等于参数id
                //则判定当前节点为该参数id节点下的子节点
                if (null != data && data.getMenuParentId().longValue() == pid.longValue()) {
                    //构造添加结果集合
                    ZyjMenuDto menu = new ZyjMenuDto();
                    menu.setMenuId(data.getMenuId());
                    menu.setMenuCss(data.getMenuCss());
                    menu.setMenuName(data.getMenuName());
                    menu.setMenuLevel(data.getMenuLevel());
                    menu.setMenuDesc(data.getMenuDesc());
                    menu.setMenuParentId(data.getMenuParentId());
                    menu.setMenuUrl(data.getMenuUrl());
                    menu.setSortNo(data.getSortNo());
                    menu.setMenuPermissions(data.getMenuPermissions());
                    children.add(menu);

                }
            });


        }

        //如果children不为空，继续递归遍历children下的子节点
        if (children.size() > 0) {
            children.forEach(data -> {
                data.setChildren(getChildren(data.getMenuId(),list));
            });
        }
        return children;
    }
}

