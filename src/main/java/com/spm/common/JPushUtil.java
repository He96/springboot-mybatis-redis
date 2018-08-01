package com.spm.common;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.*;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import java.util.Map;

/**
 * Created by he on 2017/12/11.
 */
public class JPushUtil {

    //所有平台，所有设备，内容为 ALERT 的通知
    public static PushPayload buildPushObject_all_all_alert(String alert) {
        return PushPayload.alertAll(alert);
    }

    //所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT
    public static PushPayload buildPushObject_all_alias_alert( String alert,String... alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }

    //平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
    public static PushPayload buildPushObject_android_tag_alertWithTitle(String tag, String alert, String title, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.android(alert, title, extras))
                .build();
    }

    //平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，
    // 推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，
    // 并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。//
    // 通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。
    // APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage(String alert, String msgContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(alert)
                                .setBadge(5)
                                .setSound("happy")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                .setMessage(Message.content(msgContent))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();
    }

    //平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）交（"alias1" 与 "alias2" 的并集），
    // 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush
    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String msgContent,String... alias) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        //.addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias(alias))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msgContent)
                        .addExtra("from", "JPush")
                        .setTitle("测试")
                        .build())
                .build();
    }

    //推送内容包含SMS信息
    /*public static void testSendWithSMS() {
        JPushClient jpushClient = new JPushClient(ConfigUtil.JPUSH_MASTER_SECRET, ConfigUtil.JPUSH_APP_KEY);
        try {
            SMS sms = SMS.content("Test SMS", 10);
            PushResult result = jpushClient.sendAndroidMessageWithAlias("Test SMS", "test sms", sms, "alias1");
            //LOG.info("Got result - " + result);
        } catch (APIConnectionException e) {
            // LOG.error("Connection error. Should retry later. ", e);
        } catch (APIRequestException e) {
            // LOG.error("Error response from JPush service. Should review and fix it. ", e);
            // LOG.info("HTTP Status: " + e.getStatus());
            // LOG.info("Error Code: " + e.getErrorCode());
            // LOG.info("Error Message: " + e.getErrorMessage());
        }
    }*/
}
