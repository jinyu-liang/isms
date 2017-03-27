package com.base.projectTag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ui.AbstractUITag;

import com.opensymphony.xwork2.util.ValueStack;

/**
 * 
 * <p>文件名称: WorkShopNameConvertTag.java</p>
 * <p>文件描述: 工地不标签</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月17日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@SuppressWarnings("serial")
public class ExProjectNameConvertTag extends AbstractUITag
{
    /** 项目id */
    protected String projectId;

    public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res)
    {
        return new ExProjectNameConvert(stack, req, res);
    }

    protected void populateParams()
    {
        super.populateParams();
        ExProjectNameConvert projectNm = (ExProjectNameConvert) component;
        projectNm.setProjectId(projectId);
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

}
