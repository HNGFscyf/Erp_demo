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
 * erp_sell-销售订单
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErpSell对象", description="erp_sell-销售订单")
public class ErpSell implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "销售订单")
    @TableId(value = "sell_id", type = IdType.AUTO)
    private Long sellId;

    @ApiModelProperty(value = "销售订单单号")
    private String sellNumber;

    @ApiModelProperty(value = "客户id")
    private Long customerId;

    @ApiModelProperty(value = "创建人id")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "审核人id")
    private Long checkId;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "审核状态(0待审核1已审核)")
    private Integer sellStatus;

    @ApiModelProperty(value = "完成状态(0未完成1已完成)")
    private Integer sellFinish;

    @ApiModelProperty(value = "完成时间")
    private LocalDateTime finishTime;

    @ApiModelProperty(value = "销售数量")
    private Integer sellNum;

    @ApiModelProperty(value = "销售总额")
    private BigDecimal sellTotal;

    @ApiModelProperty(value = "库存表id")
    private Long stockId;

    @ApiModelProperty(value = "备注")
    private String sellRemakes;


}
