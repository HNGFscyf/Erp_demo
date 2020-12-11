package com.example.demo.service;

import com.example.demo.Vo.DictVo;
import com.example.demo.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
public interface SysDictService extends IService<SysDict> {
    /**
     * 字典表分页查询
     * @param current
     * @param size
     * @param keyWord
     * @return
     */
       DictVo pageList(Integer current, Integer size,String keyWord);
}
