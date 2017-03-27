package com.is.pretrst.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.DExProgressDaoImpl;
import com.is.pretrst.entity.DExProgress;
import com.is.pretrst.entity.query.DExProgressQuery;
import com.is.utils.PublicDict;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 *
 * @ClassName: DExProgressServiceImpl
 * @Description: DExProgress表对应的服务类
 * @author 
 * @date 2013-09-10 10:25:55 *
 */
@Component
@Transactional 
public class DExProgressServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DExProgress.class);
	@Autowired
	private DExProgressDaoImpl dexProgressDaoImpl;
	
	 /**
     * 分页查询进度列表
     * @param queryEntity
     * @return page
     */
	@Transactional(readOnly=true)
	public Page pageQuery(DExProgressQuery queryEntity){
	   return dexProgressDaoImpl.pageQuery("DExProgress.selectByPage",queryEntity);
	}

    /**
     * 根据导航树的点击分页查询进度列表
     * @param queryEntity 里面有两个报告的属性
     * @return page
     */
    @Transactional(readOnly=true)
    public Page pageQueryTree(DExProgress gress){
        return dexProgressDaoImpl.pageQuery("DExProgress.selectByPageTree",gress);
    }

	/**
     * 根据条件查询一个对象
     * @param queryEntity
     * @return entity
     */
	public DExProgress selectOneByEntity(DExProgress entity){
	    return dexProgressDaoImpl.selectOneByEntity(entity);
	}
	/**
	 * 更新对象
	 * @param entity
	 * @return
	 */
	public int  updateByEntity(DExProgress entity){
	    int i = dexProgressDaoImpl.updateByEntity(entity);
	    if(i>0){
	    // 添加日志
        OperLogUtil.insertOperLog(entity.getReportId(), PublicDict.MODEL_EXPROGRESS,
                "进度管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新进度信息", "更新成功",
                "更新进度信息", "d_ex_progress");
	    }
        return i;
	}
	public List<DExProgress> selectByEntity(DExProgress entity){
	    return dexProgressDaoImpl.selectByEntity(entity);
	}
}
