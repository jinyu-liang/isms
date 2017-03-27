package com.is.utils.blobUtil;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

/**
 * 实现blob到string类型的转换，编码格式使用utf-8解决乱码
 * 
 * @author 
 * 
 */
public class BlobAndStringHandler extends BaseTypeHandler {
	/**
	 * 插入数据时转换成byte[]插入数据库
	 * 
	 * @param ps
	 *            数据sql
	 * @param i
	 * @param parameter
	 * @param jdbcType
	 * @author 
	 */
	public void setNonNullParameter(PreparedStatement ps, int i,
			Object parameter, JdbcType jdbcType) throws SQLException {
		byte[] bytes = null;
		if (parameter instanceof String) {
			String para = (String) parameter;
			bytes = para.getBytes();
		} else {
			bytes = (byte[]) parameter;
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
		ps.setBinaryStream(i, bis, bytes.length);
	}

	/**
	 * 插入数据时转换成byte[]插入数据库
	 * 
	 * @param rs
	 *            数据集
	 * @param columnName
	 *            列名称
	 * @return 返回字符串类型结果
	 * @author 
	 */
	public Object getNullableResult(ResultSet rs, String columnName)
			throws SQLException {
		Blob blob = rs.getBlob(columnName);
		byte[] returnValue = null;
		if (null != blob) {
			returnValue = blob.getBytes(1, (int) blob.length());
			try {
				return new String(returnValue, "utf-8");
			} catch (UnsupportedEncodingException e) {
			}
		}

		return "";
	}

	/**
	 * 插入数据时转换成byte[]插入数据库
	 * 
	 * @param cs
	 * @param columnIndex
	 * @return 返回字符串类型结果
	 * @author 
	 */
	public Object getNullableResult(CallableStatement cs, int columnIndex)
			throws SQLException {
		Blob blob = cs.getBlob(columnIndex);
		byte[] returnValue = null;
		if (null != blob) {
			returnValue = blob.getBytes(1, (int) blob.length());
		}
		try {
			return new String(returnValue, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return "";
	}
}
