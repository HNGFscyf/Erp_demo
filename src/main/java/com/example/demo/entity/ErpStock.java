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
 * erp_stock-库存表
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErpStock对象", description="erp_stock-库存表")
public class ErpStock implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "库存表id")
    @TableId(value = "stock_id", type = IdType.AUTO)
    private Long stockId;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品数量")
    private Long productNum;

    @ApiModelProperty(value = "入库日期")
    private LocalDateTime stockTime;

    @ApiModelProperty(value = "采购入库订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单入库id")
    private Long inboundId;


}
