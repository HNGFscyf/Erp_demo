package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.ZyjRoleMenu;
import com.example.demo.entity.ZyjUserRole;
import com.example.demo.mapper.ZyjRoleMenuMapper;
import com.example.demo.mapper.ZyjUserRoleMapper;
import com.example.demo.service.ZyjRoleMenuService;
import com.example.demo.service.ZyjUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class ZyjUserRoleServiceImpl extends ServiceImpl<ZyjUserRoleMapper, ZyjUserRole> implements ZyjUserRoleService {
     @Autowired
     private ZyjRoleMenuMapper zyjRoleMenuMapper;
    @Override
    public List<ZyjRoleMenu> findUserRole(Integer roleId) {
        QueryWrapper<ZyjRoleMenu> queryWrapper=new QueryWrapper<>();
        ZyjRoleMenu zyjUserRole=new ZyjRoleMenu();
        zyjUserRole.setRoleId(roleId);
        queryWrapper.setEntity(zyjUserRole);
        List<ZyjRoleMenu> zyjUserRoles = zyjRoleMenuMapper.selectList(queryWrapper);
        return zyjUserRoles;
    }

    /**
     * 删除该角色的菜单
     * @param roleId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoleMenu(Integer roleId) {
        QueryWrapper<ZyjRoleMenu> queryWrapper=new QueryWrapper<>();
        ZyjRoleMenu zyjUserRole=new ZyjRoleMenu();
        zyjUserRole.setRoleId(roleId);
        queryWrapper.setEntity(zyjUserRole);
        int delete = zyjRoleMenuMapper.delete(queryWrapper);
        return delete;
    }


}
