package com.base.userTag;

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
 * @ClassName: DataConvert
 * @Description:
 * @author life
 * 
 */
public class UserNameConvert extends ListUIBean {
	public static final String TEMPLATE = "usernameConvert";
	private static final Logger logger = LoggerFactory
			.getLogger(UserNameConvert.class);
	protected String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public UserNameConvert(ValueStack stack, HttpServletRequest request,
			HttpServletResponse response) {
		super(stack, request, response);
	}

	protected String getDefaultTemplate() {
		return TEMPLATE;
	}

	public void evaluateExtraParams() {
		super.evaluateExtraParams();
		super.setTheme("is");
		// 查找根据员工id查找姓名
		Object value = "";
		if (userId instanceof String) {
			value = findValue((String) userId);
			if (value instanceof String) {
				userId = (String) value;
			}
		}
		String username = "";
		if (StringUtils.isNotEmpty(userId)) {
			DataSource dataSource = (DataSource) SpringContextHolder
					.getBean("dataSource");
			Connection connection = null;
			try {
				connection = dataSource.getConnection();
				String namequery = "select name from ggkz_user_info where user_id = ?";
				PreparedStatement statementPosiDepartQuery = connection
						.prepareStatement(namequery);
				statementPosiDepartQuery.setString(1, userId);
				ResultSet resultSetAttach = statementPosiDepartQuery
						.executeQuery();
				while (resultSetAttach != null && resultSetAttach.next()) {
					username = resultSetAttach.getString("name");
				}
				// 释放资源
				if (resultSetAttach != null) {
					resultSetAttach.close();
				}
				statementPosiDepartQuery.close();
			} catch (Exception e) {
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}

		}
		logger.debug("用户姓名是[{}]", username);
		addParameter("username", username);
	}
}
