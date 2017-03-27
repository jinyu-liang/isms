package com.base.iterceptor;

import javax.servlet.http.HttpServletRequest;

import org.springside.modules.utils.web.struts2.Struts2Utils;

import com.base.security.SpringSecurityUtils;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 判断session是否超时的拦截器 超时跳到登录页面
 * 
 * @author 
 */
public class SessionIterceptor extends AbstractInterceptor
{
    private static final long serialVersionUID = -8076610651721925020L;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception
    {
        HttpServletRequest request = Struts2Utils.getRequest();

        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null)
        {
            if (SpringSecurityUtils.getCurrentUser() == null)
            {
                Ret.ret301("请求超时！请重新登录！");
                return null;
            }
        }
        return actionInvocation.invoke();
    }
}