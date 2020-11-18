package com.example.demo.entityDto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.entity.ZyjGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class groupDto implements Serializable {
    @ApiModelProperty(value = "主键id")
    private Long groupId;

    @ApiModelProperty(value = "部门名称")
    private String groupName;

    @ApiModelProperty(value = "部门父级id")
    private Integer groupParentId;

    private List<ZyjGroup> children;
}
