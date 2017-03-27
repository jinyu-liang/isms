package com.is.pms.msginteract.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.is.pms.msginteract.service.WebMsgService;

/**
 * 
 * <p>文件名称: MsgSocketListener.java</p>
 * <p>文件描述: 消息互动Socket 监听线程</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class MsgSocketListener implements ServletContextListener
{
    private WebMsgService webMsgService = null;

    @Override
    public void contextDestroyed(ServletContextEvent arg0)
    {
        if (webMsgService != null)
        {
            webMsgService.stopServer();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0)
    {
        webMsgService = WebMsgService.getInstance();
        webMsgService.startServer();
    }

}
