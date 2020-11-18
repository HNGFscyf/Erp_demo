package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.common.*;
import com.example.demo.common.annotation.SysLog;
import com.example.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * 系统登录
 */
@RestController
@RequestMapping("login")
@Api(value = "系统登录",tags = {"系统登录"})
public class LoginController extends BaseController {
    @Autowired
    private UserRealm userRealm;
    @PostMapping("login")
    @ApiOperation(value="登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "登录名", required = true, paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "passWord", value = "登录密码", required = true, paramType = "query", dataType = "String")
    })
    @SysLog("用户登录")
    public R login(String userName, String passWord, HttpServletResponse rep){

        try {
            Assert.isBlank(userName,"用户名不能为空");
            Assert.isBlank(passWord,"密码不能为空");
            Subject subject = ShiroUtils.getSubject();
            //用户登录之前，先清空之前的的授权缓存记录
            PrincipalCollection principals =  subject.getPrincipals();
            if (null != principals) {
                userRealm.clearCachedAuthorizationInfo(principals);
                userRealm.clearCachedAuthenticationInfo(principals);
            }
            UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
            subject.login(token);
            User user = ShiroUtils.getUser();
            //如果当前登录用户不是超级管理员，做以下判断
            if (user.getUserId().longValue() != 1 && !user.getLoginName().equals("system")) {
                //1，判断用户是否拥有角色
                if (null == user.getRoleId() || user.getRoleId() == 0L) {
                    return R.error("该用户没有分配角色, 没有访问权限，请联系超级管理员");
                }
                //2，判断用户是否有所属部门
                if (null == user.getUserGroupid() || user.getUserGroupid() == 0L) {
                    return R.error("该用户没有所属部门，请联系管理员");
                }
            }
            //token信息
            subject = SecurityUtils.getSubject();
            Serializable tokenId = subject.getSession().getId();
            rep.addHeader("Access-Control-Expose-Headers", "authToken");
            rep.addHeader("authToken", JSON.toJSONString(tokenId).replaceAll("\"",""));
            return R.ok().put("data", user).put("tokenId",tokenId);
        } catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return R.error("账号或密码不正确");
        }catch (LockedAccountException e) {
            return R.error("账号已被锁定,请联系管理员");
        }catch (AuthenticationException e) {
            return R.error("账户验证失败");
        }
    }
    @PostMapping("logout")
    @ApiOperation(value = "退出登录")
    public R logout(){
        ShiroUtils.logout();
        return R.ok("登出成功");
    }
}
