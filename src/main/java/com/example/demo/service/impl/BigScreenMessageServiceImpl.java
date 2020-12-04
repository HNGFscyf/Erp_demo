package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.entity.bigScreen.BigScreenMessage;
import com.example.demo.enums.BigScreenDataTypeEnum;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.BigScreenMessageService;
import com.example.demo.util.Constant;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BigScreenMessageServiceImpl implements BigScreenMessageService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String bigScreenMessage(String params) {
        Map<String, Object> maps = new HashMap();
        maps.put("companyId", params.split("_")[0]);
        maps.put("groupId", params.split("_")[1]);
        maps.put("path", params.split("_")[2]);
        return this.bigScreenMessage(maps);
    }
    @Override
    public String bigScreenMessage(Map<String, Object> params) {
        JSONObject one = null;
        Map<String, Object> map = params;
        String text = params.get("path").toString();
        if (BigScreenDataTypeEnum.valueOf(text) != null) {
            JSONArray riskPointControlRecord;
            JSONObject def;
            if (text.equals(BigScreenDataTypeEnum.riskPointCount.idType)) {
                QueryWrapper<User> queryWrapper=new QueryWrapper<>();
                queryWrapper.lambda().eq(User::getDelFlag, Constant.DEL_FLAG_0)
                        .eq(User::getUserStatus,Constant.userStatus_0);
                List<User> users = userMapper.selectList(queryWrapper);
                riskPointControlRecord = new JSONArray();
                def = new JSONObject();
                def.put("all", users.size());
                def.put("list", users);
                riskPointControlRecord.add(def);
                return new Gson().toJson(BigScreenMessage.builder().todayCheck(riskPointControlRecord).build());
            }
        }
        return "{}";
    }
}
