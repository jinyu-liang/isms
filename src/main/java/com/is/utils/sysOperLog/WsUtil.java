package com.is.utils.sysOperLog;

import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.utils.StringUtils;

public class WsUtil {

 	/**
	 * 根据用户id获得用户名称
	 * @param userId 用户id
	 * @return
	 */ 
	public static String getUserName(String userId){
		if(!"".equals(userId)&&userId!=null){
			GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl = (GgkzUserInfoServiceImpl) SpringContextHolder
			.getBean("ggkzUserInfoServiceImpl");
			GgkzUserInfo ggkzUserInfo = new GgkzUserInfo();
			ggkzUserInfo.setUserId(userId);
			ggkzUserInfo = ggkzUserInfoServiceImpl.selectUserByEntity(ggkzUserInfo);
			if(ggkzUserInfo!=null && !StringUtils.isEmpty(ggkzUserInfo.getName())){
				return ggkzUserInfo.getName();
			}
		}
		return "" ;
	} 
}
