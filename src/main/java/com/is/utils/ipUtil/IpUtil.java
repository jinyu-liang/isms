package com.is.utils.ipUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.is.utils.StringUtils;

public class IpUtil {
    /**
     * 绕过代理获取客户端的ip地址
     * 
     * @param request
     * @return
     */
    public static String getIpAttr() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotEmpty(ip) && ip.indexOf(",") != -1) {
            // 如果多级反向代理，forwardedfor中可能有多个ip，以逗号分开，取第一个有效
            String[] ips = ip.split(",");
            ip = ips[0];
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
