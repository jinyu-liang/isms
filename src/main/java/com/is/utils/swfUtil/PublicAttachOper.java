package com.is.utils.swfUtil;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.sys.entity.SysAttach;
import com.is.sys.entity.SysAttachAuth;
import com.is.sys.service.SysAttachAuthServiceImpl;
import com.is.sys.service.SysAttachServiceImpl;
import com.is.utils.StringUtils;

public class PublicAttachOper {
	static final Logger LOGGER = LoggerFactory
			.getLogger(PublicAttachOper.class);

	public static void publicAbbachSave(String busiId, String sysFileName,
			String path, File file, String oldName, String modelCode,
			String userId, String username) {
		SysAttachServiceImpl entityService = (SysAttachServiceImpl) SpringContextHolder
				.getBean("sysAttachServiceImpl");

		SysAttach sysAttach = new SysAttach();
		String fileSize = String.valueOf(new DecimalFormat("#.00").format(file
				.length() * 1.00 / 1024));// 获取文件大小，单位k,精确小数点后两位
		String fileType = SWFUploadHelper.getFileType(oldName);// 获取文件类型
		LOGGER.debug("文件名称,类型,大小,存放到服务上的文件名={}", oldName + "||" + fileType
				+ "||" + fileSize + "||" + sysFileName);

		/* 附件来源 */
		// String attachSource = "";
		/* 附件编号 String attachId; */

		sysAttach.setAttachId(sysFileName);
		/* 附件名称 String attName; */
		sysAttach.setAttachName(oldName);

		// 设置文件存放路径

		// CreateDocDirUtil.createDir(path);

		/* 附件路径 String attachPath; */
		sysAttach.setAttachPath(path);

		/* 文件类型 String fileType; */

		sysAttach.setFileType(fileType);

		/* 文件大小 String fileSize; */
		sysAttach.setFileSize(fileSize);

		/* 文件夹id String docDirId此处存放模块名，模块名就是文件夹名字 */
		if (StringUtils.isEmpty(modelCode)) {
			modelCode = "pub";
		}
		/* 创建时间 Date createTime; */
		sysAttach.setOperTime(new Date());

		sysAttach.setOperName(username);

		sysAttach.setOperUserId(userId);

		/* 附件状态 String attachState; */
		sysAttach.setAttachState("1");// 有效

		/* 附件来源 String attachSource 传入的模块名 */
		sysAttach.setModelCode(modelCode);
		// 保存sysDocAttach数据库
		entityService.insert(sysAttach);

	}

