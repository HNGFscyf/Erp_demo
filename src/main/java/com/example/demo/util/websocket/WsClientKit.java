package com.example.demo.util.websocket;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.ErpCompanySeo;
import com.example.demo.service.ErpCompanySeoService;
import org.apache.commons.lang.StringUtils;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * websocket工具类
 */
@Component
public class WsClientKit {

    private static Logger logger = LoggerFactory.getLogger(WsClientKit.class);

    private static ErpCompanySeoService erpCompanySeoService;

    //通过非静态set方法给静态companySeoService赋值
    @Autowired
    private void setCompanySeoService(ErpCompanySeoService erpCompanySeoService) {
        WsClientKit.erpCompanySeoService = erpCompanySeoService;
    }

    public static void sendWs(Long companyId, Long groupId, String path) {
        QueryWrapper<ErpCompanySeo> wrapper=new QueryWrapper<>();
        wrapper.lambda().eq(ErpCompanySeo::getSeoId,1);
        ErpCompanySeo companySeo = erpCompanySeoService.findById(1);
        if (null == companySeo) {
            logger.error("WsClientKit/companySeo","未配置ws访问信息");
        }else{
            String apiFrom = companySeo.getSeoWs();
            if (StringUtils.isNotBlank(apiFrom)) {
                ExecutorService newFixThreadPool = Executors.newFixedThreadPool(10);
                String pathUrl = path.concat("-back");
                //String wsUrl = "ws://192.168.125.220:8081/ws?encoding=text&companyId=" + companyId + "&groupId=" + groupId + "&path=" + pathUrl + "&from=" + apiFrom;
                String wsUrl = "ws://" + companySeo.getSeoWs() + "/ws/" + "/client";
                System.out.println("#######################wsUrl#######################"+wsUrl);
                Runnable runnable = () -> {
                    try {
                        WebSocketClient client = new WebSocketClient(new URI(wsUrl), new Draft_6455()) {
                            public void onOpen(ServerHandshake serverHandshake) {
                                logger.info("WsClientKit-握手成功");
                               /* this.send(BigScreenDataTypeEnum.riskPointCount.idType.concat("-back"));
                                this.send(BigScreenDataTypeEnum.riskPointControlRecord.idType.concat("-back"));
                                this.send(BigScreenDataTypeEnum.warningRiskPoint.idType.concat("-back"));
                                this.send(BigScreenDataTypeEnum.hiddenCount.idType.concat("-back"));
                                this.send(BigScreenDataTypeEnum.hiddenBulletin.idType.concat("-back"));
                                this.send(BigScreenDataTypeEnum.todayCheck.idType.concat("-back"));*/
                                this.send(pathUrl);
                            }

                            public void onMessage(String msg) {
                                logger.info("WsClientKit-收到消息==========" + msg);
                                if (msg.equals("over")) {
                                    this.close();
                                }

                            }

                            public void onClose(int i, String s, boolean b) {
                                logger.info("WsClientKit-链接已关闭");
                            }

                            public void onError(Exception e) {
                                e.printStackTrace();
                                logger.info("WsClientKit-发生错误已关闭");
                            }
                        };
                        client.connect();
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }

                };
                newFixThreadPool.execute(runnable);
                newFixThreadPool.shutdown();
            }
        }
    }
}
