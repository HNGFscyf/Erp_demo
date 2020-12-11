package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Vo.DictVo;
import com.example.demo.common.BaseController;
import com.example.demo.common.R;
import com.example.demo.entity.SysDict;
import com.example.demo.entity.User;
import com.example.demo.service.SysDictService;
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

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
@Api(value = "字典表管理",tags = {"字典表管理"})
@RestController
@RequestMapping("/sysDict")
public class SysDictController extends BaseController {
    @Autowired
    private SysDictService sysDictService;

    /**
     * 字典表分页查询
     * @param current
     * @param size
     * @param keyWord
     * @return
     */
    @ApiOperation(value = "分页查询字典列表",response = SysDict.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "keyWord", value = "名称", required = false, paramType = "query", dataType = "String")

    })
    @GetMapping("list")
    public R list(Integer current, Integer size,String keyWord){
        DictVo dictVo = sysDictService.pageList(current, size, keyWord);
        return R.ok().put("data",dictVo);
    }

    /**
     * 添加或修改字典表信息
     * @param sysDict
     * @return
     */
    @ApiOperation(value = "添加或修改字典表信息",response = SysDict.class)
    @PostMapping("addOrUpdate")
    public R addOrUpdate(SysDict sysDict){
        sysDict.setDelFlag(Constant.DEL_FLAG_0);
        sysDict.setCreatedBy(CastUtil.castInt(getUserId()));
        sysDict.setCreatedTime(new Date());
        sysDictService.saveOrUpdate(sysDict);
        return R.ok("保存成功");
    }

    /**
     * 删除
     * @param dictId
     * @return
     */
    @ApiOperation(value = "删除",response = SysDict.class)
    @ApiImplicitParam(name = "dictId", value = "id", required = true, paramType = "query", dataType = "Long")
    @PostMapping("delete")
    public R delete(Long dictId){
        SysDict sysDict=new SysDict();
        sysDict.setDictId(dictId);
        sysDict.setDelFlag(Constant.DEL_FLAG_1);
        sysDictService.updateById(sysDict);
        return R.ok("删除成功");
    }

}

