package com.is.utils.xmpp;

import org.androidpn.server.xmpp.push.NotificationManager;

import com.is.sys.properties.SystemPropertiesUtil;

public class XmppUtil
{
    private XmppUtil()
    {

    }

    /**
     * 消息群发
     * @param title 消息头
     * @param message 消息内容
     * @param uri 传送数据，资源地址等
     */
    public static void sendBroadcast(String title, String message, String uri)
    {
        title = title == null ? "" : title;
        message = message == null || "".equals(message) ? title : message;
        uri = uri == null ? "" : uri;
        NotificationManager notificationManager = new NotificationManager();
        notificationManager.sendBroadcast(SystemPropertiesUtil.getStringPropertie("apiKey", "1234567890"), title, message, uri);
    }

    /**
     * 消息点对点发送
     * @param userId 用户id
     * @param title 消息头
     * @param message 消息内容
     * @param uri 传送数据，资源地址等
     */
    public static int sendNotifcationToUser(String userId, String title, String message, String uri)
    {
        if (userId == null)
        {
            return 0;
        }
        //        userId = userId.toLowerCase();
        title = title == null ? "" : title;
        message = message == null || "".equals(message) ? title : message;
        uri = uri == null ? "" : uri;
        NotificationManager notificationManager = new NotificationManager();
        return notificationManager
                .sendNotifcationToUser(SystemPropertiesUtil.getStringPropertie("apiKey", "1234567890"), userId, title, message, uri);
    }
}
