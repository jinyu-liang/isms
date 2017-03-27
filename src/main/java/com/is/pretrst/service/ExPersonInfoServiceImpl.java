package com.is.pretrst.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.dao.ExPersonInfoDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.MTeam;

/**
 *
 * @ClassName: ExPersonInfoServiceImpl
 * @Description: ExPersonInfo表对应的服务类
 * @author 
 * @date 2013-12-18 15:07:34 *
 */
@Component
@Transactional 
public class ExPersonInfoServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(ExPersonInfo.class);

	@Autowired
	private ExPersonInfoDaoImpl exPersonInfoDaoImpl;
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByEntity(ExPersonInfo entity){
	  return   exPersonInfoDaoImpl.updateByEntity(entity);
	}
	
	
	/**
	 * 保险处理
	 * @param identyCard 身份证号
	 * @return 影响的行数 
	 */
	public int  perInfoDeal(String identyCard){
	    ExPersonInfo info = new ExPersonInfo();
	    info.setIdentyCardCode(identyCard);
	    info.setUpdateDate(new Date());
	    return exPersonInfoDaoImpl.perInfoDeal(info);
	}
	
	/**
     * 剪出
     * @param identyCard 身份证号
     * @return 影响的行数 
     */
    public int  perInfocheckout(String identyCard){
        ExPersonInfo info = new ExPersonInfo();
        info.setIdentyCardCode(identyCard);
        info.setUpdateDate(new Date());
        return exPersonInfoDaoImpl.perInfocheckout(info);
        
    }
    
    /**
	 * 查询一个对象 
	 * @param entity
	 * @return
	 */
	public ExPersonInfo selectOneByEntity(ExPersonInfo entity){
	    return exPersonInfoDaoImpl.selectOneByEntity(entity);
	}
	
	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	public int insert(ExPersonInfo entity){
	    return exPersonInfoDaoImpl.insert(entity);
	}
}
