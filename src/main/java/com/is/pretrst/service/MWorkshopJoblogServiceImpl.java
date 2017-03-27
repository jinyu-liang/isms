package com.is.pretrst.service;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.MWorkshopJoblogDaoImpl;
import com.is.pretrst.entity.MWorkshopJoblog;
import com.is.pretrst.entity.query.MWorkshopJoblogQuery;
import com.is.utils.PublicDict;
import com.is.utils.date.DateUtil;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: MWorkshopJoblogServiceImpl
 * @Description: MWorkshopJoblog表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:43 *
 */
@Component
@Transactional
public class MWorkshopJoblogServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MWorkshopJoblog.class);
	@Autowired
	private MWorkshopJoblogDaoImpl MWorkshopJoblogDaoImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;

	/**
	 * 分布查詢
	 * 
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageQuery(MWorkshopJoblogQuery query) {
		return MWorkshopJoblogDaoImpl.pageQuery("MWorkshopJoblog.selectByPage", query);
	}
	
    /**
     * 根据查询条件条件查询单个用户信息
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public MWorkshopJoblog selectOneByEntity(MWorkshopJoblog entity)
    {
        return MWorkshopJoblogDaoImpl.selectOneByEntity(entity);
    }

	/**
	 * 新增保存方法
	 * 
	 * @param entity
	 * @throws ParseException 
	 */
	public void saveMWorkshopJoblog(MWorkshopJoblog entity) throws ParseException {
		String id = KeyGen.getCommonKeyGen(PublicDict.M_WORKSHOP_JOBLOG);
		entity.setID(id);
		entity.setAccepttime(DateUtil.getCurDate());
		int i = MWorkshopJoblogDaoImpl.insert(entity);
		if (i > 0) {

			// 添加日志
			OperLogUtil.insertOperLog(entity.getID(),
					PublicDict.M_WORKSHOP_JOBLOG, "工作日志",
					PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加工作日志", "添加成功",
					"添加工作日志", "m_workshop_joblog");
		}
	}

	/**
	 * 修改方法
	 * 
	 * @param entity
	 */
	public void updateMWorkshopJoblog(MWorkshopJoblog entity) {
		int i = MWorkshopJoblogDaoImpl.updateByEntity(entity);
		if (i > 0) {
			// 添加日志
			OperLogUtil.insertOperLog(entity.getID(),
					PublicDict.M_WORKSHOP_JOBLOG, "工作日志",
					PublicDict.OPER_LOG_EVENT_ADD, "更新", "更新工作日志", "更新成功",
					"更新工作日志", "m_workshop_joblog");
		}
	}



	/**
	 * 获取对象查询方法
	 * 
	 * @param reportId
	 * @return
	 */
	public MWorkshopJoblog getMWorkshopJoblog(MWorkshopJoblog entity) {
		return MWorkshopJoblogDaoImpl.selectOneByEntity(entity);
	}

	public MWorkshopJoblogDaoImpl getMWorkshopJoblogDaoImpl() {
		return MWorkshopJoblogDaoImpl;
	}

	@Autowired
	public void setMWorkshopJoblogDaoImpl(MWorkshopJoblogDaoImpl MWorkshopJoblogDaoImpl) {
		this.MWorkshopJoblogDaoImpl = MWorkshopJoblogDaoImpl;
	}

	
}
