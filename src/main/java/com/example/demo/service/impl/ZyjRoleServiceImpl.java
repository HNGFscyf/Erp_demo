package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.Vo.RoleVo;
import com.example.demo.entity.ZyjRole;
import com.example.demo.mapper.ZyjRoleMapper;
import com.example.demo.service.ZyjRoleService;
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
 * @since 2020-11-12
 */
@Service
public class ZyjRoleServiceImpl extends ServiceImpl<ZyjRoleMapper, ZyjRole> implements ZyjRoleService {
    @Autowired
    private ZyjRoleMapper zyjRoleMapper;
    /**
     * 角色分页查询
     * @return
     */
    @Override
    public RoleVo findRoleList(Integer current, Integer size) {
        RoleVo roleVo=new RoleVo();
        IPage page=new Page<>(current,size);
        zyjRoleMapper.selectPage(page, null);
        roleVo.setCurrent(current);
        roleVo.setSize(size);
        roleVo.setTotal(page.getTotal());
        roleVo.setPages(page.getPages());
        roleVo.setRoleList(page.getRecords());
        return roleVo;
    }
}
