package com.example.demo.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.BaseController;
import com.example.demo.common.R;
import com.example.demo.entity.User;
import com.example.demo.entity.ZyjGroup;
import com.example.demo.service.ZyjGroupService;
import com.example.demo.util.CastUtil;
import com.example.demo.util.Constant;
import com.example.demo.util.TreeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-11-17
 */
@Api(value = "部门管理",tags = {"部门管理"})
@RestController
@RequestMapping("/zyjGroup")
public class ZyjGroupController extends BaseController {
      @Autowired
      private ZyjGroupService zyjGroupService;

    /**
     * 查询部门树
     * @return
     */
      @GetMapping("/getTreeGroup")
      @ApiOperation(value = "查询部门树",response = ZyjGroup.class)
      public R getTreeGroup() throws Exception {
          int i = CastUtil.castInt(getUserId());
          List<ZyjGroup> list;
          if (Constant.SUPER_ADMIN==i){
              list = zyjGroupService.getTreeGroup(null);
          }else {
              list = zyjGroupService.getTreeGroup(getGroupId());
          }
          List<TreeUtils> treeList = TreeUtils.getTreeList(list, "groupId", "groupParentId", "groupName");
          return R.ok()
         .put("data",treeList);
     }
    /**
     * 添加或修改部门
     * @param zyjGroup
     * @return
     */
      @PostMapping("/addGroup")
      @ApiOperation(value = "添加或修改部门",response = ZyjGroup.class)
      public R addGroup(@RequestBody ZyjGroup zyjGroup){
          if (zyjGroup.getGroupParentId()==null){
              zyjGroup.setGroupParentId(0);
          }
          zyjGroup.setCreatedBy(Integer.valueOf(getUserId().toString()));
          zyjGroup.setCreatedTime(new Date());
          zyjGroup.setDelFlag(Constant.DEL_FLAG_0);
          zyjGroupService.saveOrUpdate(zyjGroup);
          return R.ok("保存成功");
      }

    /**
     * 通过id获取单条信息
     * @param groupId
     * @return
     */
      @GetMapping("/getGroupinfo")
      @ApiOperation(value = "通过id获取单条信息",response = ZyjGroup.class)
      @ApiImplicitParam(name = "groupId", value = "部门Id", required = true, paramType = "query", dataType = "Long")
      public R getGroupInfo(Long groupId){
          QueryWrapper<ZyjGroup> queryWrapper=new QueryWrapper<>();
          ZyjGroup zyjGroup=new ZyjGroup();
          zyjGroup.setGroupId(groupId);
          zyjGroup.setCreatedBy(Integer.valueOf(getUserId().toString()));
          zyjGroup.setDelFlag(Constant.DEL_FLAG_0);
          queryWrapper.setEntity(zyjGroup);
          ZyjGroup one = zyjGroupService.getOne(queryWrapper);
          return R.ok().put("data",one);
      }

    /**
     * 删除部门
     * @param groupId
     * @return
     */
      @GetMapping("/deleteGroupInfo")
      @ApiOperation(value = "删除部门",response = ZyjGroup.class)
      @ApiImplicitParam(name = "groupId", value = "部门Id", required = true, paramType = "query", dataType = "Long")
      public R deleteGroupInfo(Long groupId){
          ZyjGroup zyjGroup1=new ZyjGroup();
          QueryWrapper<ZyjGroup> queryWrapper2=new QueryWrapper<>();
          zyjGroup1.setGroupParentId(CastUtil.castInt(groupId));
          zyjGroup1.setDelFlag(Constant.DEL_FLAG_0);
          queryWrapper2.setEntity(zyjGroup1);
          List<ZyjGroup> zyjGroups = zyjGroupService.list(queryWrapper2);
          if (zyjGroups.size()>0){
              return R.error("请先删除下级部门");
          }else {
              ZyjGroup zyjGroup2=new ZyjGroup();
              zyjGroup2.setGroupId(groupId);
              zyjGroup2.setDelFlag(Constant.DEL_FLAG_1);
              zyjGroup2.setUpdatedBy(CastUtil.castInt(getUserId()));
              zyjGroup2.setUpdatedTime(new Date());
              zyjGroupService.updateById(zyjGroup2);
          }
          return R.ok("删除成功");
      }
}

