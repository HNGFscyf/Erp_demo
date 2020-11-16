package com.example.demo.Vo;

import com.example.demo.entity.User;
import com.example.demo.entity.ZyjRole;
import lombok.Data;

import java.util.List;


@Data
public class RoleVo {
    private Integer current;
    private Integer size;
    private Long total;
    private Long pages;
    private List<ZyjRole> roleList;
}
