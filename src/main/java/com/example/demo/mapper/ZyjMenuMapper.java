package com.example.demo.mapper;

import com.example.demo.entity.ZyjMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
