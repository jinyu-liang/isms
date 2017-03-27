package com.is.pretrst.service;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.dao.DScrapTransImageDaoImpl;
import com.is.pretrst.entity.DScrapTransImage;

/**
 *
 * @ClassName: DScrapTransImageServiceImpl
 * @Description: DScrapTransImage表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:48 *
 */
@Component
@Transactional 
public class DScrapTransImageServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DScrapTransImage.class);
	@Autowired
	private DScrapTransImageDaoImpl  dScrapTransImageDaoImpl;
	
	/**
	 * 删除剩料图片数据库数据与系统 目录下图片
	 * @param entity
	 * @return int 操作影响的条数 
	 */
	public int deleteByEntity(DScrapTransImage entity){
	    List<DScrapTransImage> imList = dScrapTransImageDaoImpl.selectByEntity(entity);
	    for(DScrapTransImage im:imList){
	        this.deleteFile(im.getFileName());
	    }
	    return  dScrapTransImageDaoImpl.deleteByEntity(entity);
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
}
