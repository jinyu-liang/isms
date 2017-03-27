package com.is.pretrst.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.pretrst.dao.RstUploadConfDaoImpl;
import com.is.pretrst.entity.RstUploadConf;
import com.is.utils.PublicDict;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 *
 * @ClassName: RstUploadConfServiceImpl
 * @Description: RstUploadConf表对应的服务类
 * @author 
 * @date 2013-10-26 12:44:21 *
 */
@Component
@Transactional 
public class RstUploadConfServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(RstUploadConf.class);
	@Autowired
	private RstUploadConfDaoImpl uploadConfDaoImpl;
	
	/**
	 * 查询图片配置对象
	 * @return
	 */
	public  RstUploadConf selectPicParam(){
	    return uploadConfDaoImpl.selectAll().get(0);
	}
	/**
	 * 查询一个对象
	 * @param entity
	 * @return
	 */
	public RstUploadConf selectOneByEntity(RstUploadConf entity){
	   return uploadConfDaoImpl.selectOneByEntity(entity);
	}
	/**
	 * 更新对象
	 * @param entity
	 * @return
	 */
	public int UpdateByEntity(RstUploadConf entity){
	    int i = 0;
	    entity.setStatusCd("1");//正常状态
	    UserDetail user = SpringSecurityUtils.getCurrentUser();
	    entity.setOperTime(new Date());
        if (null != user) {
	    entity.setOperUserId(user.getUserId());
	    entity.setOperUserName(user.getUsername());
        }   
        if(entity.getFileType()==""||entity.getFileType()==null){
             i = uploadConfDaoImpl.updateByEntity(entity);
        }else{
             i = this.insert(entity);
        }
	    if(i>0){
	         // 添加日志
	            OperLogUtil.insertOperLog(entity.getFileType(), PublicDict.MODEL_SYSTEM,
	                    "系统管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新图片大小限制", "更新成功",
	                    "更新图片大小限制", "rst_upload_conf"); 
	    }
	    return i;
	}
	/**
	 * 添加对象
	 * @param entity
	 * @return
	 */
	public int insert(RstUploadConf entity){
	    int i = 0;
	    i = this.delete();
	    entity.setStatusCd("1");//正常状态
        UserDetail user = SpringSecurityUtils.getCurrentUser();
        entity.setOperTime(new Date());
        if (null != user) {
        entity.setOperUserId(user.getUserId());
        entity.setOperUserName(user.getUsername());
        }   
	     i = uploadConfDaoImpl.insert(entity);
	    if(i>0){
            // 添加日志
               OperLogUtil.insertOperLog(entity.getFileType(), PublicDict.MODEL_SYSTEM,
                       "系统管理", PublicDict.OPER_LOG_EVENT_UPDATE, "添加", "添加图片大小限制", "添加成功",
                       "添加图片大小限制", "rst_upload_conf"); 
       }
	    return i;
	}
	/**
	 * 删除之前的所有
	 * @return
	 */
	public int delete(){
	   return uploadConfDaoImpl.deleteAll();
	}

}
