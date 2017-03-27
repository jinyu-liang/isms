package com.is.pretrst.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.DExProjectDaoImpl;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.query.DExProjectQuery;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 *
 * @ClassName: DExProjectServiceImpl
 * @Description: DExProject表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:08 *
 */
@Component
@Transactional 
public class DExProjectServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DExProject.class);
	@Autowired
	private DExProjectDaoImpl dexProjectDaoImpl;
	
	/**
	 * 根据查询条件查询一个对象
	 * @param entity
	 * @return entity
	 */
	public DExProject selectOneByEntity( DExProject entity){
	   return dexProjectDaoImpl.selectOneByEntity(entity);
	}
	
	/**
	 * 根据条件更新一个对象,没有则添加
	 * @param entity
	 * @return
	 */
	public int updateByEntity(DExProject entity){
        return dexProjectDaoImpl.updateByEntity(entity);
	}
	/**
	 * 添加 一个对象
	 * @param entity
	 * @return
	 */
	public int insert(DExProject entity){
	    entity.setProjectId(KeyGen.getCommonKeyGen(PublicDict.D_EXPROJECT));
	    int i = dexProjectDaoImpl.insert(entity);
	    if(i>0){
	    // 添加日志
        OperLogUtil.insertOperLog(entity.getProjectId(), PublicDict.MODEL_EXPROGRESS,
                "进度管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加项目信息", "添加成功",
                "添加项目信息", "d_ex_project");
	    }
	     return i;
	}

	public Page selectDExProjectPage(DExProjectQuery queryEntity) {
		return dexProjectDaoImpl.pageQuery("DExProject.selectByPage", queryEntity);
	}
	
	/**
	 * 根据对象查询对象集合
	 * @param entity
	 * @return
	 */
	public List<DExProject> selectByEntity(DExProject entity){
	    return dexProjectDaoImpl.selectByEntity(entity);
	}
}
