package com.is.pretrst.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.dao.GgkzDepartInfoDaoImpl;
import com.is.pretrst.dao.ExPersonInfoPayDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExPersonInfoPay;

@Component
@Transactional 
public class ExPersonInfoPayServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(ExPersonInfoPay.class);

	@Autowired
	private ExPersonInfoPayDaoImpl exPersonInfoPayDaoImpl;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByEntity(ExPersonInfoPay entity){
	  return   exPersonInfoPayDaoImpl.updateByEntity(entity);
	}
	
	
	
	/**
	 * 保险处理
	 * @param identyCard 身份证号
	 * @return 影响的行数 
	 */
	public int  perInfoDeal(String pid){
	    ExPersonInfoPay info = new ExPersonInfoPay();
	    info.setPid(pid);
	    info.setWork_status("2");
	    return exPersonInfoPayDaoImpl.perInfoDeal(info);
	}
	
	/**
	 * g
	 * @param pid
	 * @return
	 */
	public ExPersonInfoPay  getcuizhanginfo(String div_cd){
	    ExPersonInfoPay info = new ExPersonInfoPay();
	    info.setName(div_cd);
	    exPersonInfoPayDaoImpl =  (ExPersonInfoPayDaoImpl) SpringContextHolder.getBean("exPersonInfoPayDaoImpl");
	    return exPersonInfoPayDaoImpl.getcuizhanginfo(info);
	}
	
	/**
	 * 保险处理
	 * @param identyCard 身份证号
	 * @return 影响的行数 
	 */
	public int  finishInfoDeal(String pid){
	    ExPersonInfoPay info = new ExPersonInfoPay();
	    info.setPid(pid);
	    info.setWork_status("4");
	    return exPersonInfoPayDaoImpl.perInfoDeal(info);
	}
	
	
    
    /**
	 * 查询一个对象 
	 * @param entity
	 * @return
	 */
	public ExPersonInfoPay selectOneByEntity(ExPersonInfoPay entity){
	    return exPersonInfoPayDaoImpl.selectOneByEntity(entity);
	}
	
	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	public int insert(ExPersonInfoPay entity){
	    return exPersonInfoPayDaoImpl.insert(entity);
	}
}
