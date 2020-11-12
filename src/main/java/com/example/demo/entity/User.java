package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("zyj_user") //对应表名
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty("用户ID")
    private Long userId;
    @ApiModelProperty("用户名称")
    @NotNull(message = "用户名不能为空")
    private String userName;
    @ApiModelProperty("用户性别")
    @NotNull(message = "用户性别不能为空")
    private String userSex;
    @ApiModelProperty("登录名称")
    @NotNull(message = "登录名称不能为空")
    private String loginName;
    @ApiModelProperty("登录密码")
    @NotNull(message = "登录密码不能为空")
    private String userPassword;
    @ApiModelProperty("用户手机号")
    @NotNull(message="手机号必须填")
    @Pattern(regexp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", message="账号请输入11位手机号") // 手机号
    private Integer userMobile;
    @TableField(exist = false)
    /**
     * 备用字段
     */
    @ApiModelProperty("备用字段")
    private String backup;
    @ApiModelProperty("删除标识")
    private Integer delFlag;
}