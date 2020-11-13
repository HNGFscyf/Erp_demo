package com.example.demo.common;
import com.example.demo.exception.Exception;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 */
public abstract class Assert {
public static void isBlank(String str, String message){
        if (StringUtils.isBlank(str)) {
        throw new Exception(message);
        }
        }

public static void isNull(Object object, String message) {
        if (object == null) {
        throw new Exception(message);
        }
        }
}
