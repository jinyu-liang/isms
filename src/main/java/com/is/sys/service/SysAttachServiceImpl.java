package com.is.sys.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.sys.dao.SysAttachAuthDaoImpl;
import com.is.sys.dao.SysAttachDaoImpl;
import com.is.sys.entity.SysAttachAuth;
import com.is.sys.entity.SysAttach;
import com.is.sys.entity.query.SysAttachQuery;
import com.is.utils.PublicDict;
import com.is.utils.sysOperLog.OperLogUtil;

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
public class SysAttachServiceImpl {
	private static Logger logger = LoggerFactory
			.getLogger(SysAttachServiceImpl.class);

	public SysAttachDaoImpl sysAttachDaoImpl;
	public SysAttachAuthDaoImpl sysAttachAuthDaoImpl;
	public SysAttachQuery sysAttachQuery;

	@Autowired
	public void setSysAttachDaoImpl(SysAttachDaoImpl sysAttachDaoImpl) {
		this.sysAttachDaoImpl = sysAttachDaoImpl;
	}

	@Autowired
	public void setSysAttachAuthDaoImpl(
			SysAttachAuthDaoImpl sysAttachAuthDaoImpl) {
		this.sysAttachAuthDaoImpl = sysAttachAuthDaoImpl;
	}

	/**
	 * 保存一个文件
	 * 
	 * @param Entity
	 * @return 0成功; 1异常
	 */
	public int insert(SysAttach sysAttach, SysAttachAuth sysAttachAuth) {
		try {
			sysAttachDaoImpl.insert(sysAttach);// 保存关联关系
			sysAttachAuthDaoImpl.insert(sysAttachAuth);// 保存文件信息
		} catch (Exception e) {
			logger.debug(e.toString());
			return 1;
		}
		// 添加日志
		OperLogUtil.insertOperLog(sysAttach.getAttachId(),
				PublicDict.MODEL_SYS, "基础数据", PublicDict.OPER_LOG_EVENT_IN,
				"上传", "上传附件并保存附件信息与附件与用户关联信息", "上传成功", "上传附件并保存附件信息与附件与用户关联信息",
				"sys-attach,sys-attach-auth");
		return 0;

	}

	public int insert(SysAttach sysAttach) {
		try {
			sysAttachDaoImpl.insert(sysAttach);// 保存关联关系
		} catch (Exception e) {
			logger.debug(e.toString());
			return 1;
		}
		return 0;

	}

	/**
	 * 查询附件用户关联表集合
	 * 
	 * @param entity
	 *            List<SysAttachAuth>
	 * @return
	 */
	public List<SysAttachAuth> getSysAttachAuthList(SysAttachAuth entity) {
		return sysAttachAuthDaoImpl.selectByEntity(entity);

	}

	/**
	 * 根据用户id查询所上传的文件
	 * 
	 * @param entity
	 * @return page
	 */
	public Page getSysAttachsList(SysAttachAuth entity) {
		Page page = new Page();
		List<SysAttach> sysAttachlist = new ArrayList<SysAttach>();
		List<SysAttachAuth> sysAttachAuthList = getSysAttachAuthList(entity);// 获得关联对象集合
		for (SysAttachAuth sysAttachAuth : sysAttachAuthList) {
			SysAttach sysAttach = new SysAttach();
			sysAttach.setAttachId(sysAttachAuth.getAttachId());
			sysAttachlist.add(sysAttachDaoImpl.selectOneByEntity(sysAttach));// 查询文件对象并放入集合
		}
		page.setData(sysAttachlist);// 把文件集合放入page
		return page;
	}

	/**
	 * 删除文件对象，文件与用户关联对象，系统源文件
	 * 
	 * @param sysAttachAuth
	 * @param sysAttach
	 * @return 返回1失败，
	 */
	public String deleteFile(SysAttachAuth sysAttachAuth, SysAttach sysAttach) {
		sysAttach = sysAttachDaoImpl.selectOneByEntity(sysAttach);
		try {
			sysAttachDaoImpl.deleteByEntity(sysAttach);// 删除文件对象
			sysAttachAuthDaoImpl.deleteByEntity(sysAttachAuth);// 文件与用户关联对象
		} catch (Exception e) {
			logger.debug(e.toString());
			return "1";
		}
		// sysAttach= sysAttachDaoImpl.selectOneByEntity(sysAttach);
		if (null == sysAttach) {
			return "1";
		} else {
			if (deleteFile(sysAttach.getAttachPath() + "\\"
					+ sysAttach.getAttachId())) {
				;// 删除系统中的文件
				OperLogUtil.insertOperLog(sysAttach.getAttachId(),
						PublicDict.MODEL_SYS, "系统配置",
						PublicDict.OPER_LOG_EVENT_DEL, "删除", "删除附件与数据库中的信息",
						"删除成功", "删除附件与数据库中的信息", "sys-attach,sys-attach-auth");
				return "0";
			} else
				return "1";
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 查询所有文件的全路径名称
	 * 
	 * @return List<String> pathList
	 */
	public List<String> getAllSysAttachFilePath() {
		List<String> pathList = new ArrayList<String>();
		List<SysAttach> sysAttachList = sysAttachDaoImpl.selectAll();
		for (SysAttach sysAttach : sysAttachList) {
			pathList.add(sysAttach.getAttachPath());

		}
		return pathList;
	}

	/**
	 * 根据附件id查找附件
	 * 
	 * @return
	 */
	public SysAttach getAttachById(String attachId) {
		SysAttach attach = new SysAttach();
		attach.setAttachId(attachId);
		return sysAttachDaoImpl.selectOneByEntity(attach);
	}

}
