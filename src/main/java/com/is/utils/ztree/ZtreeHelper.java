package com.is.utils.ztree;

import java.util.List;

/**
 * Ztree树状结构的帮助类，主要是将数据转换成ZTree的Json数据格式形式
 * 
 * @author 
 * 
 */

public class ZtreeHelper {

	/**
	 * 将存放json实体类的List转换成JSON数据
	 * 
	 * @param datalist
	 *            存放json实体类的List List<ZTreeData> datalist
	 * @return String 字符串形式的JSON数据
	 */
	static public String ztreeData(List<ZTreeData> datalist) {
		StringBuffer strbuff = new StringBuffer();
		if (datalist.size() > 0) {
			for (ZTreeData zd : datalist) {
				strbuff.append(zd.toString());
			}
		}
		if (strbuff.toString().length() < 1) {
			return "";
		}
		return strbuff.toString().substring(0, strbuff.toString().length() - 1);
	}
}