package com.is.ggkz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzRoleAuthDaoImpl;
import com.is.ggkz.entity.GgkzRoleAuth;
import com.is.ggkz.entity.query.GgkzRoleAuthQuery;

/**
 *
 * @ClassName: GgkzRoleAuthServiceImpl
 * @Description: GgkzRoleAuth表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:30 *
 */
//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzRoleAuthServiceImpl{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzRoleAuthServiceImpl.class);
	
	public GgkzRoleAuthDaoImpl ggkzRoleAuthDaoImpl;

	@Autowired
	public void setGgkzRoleAuthDaoImpl(GgkzRoleAuthDaoImpl ggkzRoleAuthDaoImpl) {
		this.ggkzRoleAuthDaoImpl = ggkzRoleAuthDaoImpl;
	}
	/**
	 * 增加GgkzRoleAuth对象
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int insertGgkzRoleAuth(GgkzRoleAuth ggkzRoleAuth){
		return ggkzRoleAuthDaoImpl.insert(ggkzRoleAuth);
	}
	/**
	 * 删除GgkzRoleAuth对象
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int deleteGgkzRoleAuth(GgkzRoleAuth ggkzRoleAuth){
		
		return ggkzRoleAuthDaoImpl.deleteByEntity(ggkzRoleAuth);
	} 
	/**
	 * 批量删除GgkzRoleAuth对象
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int deleteGgkzRoleAuth(List<GgkzRoleAuth> ggkzRoleAuthList){
		int i =1;
		try{
			for(GgkzRoleAuth ggkzRoleAuth:ggkzRoleAuthList ){
				 ggkzRoleAuthDaoImpl.deleteByEntity(ggkzRoleAuth);
			}
		}catch(Exception e){
			i=0;
		}
		return i;
		
	} 
	/**
	 * 更新GgkzRoleAuth对象
	 * @param ggkzRoleAuth
	 * @return int
	 */
	public int updateGgkzRoleAuth(GgkzRoleAuth ggkzRoleAuth){
		return ggkzRoleAuthDaoImpl.updateByEntity(ggkzRoleAuth);
	}
	/**
	 * 查询GgkzRoleAuth单个对象
	 * @param ggkzRoleAuth
	 * @return GgkzRoleAuth
	 */
	public GgkzRoleAuth selectOneGgkzRoleAuth(GgkzRoleAuth ggkzRoleAuth){
		return ggkzRoleAuthDaoImpl.selectOneByEntity(ggkzRoleAuth);
	}
	/**
	 * 根据id查询GgkzRoleAuth对象
	 * @param ggkzRoleAuth
	 * @return GgkzRoleAuth
	 */
	public GgkzRoleAuth selectByIdGgkzRoleAuth(String authId,String  roleId){
		GgkzRoleAuth ggkzRoleAuth =new GgkzRoleAuth();
		ggkzRoleAuth.setAuthId(authId);
		ggkzRoleAuth.setRoleId(roleId);
		return ggkzRoleAuthDaoImpl.selectOneByEntity(ggkzRoleAuth);
	}
	
	/**
	 * 查询GgkzRoleAuth对象
	 * @param ggkzRoleAuth
	 * @return List<GgkzRoleAuth>
	 */
	public List<GgkzRoleAuth> selectGgkzRoleAuth(GgkzRoleAuth ggkzRoleAuth){
		return ggkzRoleAuthDaoImpl.selectByEntity(ggkzRoleAuth);
	}
	/**
	 * 查询GgkzRoleAuth对象集合
	 * @param ggkzRoleAuth
	 * @return List<GgkzRoleAuth>
	 */
	public List<GgkzRoleAuth> selectAll(){
		return ggkzRoleAuthDaoImpl.selectAll();
	}
	/**
	 * 分布查询
	 * @param query
	 * @return
	 */
	public Page pageQuery(GgkzRoleAuthQuery query){
		return ggkzRoleAuthDaoImpl.pageQuery("GgkzRoleAuth.selectByPage",query);
	}
}
