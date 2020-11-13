package com.example.demo.Vo;

import com.example.demo.entity.User;
import lombok.Data;

import java.util.List;
@Data
public class UserVo {
    private Integer current;
    private Integer size;
    private Long total;
    private Long pages;
    private List<User> userList;
}
