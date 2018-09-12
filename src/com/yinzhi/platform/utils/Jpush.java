package com.yinzhi.platform.utils;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.AliasDeviceListResult;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oscar on 2018/1/4.
 * https://github.com/jpush/jpush-api-java-client/blob/master/example/main/java/cn/jpush/api/examples/PushExample.java
 */
public class Jpush {
    private static final Logger logger = LogManager.getLogger(Jpush.class);
    protected static final String APP_KEY ="12538e2df9d51b99701ab313";
    protected static final String MASTER_SECRET = "4589f1d5f8e449492482fcb5";


    static final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, 1);


    public static  boolean sendMessage(String [] targets,String message, Map<String, String> extras,boolean isDev){

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_android_and_ios(targets,message, extras,isDev);

        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info("Got result - " + result);
            if(result.isResultOK()) return true;

        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());

        }
        return false;
    }


    /**
     * 根据alias获取设备ID列表
     * @param alias
     * @param platform
     * @return
     */
    public static List<String> getDeviceIDByAlias (String alias, String platform) {
        try {
            AliasDeviceListResult result = jpushClient.getAliasDeviceList(alias,platform);

            return result.registration_ids;

        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);

        } catch (APIRequestException e) {
           logger.error("Error response from JPush server. Should review and fix it. ", e);
           logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
        }
        return new ArrayList<String>();
    }

    public static PushPayload buildPushObject_android_and_ios(String [] targets,String message,Map<String, String> extras,boolean isDev) {
        Audience audience = Audience.all();
        if(targets != null){
            audience = Audience.alias(targets);
        }

        if(extras == null){
            extras = new HashMap();
        }

        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(audience)
                .setNotification(Notification.newBuilder()
                        .setAlert(message)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                //.setTitle("Android Title Test")
                                .addExtras(extras).build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .disableBadge()
                                .addExtras(extras)
                                //.incrBadge(1).build())
                                .build())
                                //.addExtra("extra_key", "extra_value").build())
                        .build()).setOptions(Options.newBuilder().setApnsProduction(!isDev).build())
                .build();
    }



    public static PushPayload buildPushObject_all_alias_alert(String[] aliass, String alert) {
        Audience audience = Audience.all();
        if(aliass != null){
            audience = Audience.alias(aliass);
        }
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(audience)
                .setNotification(Notification.alert(alert))
                .build();
    }

    public static void main(String[] args){
        boolean b= sendMessage(null,"测试",null,true);
        //sendMessage(null,"【瑞波币/EOS发力上攻 涨幅分别超过15%与27%】截止北京时间11:00，火币Pro平台XRP瑞波价格为2.9844美元，折合人民币约19.42元，当前涨幅为15.67%，24小时内最高价格触及3美元。火币Pro平台EOS价格为10.40美元，折合人民币约为67.68元，当前涨幅为27.29%。",null);
        System.out.println(b);
    }

}
