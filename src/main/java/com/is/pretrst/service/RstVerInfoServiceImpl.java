package com.is.pretrst.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.RstVerInfoDaoImpl;
import com.is.pretrst.entity.RstVerInfo;
import com.is.pretrst.entity.query.RstVerInfoQuery;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.date.DateUtil;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: RstVerInfoServiceImpl
 * @Description: RstVerInfo表对应的服务类
 * @author 
 * @date 2013-10-26 12:44:18 *
 */
@Component
@Transactional
public class RstVerInfoServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(RstVerInfo.class);
	@Autowired
	private RstVerInfoDaoImpl verInfoDaoImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	public Page selectRstVerInfo(RstVerInfoQuery queryEntity) {
		return verInfoDaoImpl.pageQuery("RstVerInfo.selectByPage", queryEntity);
	}

	public String verConf(RstVerInfo entity, UserDetail user) {
		int verId = getMaxVerId();
		String fileName = "upload/pub/" + DateUtil.getCurDateByyyyymmdd() + "/"
				+ entity.getFileName();
		entity.setVerId(verId);
		entity.setOperUserId(user.getUserId());
		entity.setOperUserName(user.getUsername());
		entity.setFileName(fileName);
		entity.setOperTime(new Date());
		int i = 0;
		i += verInfoDaoImpl.insert(entity);
		if (i != 0) {// 成功
			OperLogUtil.insertMobileOperLog(entity.getVerCode(), "",
					PublicDict.MODEL_SYSTEM, "版本维护",
					PublicDict.OPER_LOG_RESULT_SUCCESS, "上传了新版本", "上传了新版本，版本为【"
							+ entity.getVerCode() + "】", entity.getMemo(),
					"上传了新版本", "d_report");
			// 发消息接口
			pmsMessageTopicProducer.sendTopic("", "【" + user.getUsername()
					+ "】已经发布了最新的apk版本，请各位及时更新最新版本", "最新版本为【" + entity.getVerCode()
					+ "】,该版本的功能为：【" + entity.getMemo() + "】", "");
			return "0";
		} else {
			return "1";
		}
	}

	private int getMaxVerId() {
		return verInfoDaoImpl.getMaxVerId();

	}

	public RstVerInfo getMaxVerCode() {
		return verInfoDaoImpl.getMaxVerCode();

	}

	/**
	 * 获取当前版本是否有效的标示
	 * 
	 * @param verCode
	 * @return
	 */
	public String getVerValidFlag(String verCode) {
		if (StringUtils.isNotEmpty(verCode)) {
			RstVerInfoQuery info = new RstVerInfoQuery();
			info.setVerCode(verCode);
			RstVerInfo infoList = verInfoDaoImpl.selectOneByEntity(info);
			if (infoList == null) {
				return "N";
			} else {
				String statuscd = infoList.getStatusCd();
				String newVerCode = getMaxVerCode().getVerCode();
				if (verCode.equals(newVerCode)) {
					return "Y";
				} else if (statuscd.equals("1")) {
					return "1";
				}
				return infoList.getStatusCd();
			}

		} else {
			return "N";
		}

	}

	/**
	 * 版本停用和启用方法
	 * 
	 * @param verId
	 * @return
	 */
	public String verStartStop(String verCode, String flag) {
		// 获取当前操作数据
		RstVerInfo entity = new RstVerInfo();
		entity.setVerCode(verCode);
		entity = verInfoDaoImpl.selectOneByEntity(entity);

		// 获取当前处于启用状态的列表
		RstVerInfoQuery info = new RstVerInfoQuery();
		info.setStatusCd("1");
		List<RstVerInfo> infoList = verInfoDaoImpl.selectByEntity(info);
		// 发消息接口
		if (flag.equals("0")) {
			pmsMessageTopicProducer.sendTopic("", verCode + "版本apk已停用通知", verCode
					+ "版本apk已停用通知,请各位知晓", "");
		} else {
			pmsMessageTopicProducer.sendTopic("", verCode + "版本apk已启用通知", verCode
					+ "版本apk已启用通知:最新版本为【" + entity.getVerCode()
					+ "】：" + entity.getMemo() , "");
		}
		if (infoList != null && infoList.size() == 1 && flag.equals("0")) {

			return "0";// 不能停用，至少有一个版本处于启用中
		} else {
			entity.setStatusCd(flag);
			verInfoDaoImpl.updateByEntity(entity);
			return "1";// 操作成功
		}
	}

	/**
	 * 获取版本是否重复标示
	 * 
	 * @param verCode
	 * @return
	 */
	public String getVerFlag(String verCode) {
		RstVerInfoQuery info = new RstVerInfoQuery();
		info.setVerCode(verCode);
		RstVerInfo infoList = verInfoDaoImpl.selectOneByEntity(info);
		if (infoList != null) {
			return "1";// 重复版本号
		} else {
			return "0";
		}
	}

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 */
	public Page selectAll() {
		Page page = new Page();
		List<RstVerInfo> invoList = verInfoDaoImpl.selectAll();
		page.setData(invoList);
		return page;
	}

}
