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
 * 
 * </p>
 *
 * @author zyj
 * @since 2020-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ZyjCommomAttachment对象", description="")
public class ZyjCommomAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "附件ID")
    @TableId(value = "attachment_id", type = IdType.AUTO)
    private Long attachmentId;

    @ApiModelProperty(value = "关联ID")
    private Long ownerId;

    @ApiModelProperty(value = "关联类型")
    private Integer ownerType;

    @ApiModelProperty(value = "附件名称")
    private String attachmentName;

    @ApiModelProperty(value = "扩展名")
    private String extendName;

    @ApiModelProperty(value = "保存路径")
    private String savePath;

    @ApiModelProperty(value = "缩略图路径")
    private String thumbnailUrl;

    @ApiModelProperty(value = "文件大小")
    private Integer fileSize;

    @ApiModelProperty(value = "MIME")
    private String mimeType;

    @ApiModelProperty(value = "图片宽度")
    private Integer imageWidth;

    @ApiModelProperty(value = "图片高度")
    private Integer imageHeight;

    @ApiModelProperty(value = "创建人")
    private Integer createdBy;

    @ApiModelProperty(value = "创建时间")
    private Date createdTime;

    @ApiModelProperty(value = "修改人")
    private Integer updatedBy;

    @ApiModelProperty(value = "修改时间")
    private Date updatedTime;

    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;
    public void addPrefixInit(Integer createdBy){
        this.setCreatedBy(createdBy);
        this.setUpdatedBy(createdBy);
        this.setCreatedTime(new Date());
        this.setUpdatedTime(new Date());
        this.setDelFlag(0);
        this.setOwnerId(-1l);
        this.setOwnerType(-1);

    }

}
