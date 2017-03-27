package com.is.pretrst.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.DExProgressDaoImpl;
import com.is.pretrst.entity.DExItem;
import com.is.pretrst.entity.query.DExItemQuery;

/**
 *
 * @ClassName: DExItemServiceImpl
 * @Description: DExItem表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:03 *
 */
@Component
@Transactional 
public class DExItemServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DExItem.class);

	@Autowired
	private DExProgressDaoImpl progressDaoImpl;
	         
	/**
	 * 查询分页
	 * @param queryEntity
	 * @return
	 */
	public Page pageQuery(DExItemQuery queryEntity){
        return progressDaoImpl.pageQuery("DExItem.selectByPage", queryEntity);
    }
}
