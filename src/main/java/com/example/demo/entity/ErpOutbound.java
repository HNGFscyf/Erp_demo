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
 * erp_outbound-销售订单出库表
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErpOutbound对象", description="erp_outbound-销售订单出库表")
public class ErpOutbound implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "出库表id")
    @TableId(value = "outbound_id", type = IdType.AUTO)
    private Long outboundId;

    @ApiModelProperty(value = "销售订单表")
    private Long sellId;

    @ApiModelProperty(value = "创建人")
    private Long createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "审核人")
    private Long checkId;

    @ApiModelProperty(value = "审核时间")
    private LocalDateTime checkTime;

    @ApiModelProperty(value = "审核状态(0待审核1已审核)")
    private Integer checkStatus;

    @ApiModelProperty(value = "备注")
    private String outboundRemakes;


}
