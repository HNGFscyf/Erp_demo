package com.example.demo.mapper;

import com.example.demo.entity.ZyjMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entityDto.ZyjMenuDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyj
 * @since 2020-11-13
 */
public interface ZyjMenuMapper extends BaseMapper<ZyjMenu> {
    /**
     * 查询用户角色权限
     * @return
     */
    List<String> findAllMenuPerm(@Param("userId") Long userId);

    /**
     * 查询菜单列表
     * @param keyword
     * @return
     */
    List<ZyjMenuDto> findAllMenu(@Param("keyword") String keyword);
}
