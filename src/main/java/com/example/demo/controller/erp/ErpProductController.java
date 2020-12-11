package com.example.demo.controller.erp;


import com.example.demo.common.R;
import com.example.demo.service.ErpProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * erp_product-商品表 前端控制器
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
@RestController
@RequestMapping("/erpProduct")
public class ErpProductController {
    @Autowired
    private ErpProductService erpProductService;
    public R list(){
     return null;
    }
}

