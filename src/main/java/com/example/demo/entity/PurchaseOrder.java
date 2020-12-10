package com.example.demo.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * purchase_order-采购订单表
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PurchaseOrder对象", description="purchase_order-采购订单表")
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "采购订单id")
    @TableId(value = "purchase_id", type = IdType.AUTO)
    private Long purchaseId;

    @ApiModelProperty(value = "采购订单单号")
    private String purchaseOdd;

    @ApiModelProperty(value = "供应商id")
    private Long supplireId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private Integer produceNum;

    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "审核人id")
    private Long checkId;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "订单状态(0待审核1已审核)")
    private Integer purchaseStatus;

    @ApiModelProperty(value = "订单完成状态(0未开始1已完成)")
    private Integer purchaseFinish;

    @ApiModelProperty(value = "订单完成时间")
    private LocalDateTime purchaseFinishTime;

    @ApiModelProperty(value = "备注")
    private String purchaseRemake;

    @ApiModelProperty(value = "采购总额")
    private BigDecimal purchaseTotal;


}
