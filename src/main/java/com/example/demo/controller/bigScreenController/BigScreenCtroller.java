package com.example.demo.controller.bigScreenController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.Assert;
import com.example.demo.common.BaseController;
import com.example.demo.common.R;
import com.example.demo.exception.Exception;
import com.example.demo.service.BigScreenMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
@RequestMapping("/BigScreenCtroller")
@Api(value = "首页页面",tags = {"首页页面"})
public class BigScreenCtroller extends BaseController {
    @Autowired
    private BigScreenMessageService bigScreenMessage;

    /**
     * 首页页面
     * @param params
     * @return
     */
    @PostMapping("bigScreenMessage")
    @ApiOperation(value = "首页页面")
    public R bigScreenMessage(@RequestParam Map<String, Object> params) {
        this.hasKey(params, new String[]{"path"});
        Assert.isNull(params.get("path"),"path不能为空");
        String path = (String)params.get("path");
        //判断path路径是否在允许值的范围
        if (!path.equals("riskPointCount")
                && !path.equals("todayCheck")
                && !path.equals("riskPointControlRecord")
                && !path.equals("warningRiskPoint")
                && !path.equals("hiddenCount")
                && !path.equals("hiddenBulletin")) {
            throw new Exception("path路径不存在，请确认");
        }
        String data = bigScreenMessage.bigScreenMessage(params);
        JSONObject jsonObject1 =JSONObject.parseObject(data);
        return R.ok().put("data",jsonObject1);
    }
}
