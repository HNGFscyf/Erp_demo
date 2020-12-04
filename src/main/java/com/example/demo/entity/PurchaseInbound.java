package com.example.demo.entity;

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
 * purchase_inbound-订单入库表
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="PurchaseInbound对象", description="purchase_inbound-订单入库表")
public class PurchaseInbound implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "入库订单id")
    @TableId(value = "inbound_id", type = IdType.AUTO)
    private Long inboundId;

    @ApiModelProperty(value = "采购订单id")
    private Long orderId;

    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "审核人id")
    private Long checkId;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "审核状态(0待审核1已审核)")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String inboundRemakes;


}
