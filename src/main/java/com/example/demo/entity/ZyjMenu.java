package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyj
 * @since 2020-11-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZyjMenu对象", description="")
@TableName("zyj_menu")
public class ZyjMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键di")
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @ApiModelProperty(value = "菜单父级id")
    private Integer menuParentId;
    @ApiModelProperty(value = "菜单层级")
    private Integer menuLevel;
    @ApiModelProperty(value = "菜单路径")
    private String menuUrl;
    @ApiModelProperty(value = "菜单描述")
    private String menuDesc;
    @ApiModelProperty(value = "菜单样式")
    private String menuCss;

    @ApiModelProperty(value = "shiro权限标志")
    private String menuPermissions;

    @ApiModelProperty(value = "菜单排序")
    private Integer sortNo;

    @ApiModelProperty(value = "是否显示 0：显示1：不显示")
    private Integer isShow;

    @ApiModelProperty(value = "创建人")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;

    @ApiModelProperty(value = "修改人")
    private Integer updatedBy;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updatedTime;

    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;


}
