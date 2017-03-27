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

import com.is.pretrst.dao.DInvoiceImageDaoImpl;
import com.is.pretrst.entity.DInvoiceImage;

/**
 *
 * @ClassName: DInvoiceImageServiceImpl
 * @Description: DInvoiceImage表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:22 *
 */
@Component
@Transactional 
public class DInvoiceImageServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DInvoiceImage.class);
	@Autowired
	private DInvoiceImageDaoImpl dinvoiceImageDaoImpl;
	
	
	/**
	 * 根据条件对象
	 * @param entity
	 * @return
	 */
	public List<DInvoiceImage> selectByEntity(DInvoiceImage entity){
	    return dinvoiceImageDaoImpl.selectByEntity(entity);
	}
	
	/**
	 * 根据查询条件得到图片集合
	 */
	public List<String> getImages(DInvoiceImage entity){
	    List<String> strList = new ArrayList<String>();
	    List<DInvoiceImage> imgList = this.selectByEntity(entity);
	    for(DInvoiceImage image:imgList){
	        strList.add(image.getFileName());
	    }
	    return strList;
	}
	
	/**
	 * 删除数据库中的图片数据与系统目录下的图片
	 * @param entity 出门单图片对象
	 * @return
	 */
	public int deleteByEntity(DInvoiceImage entity){
	    int i=0;
	    List<String> imageNames = this.getImages(entity);
	    String path = ServletActionContext.getServletContext().getRealPath("/");// 系统根目录
	    if(imageNames!=null){
	        for(String imagePath:imageNames){//删除系统目录下的图片
	            File file = new File(path + imagePath);
                // 路径为文件且不为空则进行删除
                if (file.isFile() && file.exists())
                {
                    file.delete();
                }
                i=i+1;
	        }
	    }
	    i=dinvoiceImageDaoImpl.deleteByEntity(entity);//删除数据库中的图片数据
	    return i;
	}
}
