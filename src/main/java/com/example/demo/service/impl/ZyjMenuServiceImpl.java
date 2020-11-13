package com.example.demo.service.impl;

import com.example.demo.entity.ZyjMenu;
import com.example.demo.mapper.ZyjMenuMapper;
import com.example.demo.service.ZyjMenuService;
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
 * @since 2020-11-13
 */
@Service
public class ZyjMenuServiceImpl extends ServiceImpl<ZyjMenuMapper, ZyjMenu> implements ZyjMenuService {
    @Autowired
    private ZyjMenuMapper zyjMenuMapper;
    @Override
    public List<ZyjMenu> findList() {
        List<ZyjMenu> zyjMenus = zyjMenuMapper.selectList(null);
        return zyjMenus;
    }
    /**
     * 查询用户角色权限
     * @return
     */
    @Override
    public List<String> findAllMenuPerm(Long userId) {
        List<String> allMenuPerm = zyjMenuMapper.findAllMenuPerm(userId);
        return allMenuPerm;
    }
}
