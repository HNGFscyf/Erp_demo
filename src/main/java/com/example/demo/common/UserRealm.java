package com.example.demo.common;

import com.example.demo.entity.User;
import com.example.demo.entity.ZyjMenu;
import com.example.demo.service.UserService;
import com.example.demo.service.ZyjMenuService;
import com.example.demo.util.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
        * 用户认证
        * @author zyj
        * @since 2020/11/13 10:12
        */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private ZyjMenuService zyjMenuService;
    @Autowired
    private UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)principalCollection.getPrimaryPrincipal();
        Long userId = user.getUserId();
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId== Constant.SUPER_ADMIN){
            List<ZyjMenu> menuList = zyjMenuService.findList();
            permsList = new ArrayList<>(menuList.size());
            for(ZyjMenu menu : menuList){
                permsList.add(menu.getMenuPermissions());
            }
        }else {
            permsList=zyjMenuService.findAllMenuPerm(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for(String perms : permsList){
            if(StringUtils.isBlank(perms)){
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(permsSet);
        return info;
    }
    /**
     * 认证(登录时调用)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;

        //查询用户信息
        User user = userService.findByLoginName(token.getUsername());
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }

        //账号锁定
        if(user.getUserStatus() == 1){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getUserPassword(), ByteSource.Util.bytes(user.getUserSalt()), getName());
        return info;
    }

            /**
             * 重写方法,清除当前用户的 授权缓存
             * @param principals
             */
            @Override
            public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
                super.clearCachedAuthorizationInfo(principals);
            }

            /**
             * 重写方法，清除当前用户的 认证缓存
             * @param principals
             */
            @Override
            public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
                super.clearCachedAuthenticationInfo(principals);
            }

            @Override
            public void clearCache(PrincipalCollection principals) {
                super.clearCache(principals);
            }

            /**
             * 自定义方法：清除所有 授权缓存
             */
            public void clearAllCachedAuthorizationInfo() {
                getAuthorizationCache().clear();
            }

            /**
             * 自定义方法：清除所有 认证缓存
             */
            public void clearAllCachedAuthenticationInfo() {
                getAuthenticationCache().clear();
            }

            /**
             * 自定义方法：清除所有的  认证缓存  和 授权缓存
             */
            public void clearAllCache() {
                clearAllCachedAuthenticationInfo();
                clearAllCachedAuthorizationInfo();
            }
}
