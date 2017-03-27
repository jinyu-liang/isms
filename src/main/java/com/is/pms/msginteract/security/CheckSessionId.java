package com.is.pms.msginteract.security;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>文件名称: MsgInteractAction.java</p>
 * <p>文件描述: 请求效验JSESSIONID</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class CheckSessionId
{
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckSessionId.class);

    private CheckSessionId()
    {

    }

    /**
     * 效验JSESSIONID
     */
    public static boolean checker(String JSESSIONID)
    {
        boolean rst = false;
        HttpURLConnection urlConn;
        try
        {
            urlConn = (HttpURLConnection) (new URL("http://127.0.0.1:8080/ISMS/socket/socket-security!checkSession.action").openConnection());
            if (JSESSIONID != null && !"".equals(JSESSIONID))
            {
                urlConn.addRequestProperty("Cookie", "JSESSIONID=" + JSESSIONID);
            }
            else
            {
                urlConn.addRequestProperty("Cookie", "");
            }
            urlConn.setRequestMethod("POST");
            //            urlConn.setRequestProperty("Accept", "  text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            //            urlConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            //            urlConn.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.8,en-us;q=0.5,en;q=0.3");
            //            urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64; rv:20.0) Gecko/20100101 Firefox/20.0");
            HttpURLConnection.setFollowRedirects(false);
            urlConn.setDoOutput(true); // 需要向服务器写数据
            urlConn.setDoInput(true); //
            urlConn.setUseCaches(false); // 获得服务器最新的信息
            urlConn.setAllowUserInteraction(false);
            InputStream in = urlConn.getInputStream();
            byte[] bt = new byte[1];
            if (in.read(bt) != -1)
            {
                rst = bt[0] == 1;
            }
        }
        catch (MalformedURLException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("checker异常：{}", e);
            }
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("checker异常：{}", e);
            }
        }
        return rst;
    }
}
