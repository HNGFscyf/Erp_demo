package com.example.demo.service;

import com.example.demo.entity.ZyjGroup;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyj
 * @since 2020-11-17
 */
public interface ZyjGroupService extends IService<ZyjGroup> {
    /**
     * 查询当前用户的部门及下级部门
     * @param groupId
     * @return
     */
    List<ZyjGroup> getTreeGroup(Integer groupId);
}
