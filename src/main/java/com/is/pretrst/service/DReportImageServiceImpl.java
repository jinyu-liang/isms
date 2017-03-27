package com.is.pretrst.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.dao.DReportImageDaoImpl;
import com.is.pretrst.entity.DReportImage;

/**
 *
 * @ClassName: DReportImageServiceImpl
 * @Description: DReportImage表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:45 *
 */
@Component
@Transactional 
public class DReportImageServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DReportImage.class);
	@Autowired
	private DReportImageDaoImpl  dReportImageDaoImpl;
	
	/**
	 * 根据对象删除数据库图片数据与系统目录下图片文件
	 * @param entity
	 * @return
	 */
	public int deleteByEntity(DReportImage entity){
	    List<DReportImage> imList = dReportImageDaoImpl.selectByEntity(entity);
	    for(DReportImage im:imList){
	        this.deleteFile(im.getFileName());
	    }
	    return dReportImageDaoImpl.deleteByEntity(entity);
	}
	
	/**
     * 删除图片文件
     * @param filePath 图片名称
     */
    public void  deleteFile(String filePath){
        String path = ServletActionContext.getServletContext().getRealPath("/");// 系统根目录
        File file = new File(path + filePath);//删除图片
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
        }
    }
    
	/**
	 * 根据查询条件查询对象集合
	 * @param entity
	 * @return
	 */
	public List<DReportImage> selectByEntity(DReportImage entity){
	    return dReportImageDaoImpl.selectByEntity(entity);
	}
	
	/**
	 * 查询对象集合里的图片集合
	 * @param entity
	 * @return
	 */
	public List<String> getImages(DReportImage entity){
	    List<String> strList = new ArrayList<String>();
	    System.out.println("--------entity-----------"+entity);
	    List<DReportImage> imageList = this.selectByEntity(entity);
	    System.out.println("--------imageList-----------"+imageList);
	    for(DReportImage image:imageList){
	        strList.add(image.getFileName());
	    }
	    return strList;
	}
}
