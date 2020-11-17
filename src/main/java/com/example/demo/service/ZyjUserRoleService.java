package com.example.demo.service;

import com.example.demo.entity.ZyjRoleMenu;
import com.example.demo.entity.ZyjUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyj
 * @since 2020-11-12
 */
public interface ZyjUserRoleService extends IService<ZyjUserRole> {
    /**
     * 查询该用户是否有权限
     * @return
     */
    List<ZyjRoleMenu> findUserRole(Integer roleId);

    /**
     * 删除该角色的菜单
     * @param roleId
     * @return
     */
    int deleteRoleMenu(Integer roleId);

}
