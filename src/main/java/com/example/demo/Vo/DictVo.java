package com.example.demo.Vo;

import com.example.demo.entity.SysDict;
import lombok.Data;

import java.util.List;

/**
 * 字典表分页查询返回数据
 */
@Data
public class DictVo {
    private Integer current;
    private Integer size;
    private Long total;
    private Long pages;
    private List<SysDict> dictList;
}
