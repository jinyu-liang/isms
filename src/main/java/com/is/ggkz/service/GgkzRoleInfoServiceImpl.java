package com.is.ggkz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.base.security.SpringSecurityUtils;
import com.is.ggkz.dao.GgkzAuthInfoDaoImpl;
import com.is.ggkz.dao.GgkzAuthMobileInfoDaoImpl;
import com.is.ggkz.dao.GgkzRoleAuthDaoImpl;
import com.is.ggkz.dao.GgkzRoleInfoDaoImpl;
import com.is.ggkz.dao.GgkzRoleMobileAuthDaoImpl;
import com.is.ggkz.entity.GgkzAuthInfo;
import com.is.ggkz.entity.GgkzAuthMobileInfo;
import com.is.ggkz.entity.GgkzRoleAuth;
import com.is.ggkz.entity.GgkzRoleInfo;
import com.is.ggkz.entity.GgkzRoleMobileAuth;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.ggkz.entity.query.GgkzRoleInfoQuery;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: GgkzRoleInfoServiceImpl
 * @Description: GgkzRoleInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:01 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzRoleInfoServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzRoleInfoServiceImpl.class);

	public GgkzRoleInfoDaoImpl ggkzRoleInfoDaoImpl;
	public GgkzAuthInfoDaoImpl ggkzAuthInfoDaoImpl;
	public GgkzRoleAuthDaoImpl ggkzRoleAuthDaoImpl;
	public GgkzAuthMobileInfoDaoImpl ggkzAuthMobileInfoDaoImpl;
	public GgkzRoleMobileAuthDaoImpl ggkzRoleMobileAuthDaoImpl;

	@Autowired
	public void setGgkzRoleInfoDaoImpl(GgkzRoleInfoDaoImpl ggkzRoleInfoDaoImpl) {
		this.ggkzRoleInfoDaoImpl = ggkzRoleInfoDaoImpl;
	}

	@Autowired
	public void setGgkzAuthInfoDaoImpl(GgkzAuthInfoDaoImpl ggkzAuthInfoDaoImpl) {
		this.ggkzAuthInfoDaoImpl = ggkzAuthInfoDaoImpl;
	}

	@Autowired
	public void setGgkzRoleAuthDaoImpl(GgkzRoleAuthDaoImpl ggkzRoleAuthDaoImpl) {
		this.ggkzRoleAuthDaoImpl = ggkzRoleAuthDaoImpl;
	}

	@Autowired
	public void setGgkzAuthMobileInfoDaoImpl(
			GgkzAuthMobileInfoDaoImpl ggkzAuthMobileInfoDaoImpl) {
		this.ggkzAuthMobileInfoDaoImpl = ggkzAuthMobileInfoDaoImpl;
	}

	@Autowired
	public void setGgkzRoleMobileAuthDaoImpl(
			GgkzRoleMobileAuthDaoImpl ggkzRoleMobileAuthDaoImpl) {
		this.ggkzRoleMobileAuthDaoImpl = ggkzRoleMobileAuthDaoImpl;
	}

	/**
	 * 增加GgkzRoleInfo对象
	 * 
	 * @param ggkzRoleInfo
	 * @return int
	 */
	public int saveRole(GgkzRoleInfo ggkzRoleInfo) {
		ggkzRoleInfo.setOperTime(new Date());
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			ggkzRoleInfo.setOperUserId(user.getUserId());
		}
		ggkzRoleInfo.setRoleId(KeyGen.getCommonKeyGen(PublicDict.ROLE_INFO));// 角色信息id
		int result = ggkzRoleInfoDaoImpl.insert(ggkzRoleInfo);
		// 添加日志
		OperLogUtil.insertOperLog(ggkzRoleInfo.getRoleId(),
				PublicDict.MODEL_GGKZ, "角色信息", PublicDict.OPER_LOG_EVENT_ADD,
				"添加", "添加角色", "添加成功", "添加角色", "ggkz_role_info");
		return result;
	}

	/**
	 * 更新GgkzRoleInfo对象
	 * 
	 * @param ggkzRoleInfo
	 * @return int
	 */
	public int updateRole(GgkzRoleInfo ggkzRoleInfo) {
		ggkzRoleInfo.setOperTime(new Date());
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		if (user != null) {
			ggkzRoleInfo.setOperUserId(user.getUserId());
		}
		int result = ggkzRoleInfoDaoImpl.updateByEntity(ggkzRoleInfo);
		// 添加日志
		OperLogUtil.insertOperLog(ggkzRoleInfo.getRoleId(),
				PublicDict.MODEL_GGKZ, "角色信息",
				PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "修改角色", "修改成功", "修改角色",
				"ggkz_role_info");
		return result;
	}

	/**
	 * 删除GgkzRoleInfo对象
	 * 
	 * @param ggkzRoleInfo
	 * @return int
	 */
	public int deleteGgkzRoleInfo(GgkzRoleInfo entity) {
		int result = 0;
		result += ggkzRoleInfoDaoImpl.deleteByEntity(entity);// 删除角色信息
		GgkzRoleAuth roleAuth = new GgkzRoleAuth();
		roleAuth.setRoleId(entity.getRoleId());
		result += ggkzRoleAuthDaoImpl.deleteByEntity(roleAuth);// 删除角色对应的权限信息
		OperLogUtil.insertOperLog(entity.getRoleId(), PublicDict.MODEL_GGKZ,
				"角色信息", PublicDict.OPER_LOG_EVENT_DEL, "删除", "删除角色", "删除成功",
				"删除角色", "ggkz_role_info");
		return result;
	}

	/**
	 * 批量删除多个GgkzRoleInfo对象
	 * 
	 * @param List
	 *            <String> ids
	 * @return int
	 */
	@Transactional
	public void deleteManyRoleInfo(String roleIds) {
		List<String> roleIdList = new ArrayList<String>();

		String[] str = roleIds.split(",");// 分隔角色id成list,用于批量删除角色
		for (int i = 0; i < str.length; i++) {
			roleIdList.add(str[i]);
		}
		ggkzRoleAuthDaoImpl.deleteManyRoleInfo(roleIdList);// 删除角色权限对象
		ggkzRoleInfoDaoImpl.deleteManyRoleInfo(roleIdList);// 删除角色对象
		ggkzRoleMobileAuthDaoImpl.deleteManyRoleInfo(roleIdList);
		// 添加日志
		OperLogUtil.insertOperLog(roleIds, PublicDict.MODEL_GGKZ, "角色信息",
				PublicDict.OPER_LOG_EVENT_DEL, "删除", "批量删除角色和角色与权限关联关系",
				"删除成功", "删除角色和角色与权限关联关系", "ggkz_role_info,ggkz-role-auth");
	}

	/**
	 * 查询GgkzRoleInfo单个对象
	 * 
	 * @param ggkzRoleInfo
	 * @return GgkzRoleInfo
	 */
	@Transactional(readOnly = true)
	public GgkzRoleInfo selectOneGgkzRoleInfo(GgkzRoleInfo ggkzRoleInfo) {
		return ggkzRoleInfoDaoImpl.selectOneByEntity(ggkzRoleInfo);
	}

	/**
	 * 查询GgkzRoleInfo对象
	 * 
	 * @param ggkzRoleInfo
	 * @return List<GgkzRoleInfo>
	 */
	@Transactional(readOnly = true)
	public List<GgkzRoleInfo> selectGgkzRoleInfo(GgkzRoleInfo ggkzRoleInfo) {
		return ggkzRoleInfoDaoImpl.selectByEntity(ggkzRoleInfo);
	}

	/**
	 * 查询GgkzRoleInfo对象集合
	 * 
	 * @param ggkzRoleInfo
	 * @return List<GgkzRoleInfo>
	 */
	@Transactional(readOnly = true)
	public List<GgkzRoleInfo> selectAll() {
		return ggkzRoleInfoDaoImpl.selectAll();
	}

	/**
	 * 分布查詢
	 * 
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageQuery(GgkzRoleInfoQuery query) {
		return ggkzRoleInfoDaoImpl
				.pageQuery("GgkzRoleInfo.selectByPage", query);
	}

	/**
	 * 根据Id查询角色对象
	 * 
	 * @param authId
	 * @return
	 */
	@Transactional(readOnly = true)
	public GgkzRoleInfo getRoleInfo(String authId) {
		GgkzRoleInfo info = new GgkzRoleInfo();
		info.setRoleId(authId);
		return selectOneGgkzRoleInfo(info);
	}

	/**
	 * 查询权限对象集合
	 * 
	 * @param ggkzRoleInfo
	 * @return List<GgkzRoleInfo>
	 */
	@Transactional(readOnly = true)
	public List<GgkzAuthInfo> selectAuthInfo() {
		return ggkzAuthInfoDaoImpl.selectAll();
	}

	public List<GgkzAuthMobileInfo> selectAuthMobileInfo() {
		return ggkzAuthMobileInfoDaoImpl.selectAll();
	}

	/**
	 * 根据权限id查询权限对象集合
	 * 
	 * @param authRoleIds
	 * @return List<GgkzAuthInfo>
	 */
	@Transactional(readOnly = true)
	public List<GgkzAuthInfo> selectAuthInfoByAuthId(String authRoleIds) {
		GgkzAuthInfo ggkzAuthInfo = new GgkzAuthInfo();
		List<GgkzAuthInfo> ggkzAuthInfoList = new ArrayList<GgkzAuthInfo>();
		if (null != authRoleIds && !"".equals(authRoleIds)) {
			String[] authRoleId = authRoleIds.split(",");
			for (int i = 0; i < authRoleId.length; i++) {
				ggkzAuthInfo.setAuthId(authRoleId[i]);
				ggkzAuthInfoList.add(ggkzAuthInfoDaoImpl
						.selectOneByEntity(ggkzAuthInfo));
			}
		}
		return ggkzAuthInfoList;
	}

	/**
	 * 根据角色id查询权限对象集合-根据角色对象的id查询角色权限对象集合。然后遍历权限对象集合获得所有权限对象的id,去查询权限对象
	 * 
	 * @param roleId
	 * @return List<GgkzAuthInfo>
	 */
	@Transactional(readOnly = true)
	public List<GgkzAuthInfo> selectAuthInfoByRoleId(String roleId) {
		// GgkzAuthInfo ggkzAuthInfo =new GgkzAuthInfo();
		// List<GgkzAuthInfo> ggkzAuthInfoList = new ArrayList<GgkzAuthInfo>();
		// if(null!=roleId&&!"".equals(roleId)){
		// List<GgkzRoleAuth> ggkzRoleAuthList = new ArrayList< GgkzRoleAuth>();
		// GgkzRoleAuth ggkzRoleAuth = new GgkzRoleAuth();
		// ggkzRoleAuth.setRoleId(roleId);
		// ggkzRoleAuthList =
		// ggkzRoleAuthDaoImpl.selectByEntity(ggkzRoleAuth);//查询角色权限对象
		// List<String> authIds=new ArrayList<String>() ;
		// for(GgkzRoleAuth roleAuth:ggkzRoleAuthList){//获得角色权限对象的id
		// if(roleAuth!=null&&roleAuth.getAuthId()!=""&&!"".equals(roleAuth.getAuthId())){
		// authIds.add(roleAuth.getAuthId());//封装权限id
		// }
		// }
		// if(null!=authIds&&!"".equals(authIds)&&authIds.size()!=0){
		// for(int i=0;i<authIds.size();i++){
		// ggkzAuthInfo.setAuthId(authIds.get(i));
		// ggkzAuthInfoList.add(ggkzAuthInfoDaoImpl.selectOneByEntity(ggkzAuthInfo));//查询权限对象
		// }
		// }
		// }
		List<String> roleIds = new ArrayList<String>();
		roleIds.add(roleId);
		return ggkzRoleAuthDaoImpl.selectAuthByRoles(roleIds);
	}

	/**
	 * 根据角色id查询权限对象集合-根据角色对象的id查询角色权限对象集合。然后遍历权限对象集合获得所有权限对象的id,去查询权限对象
	 * 
	 * @param roleId
	 * @return List<GgkzAuthInfo>
	 */
	@Transactional(readOnly = true)
	public List<GgkzAuthMobileInfo> selectAuthMobileInfoByRoleId(String roleId) {

		List<String> roleIds = new ArrayList<String>();
		roleIds.add(roleId);
		return ggkzRoleMobileAuthDaoImpl.selectAuthByRoles(roleIds);
	}

	/**
	 * 保存角色信息与角色权限对象
	 * 
	 * @param GgkzRoleInfo
	 *            角色信息对象
	 * @param String
	 *            authIds 所选择的权限Ids
	 * @return int
	 */
	public int saveRoleInfoAndAuth(GgkzRoleInfo entity, String[] authIds,
			String[] mobileCheckbox) {
		int result = 0;
		result += this.saveRole(entity);// 增加GgkzRoleInfo对象
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		if (authIds != null && authIds.length > 0) {// 如果为空就不添加角色权限对象
			for (int i = 0; i < authIds.length; i++) {// 循环添加角色权限对象
				GgkzRoleAuth ggkzRoleAuth = new GgkzRoleAuth();
				ggkzRoleAuth.setRoleId(entity.getRoleId());
				ggkzRoleAuth.setAuthId(authIds[i]);
				if (user != null) {
					ggkzRoleAuth.setOperUserId(user.getUserId());// 操作人员id
				}
				ggkzRoleAuth.setOperTime(new Date());
				result += ggkzRoleAuthDaoImpl.insert(ggkzRoleAuth);
			}
		}
		if (mobileCheckbox != null && mobileCheckbox.length > 0) {// 如果为空就不添加角色权限对象
			for (int i = 0; i < mobileCheckbox.length; i++) {// 循环添加角色权限对象
				GgkzRoleMobileAuth ggkzRoleAuth = new GgkzRoleMobileAuth();
				ggkzRoleAuth.setRoleId(entity.getRoleId());
				ggkzRoleAuth.setAuthId(mobileCheckbox[i]);
				result += ggkzRoleMobileAuthDaoImpl.insert(ggkzRoleAuth);
			}
		}
		// 添加日志
		OperLogUtil.insertOperLog(entity.getRoleId(), PublicDict.MODEL_GGKZ,
				"角色信息", PublicDict.OPER_LOG_EVENT_ADD, "增加", "添加角色与权限关联信息",
				"添加成功", "添加角色与权限关联信息", "ggkz_role_auth,ggkz_role_mobile_auth");
		return result;
	}

	/**
	 * 更新角色权限对象
	 * 
	 * @param GgkzRoleInfo
	 *            角色信息对象
	 * @param authIds
	 *            所选择的权限Ids
	 * @return int
	 */
	public int updateRoleInfoAndAuth(GgkzRoleInfo entity, String[] authIds,
			String[] mobileCheckbox) {
	    int result = 0;
	    result = this.updateRole(entity);// 更新GgkzRoleInfo对象
		List<String> strList = new ArrayList<String>();
		strList.add(entity.getRoleId());
		result += ggkzRoleAuthDaoImpl.deleteManyRoleInfo(strList);// 批量删除当前对象所对应的权限
		ggkzRoleMobileAuthDaoImpl.deleteManyRoleInfo(strList);
		if (authIds != null && authIds.length > 0) {
			for (int i = 0; i < authIds.length; i++) {
				GgkzRoleAuth roleAuth = new GgkzRoleAuth();
				roleAuth.setRoleId(entity.getRoleId());
				roleAuth.setAuthId(authIds[i]);
				UserDetail user = SpringSecurityUtils.getCurrentUser();
				if (user != null) {
					roleAuth.setOperUserId(user.getUserId());// 操作人员id
				}
				roleAuth.setOperTime(new Date());
				result += ggkzRoleAuthDaoImpl.insert(roleAuth);// 重新添加角色信息对应的权限
			}
		}
		if (mobileCheckbox != null && mobileCheckbox.length > 0) {
			for (int i = 0; i < mobileCheckbox.length; i++) {
				GgkzRoleMobileAuth roleAuth = new GgkzRoleMobileAuth();
				roleAuth.setRoleId(entity.getRoleId());
				roleAuth.setAuthId(mobileCheckbox[i]);
				result += ggkzRoleMobileAuthDaoImpl.insert(roleAuth);// 重新添加角色信息对应的权限
			}
		}
		// 添加日志
		OperLogUtil.insertOperLog(entity.getRoleId(), PublicDict.MODEL_GGKZ,
				"角色信息", PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "修改角色与权限关联关系",
				"修改成功", "修改角色与权限关联关系", "ggkz_role_auth");
		return result;
	}
}
