package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String userName;
    @ApiModelProperty("登录名称")
    private String loginName;
    @ApiModelProperty("登录密码")
    private String userPassword;
    @ApiModelProperty("用户手机号")
    private Integer userMobile;
    @TableField(exist = false)
    /**
     * 备用字段
     */
    @ApiModelProperty("备用字段")
    private String backup;
}
