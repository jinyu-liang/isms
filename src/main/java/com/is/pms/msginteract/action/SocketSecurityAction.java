package com.is.pms.msginteract.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;

/**
 * 
 * <p>文件名称: SocketSecurityAction.java</p>
 * <p>文件描述: flex socket 通信 JSESSIONID效验</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Namespace("/socket")
public class SocketSecurityAction extends BaseStruts2Action implements ServletResponseAware
{

    private static final long   serialVersionUID = 1L;

    private static final Logger LOGGER           = LoggerFactory.getLogger(SocketSecurityAction.class);

    private HttpServletResponse response;

    /**
     * 效验JSESSIONID：0效验失败；1效验成功
     */
    public void checkSession()
    {
        response.setContentType("text/plain");
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            if (this.session.isEmpty())
            {
                out.write(0);
            }
            else
            {
                out.write(1);
            }
            out.flush();
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("checkSession异常：{}", e);
            }
        }
        finally
        {
            if (out != null)
            {
                try
                {
                    out.close();
                }
                catch (Exception e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("checkSession异常：{}", e);
                    }
                }
            }
        }

    }

    @Override
    public void setServletResponse(HttpServletResponse response)
    {
        this.response = response;
    }

}
