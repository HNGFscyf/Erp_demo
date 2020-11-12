package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统登录
 */
@RestController
@RequestMapping("Login")
@Api(value = "系统登录",tags = {"系统登录"})
public class LoginController {
}
