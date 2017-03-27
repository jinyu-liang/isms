package com.is.pretrst.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.dao.DContractImageDaoImpl;
import com.is.pretrst.entity.DContractImage;
import com.is.pretrst.entity.MWorkshopExt;
import com.is.pretrst.entity.query.MWorkshopExtQuery;

/**
 *
 * @ClassName: DReportImageServiceImpl
 * @Description: DReportImage表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:45 *
 */
@Component
@Transactional 
public class DContractImageServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DContractImageServiceImpl.class);
	@Autowired
	private DContractImageDaoImpl contractImageDaoImpl;
	
	/**
	 * 根据查询条件查询对象集合
	 * @param entity
	 * @return
	 */
	public List<DContractImage> selectByEntity(DContractImage entity){
	    return contractImageDaoImpl.selectByEntity(entity);
	}
	
	/**
	 * 查询对象集合里的图片集合
	 * @param entity
	 * @return
	 */
	public List<String> getImages(DContractImage entity){
	    List<String> strList = new ArrayList<String>();
	    System.out.println("--------entity-----------"+entity);
	    List<DContractImage> imageList = this.selectByEntity(entity);
	    System.out.println("--------imageList-----------"+imageList);
	    for(DContractImage image:imageList){
	        strList.add(image.getFilename());
	    }
	    return strList;
	}
}
