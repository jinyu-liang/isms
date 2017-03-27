package com.is.ggkz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzResourceInfoDaoImpl;
import com.is.ggkz.entity.GgkzResourceInfo;
import com.is.ggkz.entity.query.GgkzResourceInfoQuery;

/**
 *
 * @ClassName: GgkzResourceInfoServiceImpl
 * @Description: GgkzResourceInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:25 *
 */
//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzResourceInfoServiceImpl{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzResourceInfoServiceImpl.class);
	
	public GgkzResourceInfoDaoImpl ggkzResourceInfoDaoImpl;

	@Autowired
	public void setGgkzResourceInfoDaoImpl(GgkzResourceInfoDaoImpl ggkzResourceInfoDaoImpl) {
		this.ggkzResourceInfoDaoImpl = ggkzResourceInfoDaoImpl;
	}
	
	/**
	 * 添加一个资源信息
	 * @param resource
	 * @return 1:添加成功，0:添加失败
	 * @author 
	 */
	public int saveResourceInfo(GgkzResourceInfo resource){
		return ggkzResourceInfoDaoImpl.insert(resource);
	}
	
	/**
	 * 根据ID删除资源
	 * @author 
	 * @return 1:删除成功 0：删除失败
	 * @param resource
	 */
	public int deleteResourceById(GgkzResourceInfo resource) {
		return ggkzResourceInfoDaoImpl.deleteByEntity(resource);
	}

	/**
	 * 修改资源
	 * @return 1:成功 0：失败
	 * @author 
	 * @param resource
	 * @return
	 */
	public int updateResourceByEntity(GgkzResourceInfo resource) {
		return ggkzResourceInfoDaoImpl.updateByEntity(resource);
	}

	/**
	 * 根据资源对象查询资源
	 * @author 
	 * @param resource
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<GgkzResourceInfo> selectByEntity(GgkzResourceInfo resource) {
		return ggkzResourceInfoDaoImpl.selectByEntity(resource);
	}

	/**
	 * 查询一个资源对象
	 * @author 
	 * @param resource
	 * @return
	 */
	@Transactional(readOnly=true)
	public GgkzResourceInfo selectOneByEntity(GgkzResourceInfo resource) {
		return ggkzResourceInfoDaoImpl.selectOneByEntity(resource);
	}

	/**
	 * 批量删除资源
	 * @author 
	 * @param ids
	 * @return
	 */
	@Transactional
	public int deleteManyResource(List<String> ids) {
		int res = ggkzResourceInfoDaoImpl.deleteManyResource(ids);
		return res;
		//throw new RuntimeException("测试事务");
	}
	
	/**
	 * 分布查詢
	 * @author 
	 * @param query
	 * @return
	 */
	public Page pageQuery(GgkzResourceInfoQuery query){
		return ggkzResourceInfoDaoImpl.pageQuery("GgkzResourceInfo.selectByPage",query);
	}

}
