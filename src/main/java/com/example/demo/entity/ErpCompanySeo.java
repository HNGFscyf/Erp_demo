package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2020-12-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="ErpCompanySeo对象", description="")
public class ErpCompanySeo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "seo_id", type = IdType.AUTO)
    private Long seoId;

    @ApiModelProperty(value = "logo")
    private String seoLogo;

    @ApiModelProperty(value = "标题")
    private String seoTitle;

    @ApiModelProperty(value = "ws 推送地址")
    private String seoWs;

    @ApiModelProperty(value = "1新0 旧")
    private Integer haveNewHomePage;

    @ApiModelProperty(value = "0 不显示 1 显示")
    private Integer appHideShow;

    @ApiModelProperty(value = "融云key")
    private String rongAppKey;

    @ApiModelProperty(value = "融云secret")
    private String rongAppSecret;

    private String bigScreenTitle;

    @ApiModelProperty(value = "1显示 0 不显示")
    private Integer bigScreenScore;


}
