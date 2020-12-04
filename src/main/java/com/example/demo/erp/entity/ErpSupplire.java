package com.example.demo.erp.entity;

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
 * erp_supplire-供应商
 * </p>
 *
 * @author zyj
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErpSupplire对象", description="erp_supplire-供应商")
public class ErpSupplire implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "供应商id")
    @TableId(value = "supplire_id", type = IdType.AUTO)
    private Long supplireId;

    @ApiModelProperty(value = "供应商名称")
    private String supplireName;

    @ApiModelProperty(value = "供应商地址")
    private String supplireAddress;

    @ApiModelProperty(value = "联系人")
    private String linkUser;

    @ApiModelProperty(value = "联系电话")
    private String linkPhone;

    @ApiModelProperty(value = "是否启用(0是1否)")
    private Integer supplireEnable;

    @ApiModelProperty(value = "创建人")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "修改人")
    private Integer updatedBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "删除标识(0否1是)")
    private Integer delFlag;


}
