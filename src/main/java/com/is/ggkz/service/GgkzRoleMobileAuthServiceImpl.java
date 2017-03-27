package com.is.ggkz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzRoleMobileAuthDaoImpl;
import com.is.ggkz.entity.GgkzRoleMobileAuth;
import com.is.ggkz.entity.query.GgkzRoleMobileAuthQuery;

/**
 * 
 * @ClassName: GgkzRoleMobileAuthServiceImpl
 * @Description: GgkzRoleMobileAuth表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:30 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzRoleMobileAuthServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzRoleMobileAuthServiceImpl.class);

	public GgkzRoleMobileAuthDaoImpl ggkzRoleMobileAuthDaoImpl;

	@Autowired
	public void setGgkzRoleMobileAuthDaoImpl(
			GgkzRoleMobileAuthDaoImpl ggkzRoleMobileAuthDaoImpl) {
		this.ggkzRoleMobileAuthDaoImpl = ggkzRoleMobileAuthDaoImpl;
	}

	/**
	 * 增加GgkzRoleAuth对象
	 * 
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int insertGgkzRoleAuth(GgkzRoleMobileAuth ggkzRoleMobileAuth) {
		return ggkzRoleMobileAuthDaoImpl.insert(ggkzRoleMobileAuth);
	}

	/**
	 * 删除GgkzRoleAuth对象
	 * 
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int deleteGgkzRoleAuth(GgkzRoleMobileAuth ggkzRoleMobileAuth) {

		return ggkzRoleMobileAuthDaoImpl.deleteByEntity(ggkzRoleMobileAuth);
	}

	/**
	 * 批量删除GgkzRoleAuth对象
	 * 
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int deleteGgkzRoleAuth(List<GgkzRoleMobileAuth> ggkzRoleAuthList) {
		int i = 1;
		try {
			for (GgkzRoleMobileAuth ggkzRoleAuth : ggkzRoleAuthList) {
				ggkzRoleMobileAuthDaoImpl.deleteByEntity(ggkzRoleAuth);
			}
		} catch (Exception e) {
			i = 0;
		}
		return i;

	}

	/**
	 * 更新GgkzRoleAuth对象
	 * 
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int updateGgkzRoleAuth(GgkzRoleMobileAuth ggkzRoleAuth) {
		return ggkzRoleMobileAuthDaoImpl.updateByEntity(ggkzRoleAuth);
	}

	/**
	 * 查询GgkzRoleAuth单个对象
	 * 
	 * @param ggkzRoleAuth
	 * @return GgkzRoleAuth
	 */
	public GgkzRoleMobileAuth selectOneGgkzRoleAuth(
			GgkzRoleMobileAuth ggkzRoleAuth) {
		return ggkzRoleMobileAuthDaoImpl.selectOneByEntity(ggkzRoleAuth);
	}

	/**
	 * 根据id查询GgkzRoleAuth对象
	 * 
	 * @param ggkzRoleAuth
	 * @return GgkzRoleAuth
	 */
	public GgkzRoleMobileAuth selectByIdGgkzRoleAuth(String authId,
			String roleId) {
		GgkzRoleMobileAuth ggkzRoleAuth = new GgkzRoleMobileAuth();
		ggkzRoleAuth.setAuthId(authId);
		ggkzRoleAuth.setRoleId(roleId);
		return ggkzRoleMobileAuthDaoImpl.selectOneByEntity(ggkzRoleAuth);
	}

	/**
	 * 查询GgkzRoleAuth对象
	 * 
	 * @param ggkzRoleAuth
	 * @return List<GgkzRoleAuth>
	 */
	public List<GgkzRoleMobileAuth> selectGgkzRoleAuth(
			GgkzRoleMobileAuth ggkzRoleAuth) {
		return ggkzRoleMobileAuthDaoImpl.selectByEntity(ggkzRoleAuth);
	}

	/**
	 * 查询GgkzRoleAuth对象集合
	 * 
	 * @param ggkzRoleAuth
	 * @return List<GgkzRoleAuth>
	 */
	public List<GgkzRoleMobileAuth> selectAll() {
		return ggkzRoleMobileAuthDaoImpl.selectAll();
	}

	/**
	 * 分布查询
	 * 
	 * @param query
	 * @return
	 */
	public Page pageQuery(GgkzRoleMobileAuthQuery query) {
		return ggkzRoleMobileAuthDaoImpl.pageQuery("GgkzRoleAuth.selectByPage",
				query);
	}
}
