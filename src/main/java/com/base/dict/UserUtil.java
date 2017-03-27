package com.base.dict;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.utils.StringUtils;

public class UserUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(UserUtil.class);

    private UserUtil()
    {

    }

    public static String getUserNameById(String userId)
    {
        String username = "";
        if (StringUtils.isNotEmpty(userId))
        {
            DataSource dataSource = (DataSource) SpringContextHolder.getBean("dataSource");
            Connection connection = null;
            try
            {
                connection = dataSource.getConnection();
                String namequery = "select name from ggkz_user_info where user_id = ?";
                PreparedStatement statementPosiDepartQuery = connection.prepareStatement(namequery);
                statementPosiDepartQuery.setString(1, userId);
                ResultSet resultSetAttach = statementPosiDepartQuery.executeQuery();
                while (resultSetAttach != null && resultSetAttach.next())
                {
                    username = resultSetAttach.getString("name");
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
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("{}", e);
                }
            }
            finally
            {
                try
                {
                    connection.close();
                }
                catch (SQLException e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("{}", e);
                    }
                }
            }
        }
        return username;
    }
}
