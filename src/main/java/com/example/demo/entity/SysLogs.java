package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

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
 * @since 2020-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="SysLog对象", description="")
@TableName("sys_log") //对应表名
public class SysLogs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "系统日志id")
    @TableId(value = "sys_log_id", type = IdType.AUTO)
    private Long sysLogId;

    @ApiModelProperty(value = "系统日志类型id")
    private Integer sysLogTypeId;

    @ApiModelProperty(value = "系统日志描述")
    private String sysLogDesc;

    @ApiModelProperty(value = "参数1")
    private String sysLogParam1;

    @ApiModelProperty(value = "参数2")
    private String sysLogParam2;

    @ApiModelProperty(value = "系统日志时间")
    private Date sysLogTime;

    @ApiModelProperty(value = "请求方法名")
    private String sysLogMethod;

    @ApiModelProperty(value = "请求IP地址")
    private String sysLogIp;

    @ApiModelProperty(value = "操作人ID")
    private Long sysLogUserid;

    @ApiModelProperty(value = "方法执行时长(毫秒)")
    private Long sysLogExetime;


}
