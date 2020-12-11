package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 字典表
 * </p>
 *
 * @author zyj
 * @since 2020-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysDict对象", description="字典表")
public class SysDict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "ID")
    @TableId(value = "dict_id", type = IdType.AUTO)
    private Long dictId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典描述")
    private String dictDesc;

    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    @ApiModelProperty(value = "字典类型")
    private String dictType;

    @ApiModelProperty(value = "类型0系统1用户自定义")
    private Integer typeId;

    @ApiModelProperty(value = "自定义字典所属企业")
    private String ownerId;

    @ApiModelProperty(value = "所属行业0通用")
    private String industryCode;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "更新时间")
    private Date updatedTime;

    @ApiModelProperty(value = "创建人")
    private Integer createdBy;

    @ApiModelProperty(value = "更新人")
    private Integer updatedBy;

    @ApiModelProperty(value = "删除标记")
    private Integer delFlag;


}
