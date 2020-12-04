package com.example.demo.mapper;

import com.example.demo.entity.ErpCompanySeo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Mapper
public interface ErpCompanySeoMapper extends BaseMapper<ErpCompanySeo> {
   ErpCompanySeo findById(@Param("seoId") Integer seoId);
}
