package com.is.mobile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.dao.GgkzUserRoleDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.GgkzUserRole;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.pretrst.dao.DReportImageDaoImpl;
import com.is.pretrst.dao.ExPersonManagerDaoImpl;
import com.is.pretrst.service.ExPersonManagerServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>文件名称: MobileSendMetralServiceImpl.java</p>
 * <p>文件描述: 手机登录验证服务实现</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要:
 * <p>其他说明:只要能够请求到本方法，即有采购的的权限</p>
 * <p>完成日期：2014年9月14日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Component
@Transactional
public class MobileGetAndroidAuthServiceImpl
{
    @Autowired
    private ExPersonManagerServiceImpl exPersonManagerServiceImpl;
    @Autowired
    private ExPersonManagerDaoImpl exPersonManagerDaoImpl;
    @Autowired
    private GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl;
    @Autowired
    GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
    public GgkzUserRoleDaoImpl getGgkzUserRoleDaoImpl() {
		return ggkzUserRoleDaoImpl;
	}

	public void setGgkzUserRoleDaoImpl(GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl) {
		this.ggkzUserRoleDaoImpl = ggkzUserRoleDaoImpl;
	}

	//采购申请图片
    @Autowired
    private DReportImageDaoImpl dReportImageDaoImpl;
    String    statusCd ="";


	public ExPersonManagerServiceImpl getExPersonManagerServiceImpl() {
		return exPersonManagerServiceImpl;
	}



	public void setExPersonManagerServiceImpl(
			ExPersonManagerServiceImpl exPersonManagerServiceImpl) {
		this.exPersonManagerServiceImpl = exPersonManagerServiceImpl;
	}



	public ExPersonManagerDaoImpl getExPersonManagerDaoImpl() {
		return exPersonManagerDaoImpl;
	}



	public void setExPersonManagerDaoImpl(
			ExPersonManagerDaoImpl exPersonManagerDaoImpl) {
		this.exPersonManagerDaoImpl = exPersonManagerDaoImpl;
	}



	public DReportImageDaoImpl getdReportImageDaoImpl() {
		return dReportImageDaoImpl;
	}



	public void setdReportImageDaoImpl(DReportImageDaoImpl dReportImageDaoImpl) {
		this.dReportImageDaoImpl = dReportImageDaoImpl;
	}



