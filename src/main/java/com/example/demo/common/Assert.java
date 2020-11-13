package com.example.demo.common;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 */
public abstract class Assert {
public static void isBlank(String str, String message) throws Exception {
        if (StringUtils.isBlank(str)) {
        throw new Exception(message);
        }
        }

public static void isNull(Object object, String message) throws Exception {
        if (object == null) {
        throw new Exception(message);
        }
        }
}
