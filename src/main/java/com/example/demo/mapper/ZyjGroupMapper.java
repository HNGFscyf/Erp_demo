package com.example.demo.mapper;

import com.example.demo.entity.ZyjGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyj
 * @since 2020-11-17
 */
public interface ZyjGroupMapper extends BaseMapper<ZyjGroup> {
    /**
     * 查询当前用户的部门及下级部门
     * @param groupId
     * @return
     */
   List<ZyjGroup> getTreeGroup(@Param("groupId") Integer groupId);
}
