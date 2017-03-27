package com.is.pretrst.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.dao.DProgressImageDaoImpl;
import com.is.pretrst.entity.DProgressImage;

/**
 *
 * @ClassName: DProgressImageServiceImpl
 * @Description: DProgressImage表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:27 *
 */
@Component
@Transactional 
public class DProgressImageServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DProgressImage.class);
	@Autowired
	private DProgressImageDaoImpl dprogressImageDaoImpl;
	
	/**
	 * 根据查询条件查询对象集合
	 * @param entity
	 * @return
	 */
	public List<DProgressImage> selectByEntity(DProgressImage entity){
	    return dprogressImageDaoImpl.selectByEntity(entity);
	} 
	
	/**
	 * 根据图片对象集合得到所有对象的图片集合
	 * @param entityList
	 * @return
	 */
	public List<String> selectImagebyEntity(DProgressImage entity){
	    List<DProgressImage> entityList = this.selectByEntity(entity);
	    List<String> imageList = new ArrayList<String>();
	    if(entityList!=null && entityList.size()!=0){
    	    for(DProgressImage im:entityList){
    	        imageList.add(im.getFilename());
    	    }
	    }
	    return imageList;
	}
}
