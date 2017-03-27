package com.is.pms.msginteract.service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;

import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.is.pms.msginteract.io.filter.PmsIoFilter;
import com.is.pms.msginteract.io.handler.PmsIoHandler;
import com.is.pms.msginteract.io.policy.PolicyFileLoader;
import com.is.session.context.IsSessionContext;

/**
 * 
 * <p>文件名称: WebMsgService.java</p>
 * <p>文件描述: flex socket 通信接口</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class WebMsgService implements Runnable
{
    private static final Logger    LOGGER                 = LoggerFactory.getLogger(WebMsgService.class);

    private static WebMsgService   msgService             = null;

    private int                    port                   = 9999;                                        //socket端口

    @SuppressWarnings("unused")
    private ExecutorService        executorService;                                                      //线程池

    //    private final int              POOL_SIZE              = 50;                                          //单个CPU线程池大小

    private NioSocketAcceptor      acceptor               = null;

    @SuppressWarnings("unused")
    private IsSessionContext isSessionContext = null;

    private WebMsgService()
    {
        isSessionContext = IsSessionContext.getInstance();

        //        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * POOL_SIZE);
    }

    public static WebMsgService getInstance()
    {
        if (msgService == null)
        {
            synchronized (WebMsgService.class)
            {
                if (msgService == null)
                {
                    msgService = new WebMsgService();
                }
            }
        }
        return msgService;
    }

    public void startServer()
    {
        Thread t = new Thread(this);
        t.start();
    }

    public void stopServer()
    {
        if (acceptor != null)
        {
            acceptor.unbind();
        }
    }

    public void run()
    {
        if (PolicyFileLoader.hasPolicyFile())
        {
            try
            {
                PolicyFileLoader.loadPolicyFile();
                acceptor = new NioSocketAcceptor();
                acceptor.getFilterChain().addLast("chatFilter", new PmsIoFilter());
                acceptor.setHandler(new PmsIoHandler());
                acceptor.bind(new InetSocketAddress(port));
            }
            catch (IOException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("run异常：{}", e);
                }
            }
            catch (Exception e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("run异常：{}", e);
                }
            }
        }
        else
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("policyfile.xml文件不存在");
            }
        }
    }

}
