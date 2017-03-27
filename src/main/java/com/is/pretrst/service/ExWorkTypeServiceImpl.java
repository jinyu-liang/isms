package com.is.pretrst.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.ExWorkTypeDaoImpl;
import com.is.pretrst.entity.ExWorkType;
import com.is.pretrst.entity.query.ExWorkTypeQuery;

/**
 *
 * @ClassName: ExWorkTypeServiceImpl
 * @Description: ExWorkType表对应的服务类
 * @author 
 * @date 2013-12-18 15:07:38 *
 */
@Component
@Transactional 
public class ExWorkTypeServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(ExWorkTypeServiceImpl.class);
	@Autowired
	private ExWorkTypeDaoImpl exWorkTypeDaoImpl;
	
	/**
	 * 分页查询
	 * @param queryEntity
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page pageQuery(ExWorkTypeQuery queryEntity){
	   return exWorkTypeDaoImpl.pageQuery("ExWorkType.selectByPage", queryEntity);
	}
	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	public int insert(ExWorkType entity){
	    return exWorkTypeDaoImpl.insert(entity);
	}
	/**
	 * 查询一个对象 
	 * @param entity
	 * @return
	 */
	public ExWorkType selectOneByEntity(ExWorkType entity){
	    return exWorkTypeDaoImpl.selectOneByEntity(entity);
	}
	/**
	 * 更新对象
	 * @param entity
	 * @return
	 */
	public int updateByEntity(ExWorkType entity){
	    return exWorkTypeDaoImpl.updateByEntity(entity);
	}
	
	/**
     * 删除对象
     * @param entity
     * @return
     */
	public int deleteByEntity(ExWorkType entity){
	    return exWorkTypeDaoImpl.deleteByEntity(entity);
	}
	
    public List<ExWorkType> selectEntity(ExWorkType queryEntity)
    {
        return  exWorkTypeDaoImpl.selectByEntity(queryEntity);
    }
}
