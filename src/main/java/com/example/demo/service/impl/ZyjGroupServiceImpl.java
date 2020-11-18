package com.example.demo.service.impl;

import com.example.demo.entity.ZyjGroup;
import com.example.demo.mapper.ZyjGroupMapper;
import com.example.demo.service.ZyjGroupService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyj
 * @since 2020-11-17
 */
@Service
public class ZyjGroupServiceImpl extends ServiceImpl<ZyjGroupMapper, ZyjGroup> implements ZyjGroupService {
    @Autowired
    private ZyjGroupMapper zyjGroupMapper;
    /**
     * 查询当前用户的部门及下级部门
     * @param groupId
     * @return
     */
    @Override
    public List<ZyjGroup> getTreeGroup(Integer groupId) {
        return zyjGroupMapper.getTreeGroup(groupId);
    }
}