	public String getStatusCd() {
		return statusCd;
	}



	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	public List<String> getRoles(String userId) {
		GgkzUserRole queryEntity=new GgkzUserRole();
		queryEntity.setUserId(userId);
		// TODO Auto-generated method stub
		return ggkzUserRoleDaoImpl.selectRolesByUserId(queryEntity);
	}
	/**
	 * 外线人员已上传列表 传入参数：三个参数缺一不可
	 * userId:用户名 
	 * password:密码
	 * mobileSn:手机sn注册码
	 * mobileId:手机Id 
	 * 返回参数说明： 
	 * 0_没有该用户 
	 * 1_手机sn没有注册 
	 * 2_密码错误
	 * 3_登陆成功,可以获取用户在安卓客户端的所有操作权限列表
	 * 
	 */
	public String getLoginFlag(String userId, String password, String mobileSn,
			String mobileId) {
		String getPassword = "";
		GgkzUserInfo entity=new GgkzUserInfo();
		entity.setName(userId);
		entity.setLoginName(userId);
		GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectLoginEntity(entity);
		if (userIndo == null) {
	          OperLogUtil.insertMobileOperLog(userId, userId,PublicDict.MODEL_USER,
			"安卓端登录", PublicDict.OPER_LOG_EVENT_LOGIN, "登录验证", "手机端登录，系统中没有该用户", "登录失败",
            "手机端用户登录，系统中没有该用户", "sys_oper_log");
			return "0";
		} else {
			getPassword=userIndo.getNewPassword();
			if(StringUtils.isEmpty(getPassword)){
				getPassword=userIndo.getPassword();
			}
			String mobileFlag=getMobileFlag(userIndo.getUserId(),mobileSn,mobileId);
			if(mobileFlag.equals("0")){
		          OperLogUtil.insertMobileOperLog(userIndo.getName(), userId,PublicDict.MODEL_USER,
		                  "安卓端登录", PublicDict.OPER_LOG_EVENT_LOGIN, "登录验证", "手机端登录，手机在服务器端没有注册", "登录失败",
		                  "手机端用户登录，手机没有注册", "sys_oper_log");
				return "1";
			}
			if(!password.equals(getPassword)){
		          OperLogUtil.insertMobileOperLog(userIndo.getName(), userId,PublicDict.MODEL_USER,
		                  "安卓端登录", PublicDict.OPER_LOG_EVENT_LOGIN, "登录验证", "手机端登录，输入密码错误", "登录失败",
		                  "手机端用户登录，输入密码错误", "sys_oper_log");
		          
				return "2";
			}else{
		          OperLogUtil.insertMobileOperLog(userIndo.getName(), userId,PublicDict.MODEL_USER,
		                  "安卓端登录", PublicDict.OPER_LOG_EVENT_LOGIN, "登录验证", "手机端登录,用户名密码验证成功", "登录成功",
		                  "手机端用户登录，登录成功", "sys_oper_log");
				return "3";
			}
		}
	}
	/**
	 * 设备是否注册查询,如果第一次用设备登录，则注册该设备，一个手机只能注册给一个用户
	 * @param mobileSn
	 * @param mobileId
	 * @return 1_已经注册,0_未注册
	 */
	private String getMobileFlag(String userId,String mobileSn, String mobileId) {
		String mobileFlag="0";
		GgkzUserInfoQuery entity=new GgkzUserInfoQuery();
		entity.setMobileId(mobileId);
		entity.setMobileSn(mobileSn);
		//获取当前手机是否注册
		List<GgkzUserInfo> userList = ggkzUserInfoDaoImpl.selectByEntity(entity);
		 entity=new GgkzUserInfoQuery();
		entity.setUserId(userId);
		//获取当前用户是否有效
		GgkzUserInfo userInfo = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		mobileFlag=userInfo.getOrgId();
		if(mobileFlag.equals("1") && userList.size()==0){
			//注册mobileId和mobileSn
			userInfo.setMobileId(mobileId);
			userInfo.setMobileSn(mobileSn);
			ggkzUserInfoDaoImpl.updateByEntity(userInfo);
	         OperLogUtil.insertMobileOperLog(userInfo.getName(), userId,PublicDict.MODEL_USER,
	                  "安卓端登录", PublicDict.OPER_LOG_EVENT_LOGIN, "第一次登录验证注册设备", "手机端登录,注册设备成功", "注册设备成功",
	                  "手机端用户登录，注册设备", "sys_oper_log");
			return "1";
		}
		if(userList.size()==0){
			//注册mobileId和mobileSn
			userInfo.setMobileId(mobileId);
			userInfo.setMobileSn(mobileSn);
			ggkzUserInfoDaoImpl.updateByEntity(userInfo);
	         OperLogUtil.insertMobileOperLog(userInfo.getName(), userId,PublicDict.MODEL_USER,
	                  "安卓端登录", PublicDict.OPER_LOG_EVENT_LOGIN, "第一次登录验证注册设备", "手机端登录,注册设备成功", "注册设备成功",
	                  "手机端用户登录，注册设备", "sys_oper_log");
			return "0";
		}else{
			mobileFlag=userList.get(0).getOrgId();
			if(mobileFlag.equals("0")||StringUtils.isEmpty(mobileFlag)){
				return "0";
			}else{
				return "1";	
			}
		}
	
		 
	}
	/**
	 * 设备是否注册查询,如果第一次用设备登录，则注册该设备，一个手机只能注册给一个用户
	 * @param mobileSn
	 * @param mobileId
	 * @return 1_已经注册,0_未注册
	 */
	public String getMobileServerFlag(String mobileSn, String mobileId) {
		String mobileFlag="0";
		GgkzUserInfoQuery entity=new GgkzUserInfoQuery();
		entity.setMobileId(mobileId);
		entity.setMobileSn(mobileSn);
		//获取当前手机是否注册
		GgkzUserInfo userInfo = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
		if(userInfo!=null){
			mobileFlag=userInfo.getOrgId();	
		}
		return mobileFlag;
	}
	
	public GgkzUserInfo getUserByAndroid(String userId) {
        GgkzUserInfo entity=new GgkzUserInfo();
        entity.setName(userId);
        entity.setLoginName(userId);
        GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectLoginEntity(entity);
   
        return userIndo;
    }

	public String getUserIdByAndroid(String userId) {
		GgkzUserInfo entity=new GgkzUserInfo();
		entity.setName(userId);
		entity.setLoginName(userId);
		GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectLoginEntity(entity);
		if(userIndo!=null){
			return userIndo.getUserId();
		}else{
			return userId;
		}
	
	}
	public String getUserNameByAndroid(String userId) {
		GgkzUserInfo entity=new GgkzUserInfo();
		entity.setName(userId);
		entity.setLoginName(userId);
		GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectLoginEntity(entity);
		if(userIndo!=null){
			return userIndo.getName();
		}else{
			return userId;
		}
	
	}
}



