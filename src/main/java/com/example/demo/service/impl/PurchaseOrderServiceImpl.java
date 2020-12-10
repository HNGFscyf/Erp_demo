package com.example.demo.service.impl;

import com.example.demo.entity.PurchaseOrder;
import com.example.demo.mapper.PurchaseOrderMapper;
import com.example.demo.service.PurchaseOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * purchase_order-采购订单表 服务实现类
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
@Service
public class PurchaseOrderServiceImpl extends ServiceImpl<PurchaseOrderMapper, PurchaseOrder> implements PurchaseOrderService {

}
