package com.example.demo.service;

import java.util.Map;

public interface BigScreenMessageService {
    /**
     * 查询大屏信息
     * @param params
     * @return
     */
    String bigScreenMessage(Map<String, Object> params);
    String bigScreenMessage(String params);
}
