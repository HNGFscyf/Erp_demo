package com.example.demo.service;

import com.example.demo.Vo.RoleVo;
import com.example.demo.entity.ZyjRole;
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
public interface ZyjRoleService extends IService<ZyjRole> {
    /**
     * 角色分页查询
     * @return
     */
    RoleVo findRoleList(Integer current, Integer size);
}