	/***
	 * 保存附件关联权限表
	 */
	public static void AttachAuthSave(String fileIds, String busiId,
			String userId) {
		SysAttachAuthServiceImpl attachAuthService = (SysAttachAuthServiceImpl) SpringContextHolder
				.getBean("sysAttachAuthServiceImpl");
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileIdss = fileIds.split(";");// 将所有文件Id区分开来
			for (int i = 0; i < fileIdss.length; i++) {
				String sysFileName = fileIdss[i];
				SysAttachAuth sysAttachAuth = new SysAttachAuth();
				sysAttachAuth.setAttachId(sysFileName);
				sysAttachAuth.setBusiId(busiId);
				sysAttachAuth.setUserId(userId);
				attachAuthService.insert(sysAttachAuth);
			}
		}

	}

	/***
	 * 根据业务Id更新附件关联权限表
	 * 
	 * @param oldBusiId
	 *            旧业务Id
	 * @param newBusiId
	 *            新业务Id，
	 */
	public static void AttachAuthUpdate(String oldBusiId, String NewBusiId,
			String orgId) {
		// SysAttachAuthServiceImpl attachAuthService =
		// (SysAttachAuthServiceImpl) SpringContextHolder
		// .getBean("sysAttachAuthService");
		// attachAuthService.attachAuthUpdate(oldBusiId, NewBusiId, orgId);
	}

	/**
	 * 保存附件关联权限表
	 * 
	 * @param fileIds
	 *            选择的附件
	 * @param busiId
	 *            业务Id
	 * @param empId
	 *            员工Id，如果业务上是按照部门/岗位来取附件，存的时候就存部门Id/岗位Id，请在业务模块里面控制
	 * @param orgId
	 *            机构Id
	 */
	public static void AttachAuthSaveByEmpId(String fileIds, String busiId,
			String userId) {
		SysAttachAuthServiceImpl attachAuthService = (SysAttachAuthServiceImpl) SpringContextHolder
				.getBean("sysAttachAuthService");
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fileIdss = fileIds.split(";");// 将所有文件Id区分开来
			for (int i = 0; i < fileIdss.length; i++) {
				String sysFileName = fileIdss[i];
				SysAttachAuth sysAttachAuth = new SysAttachAuth();
				sysAttachAuth.setAttachId(sysFileName);
				sysAttachAuth.setBusiId(busiId);
				sysAttachAuth.setUserId(userId);
				attachAuthService.insert(sysAttachAuth);
			}
		}

	}

	/**
	 * 获取附件路径方法
	 * 
	 * @param orgId
	 *            附件Id
	 */
	public static String getAttachPath(String attachId) {
		SysAttachServiceImpl entityService = (SysAttachServiceImpl) SpringContextHolder
				.getBean("sysAttachServiceImpl");
		SysAttach attach = entityService.getAttachById(attachId);
		String attachPath = "";
		if (attach != null) {
			attachPath = attach.getAttachPath() + "/" + attach.getAttachId();
		}
		return attachPath;
	}

	/**
	 * 根据附件的ID和业务ID删除附件信息和附件的关联关系
	 * 
	 * @param fileId
	 *            附件的ID
	 * @param businessId
	 *            附件的业务ID
	 * @return true：删除成功 false：删除失败
	 */
	public static boolean delDocAttachById(String fileId, String businessId) {
		try {
			SysAttachServiceImpl entityService = (SysAttachServiceImpl) SpringContextHolder
					.getBean("sysAttachServiceImpl");
			// 删除数据库对应的文件
			SysAttach att = new SysAttach();
			att.setAttachId(fileId);
			SysAttachAuth attachAuth = new SysAttachAuth();
			// 删除关联数据
			LOGGER.debug("要删除的文件ID为{}和业务Id为{}", fileId, businessId);
			attachAuth.setAttachId(fileId);
			attachAuth.setBusiId(businessId);
			entityService.deleteFile(attachAuth, att);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 根据业务ID删除所有的附件信息和附件的关联关系
	 * 
	 * @param businessId
	 *            附件的业务ID
	 * @return true：删除成功 false：删除失败
	 * @author 邵利 2011-08-18
	 */
	public static boolean delDocAttachById(String businessId) {

		try {
			SysAttachAuthServiceImpl attachAuthService = (SysAttachAuthServiceImpl) SpringContextHolder
					.getBean("sysAttachAuthService");
			SysAttachServiceImpl entityService = (SysAttachServiceImpl) SpringContextHolder
					.getBean("sysAttachServiceImpl");
			SysAttachAuth attachAuth = new SysAttachAuth();
			attachAuth.setBusiId(businessId);
			List<SysAttachAuth> sysAttachAuthList = attachAuthService
					.selectByEntity(attachAuth);
			if (sysAttachAuthList != null && sysAttachAuthList.size() > 0) {
				for (int i = 0; i < sysAttachAuthList.size(); i++) {
					String attId = sysAttachAuthList.get(i).getAttachId();
					if (StringUtils.isNotEmpty(attId)) {

						// 删除数据库对应的文件
						SysAttach att = new SysAttach();
						att.setAttachId(attId);
						SysAttachAuth attachAuth1 = new SysAttachAuth();
						// 删除关联数据
						LOGGER.debug("要删除的文件ID为{}和业务Id为{}", attId, businessId);
						attachAuth1.setAttachId(attId);
						attachAuth1.setBusiId(businessId);
						entityService.deleteFile(attachAuth1, att);
					}
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
