package com.example.demo.common;

import com.example.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共Controller
 * @author zyj
 * @since 2020/11/13 9:23
 */
public  abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected User getUser() {
        return (User) ShiroUtils.getSubject().getPrincipal();
    }

    protected Long getUserId() {
        return getUser().getUserId();
    }
    protected Long getRoleId(){return getUser().getRoleId();}

    protected String getLoginName() {
        return getUser().getLoginName();
    }
    protected Integer getGroupId() {
        return getUser().getUserGroupid();
    }
}
