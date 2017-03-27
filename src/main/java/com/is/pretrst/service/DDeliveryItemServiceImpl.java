package com.is.pretrst.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.DDeliveryItemDaoImpl;
import com.is.pretrst.entity.DDeliveryItem;
import com.is.pretrst.entity.query.DDeliveryItemQuery;

/**
 *
 * @ClassName: DDeliveryItemServiceImpl
 * @Description: DDeliveryItem表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:51 *
 */
@Component
@Transactional 
public class DDeliveryItemServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DDeliveryItem.class);
	private DDeliveryItemDaoImpl deliveryItemDaoImpl;
	@Autowired
    public void setDeliveryItemDaoImpl(DDeliveryItemDaoImpl deliveryItemDaoImpl)
    {
        this.deliveryItemDaoImpl = deliveryItemDaoImpl;
    }
	
	/**
	 * 要页查询出门列表
	 * @param queryEntity
	 * @return page
	 */
	public Page queryPage(DDeliveryItemQuery queryEntity){
	    return deliveryItemDaoImpl.pageQuery("DDeliveryItem.selectByPage", queryEntity);
	    
	}
}
