package com.example.demo.service;

import com.example.demo.entity.ErpCompanySeo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
public interface ErpCompanySeoService extends IService<ErpCompanySeo> {
    ErpCompanySeo findById(Integer seoId);
}
