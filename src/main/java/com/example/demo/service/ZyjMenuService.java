package com.example.demo.service;

import com.example.demo.entity.ZyjMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entityDto.ZyjMenuDto;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyj
 * @since 2020-11-13
 */
public interface ZyjMenuService extends IService<ZyjMenu> {
    List<ZyjMenu> findList();
    /**
     * 查询用户角色权限
     * @return
     */
    List<String> findAllMenuPerm(Long userId);
    /**
     * 查询菜单列表
     * @param keyword
     * @return
     */
    List<ZyjMenuDto> findAllMenu(String keyword);
    /**
     * 根据登录人获取用户权限
     * @param userId
     * @param menuLevel
     * @param parentId
     * @return
     */
    List<ZyjMenuDto> findUserMenuList(Long userId,String menuLevel,Integer parentId);
}
