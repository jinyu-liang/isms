package com.is.sys.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.sys.dao.SysAttachAuthDaoImpl;
import com.is.sys.entity.SysAttach;
import com.is.sys.entity.SysAttachAuth;
import com.is.utils.StringUtils;

/**
 * 
 * @ClassName: SysAttachServiceImpl
 * @Description: SysAttach表对应的服务类
 * @author 
 * @date 2013-02-27 14:20:27 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class SysAttachAuthServiceImpl {

	public SysAttachAuthDaoImpl sysAttachAuthDaoImpl;
	private SysAttachServiceImpl sysAttachServiceImpl;

	@Autowired
	public void setSysAttachServiceImpl(SysAttachServiceImpl sysAttachServiceImpl) {
		this.sysAttachServiceImpl = sysAttachServiceImpl;
	}

	@Autowired
	public void setSysAttachAuthDaoImpl(
			SysAttachAuthDaoImpl sysAttachAuthDaoImpl) {
		this.sysAttachAuthDaoImpl = sysAttachAuthDaoImpl;
	}

	public List<SysAttachAuth> selectByEntity(SysAttachAuth entity) {
		return sysAttachAuthDaoImpl.selectByEntity(entity);
	}

	public void insert(SysAttachAuth auth) {
		sysAttachAuthDaoImpl.insert(auth);
	}

	/**
	 * 返回所有图片文件路径
	 * @author 
	 * @param entity
	 * @return
	 */
	public List<String> selectAllImgFilse(SysAttachAuth entity) {
		if(entity==null||("").equals(entity.getBusiId())||entity.getBusiId()==null){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(ServletActionContext.getRequest().getScheme());
		sb.append("://");
		sb.append(ServletActionContext.getRequest().getServerName());
		sb.append(":");
		sb.append(ServletActionContext.getRequest().getServerPort()).append(ServletActionContext.getRequest().getContextPath());
		sb.append("/");
		//ServletActionContext.getRequest().getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		/* 图片文件 */
	    List<String> imgFilePath = new ArrayList<String>();
		String imgType = "bmp,jpg,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw";
		List<SysAttachAuth> sysAttachAuthList = sysAttachAuthDaoImpl.selectByEntity(entity);
		if (sysAttachAuthList != null && sysAttachAuthList.size() > 0) {
			for (int i = 0; i < sysAttachAuthList.size(); i++) {
				String attId = sysAttachAuthList.get(i).getAttachId();
				if (StringUtils.isNotEmpty(attId)) {
					SysAttach attach = new SysAttach();
					attach = sysAttachServiceImpl.getAttachById(attId);
					if (attach != null) {
						String type = attach.getFileType();
						if (imgType.indexOf(type) != -1) {
							StringBuffer filePath = new StringBuffer();
							// ggkz//upload/ggkz/20130409/attach2013040909484293172179000.jpg
							filePath.append(sb.toString());
							filePath.append(attach.getAttachPath()).append("/")
									.append(attach.getAttachId());
							imgFilePath.add(filePath.toString());
						}
					}
				}
			}
		}
		return imgFilePath;
	}
}
