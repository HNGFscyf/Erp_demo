package com.example.demo.common;

import com.example.demo.entity.User;
import com.example.demo.exception.Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

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
    /**
     * 判断Map是否存在指定的key，不存在抛出异常
     * @param map
     * @param key
     * @return
     */
    public String hasKey(Map<String, Object> map, String... key) {
        String msg = "";
        if (null == map || map.isEmpty()) {
            msg = "缺少参数";
            throw new Exception(msg);
        } else {
            if (key != null && key.length > 0) {
                for(int i = 0; i < key.length; ++i) {
                    if (!map.containsKey(key[i])) {
                        msg = msg + "缺少参数[" + key[i] + "]";
                    }
                }
            }
            if (!msg.equals("")) {
                throw new Exception(msg);
            } else {
                return msg;
            }
        }
    }
}
