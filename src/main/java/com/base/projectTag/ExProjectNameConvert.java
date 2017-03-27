package com.base.projectTag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.struts2.components.ListUIBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.opensymphony.xwork2.util.ValueStack;
import com.is.utils.StringUtils;

/**
 * 
 * <p>文件名称: WorkShopNameConvert.java</p>
 * <p>文件描述: 工地字典转换器</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月17日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class ExProjectNameConvert extends ListUIBean
{
    public static final String  TEMPLATE = "exProjectNameConvert";

    private static final Logger logger   = LoggerFactory.getLogger(ExProjectNameConvert.class);

    protected String            projectId;

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public ExProjectNameConvert(ValueStack stack, HttpServletRequest request, HttpServletResponse response)
    {
        super(stack, request, response);
    }

    protected String getDefaultTemplate()
    {
        return TEMPLATE;
    }

    public void evaluateExtraParams()
    {
        super.evaluateExtraParams();
        super.setTheme("is");
        // 查找根据员工id查找姓名
        Object value = "";
        if (projectId instanceof String)
        {
            value = findValue((String) projectId);
            if (value instanceof String)
            {
                projectId = (String) value;
            }
        }
        String projectNm = "";
        if (StringUtils.isNotEmpty(projectId))
        {
            DataSource dataSource = (DataSource) SpringContextHolder.getBean("dataSource");
            Connection connection = null;
            try
            {
                connection = dataSource.getConnection();
                String namequery = "select project_nm from d_ex_project where project_id = ?";
                PreparedStatement statementPosiDepartQuery = connection.prepareStatement(namequery);
                statementPosiDepartQuery.setString(1, projectId);
                ResultSet resultSetAttach = statementPosiDepartQuery.executeQuery();
                while (resultSetAttach != null && resultSetAttach.next())
                {
                    projectNm = resultSetAttach.getString("project_nm");
                }
                // 释放资源
                if (resultSetAttach != null)
                {
                    resultSetAttach.close();
                }
                statementPosiDepartQuery.close();
            }
            catch (Exception e)
            {
                logger.error("项目标签转换器异常：{}", e);
            }
            finally
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    logger.error("项目标签转换器异常：{}", e);
                }
            }

        }
        addParameter("projectNm", projectNm);
    }
}
