package com.example.demo.service.impl;

import com.example.demo.entity.ErpCompanySeo;
import com.example.demo.mapper.ErpCompanySeoMapper;
import com.example.demo.service.ErpCompanySeoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Service
public class ErpCompanySeoServiceImpl extends ServiceImpl<ErpCompanySeoMapper, ErpCompanySeo> implements ErpCompanySeoService {
@Autowired
private ErpCompanySeoMapper erpCompanySeoMapper;
    @Override
    public ErpCompanySeo findById(Integer seoId) {
        return erpCompanySeoMapper.findById(seoId);
    }
}
