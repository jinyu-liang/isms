package com.is.pretrst.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.pretrst.dao.ExPersonInfoDaoImpl;
import com.is.pretrst.dao.MTeamDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.query.ExPersonInfoQuery;
import com.is.pretrst.entity.query.MTeamQuery;

/**
 *
 * @ClassName: MTeamServiceImpl
 * @Description: MTeam表对应的服务类
 * @author 
 * @date 2013-12-18 15:07:38 *
 */
@Component
@Transactional 
public class MTeamServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(MTeam.class);
	@Autowired
	private MTeamDaoImpl mTeamDaoImpl;
	@Autowired
	private ExPersonInfoDaoImpl exPersonInfoDaoImpl;
	
	/**
	 * 分页查询
	 * @param queryEntity
	 * @return
	 */
	@Transactional(readOnly=true)
	public Page pageQuery(MTeamQuery queryEntity){
	   return mTeamDaoImpl.pageQuery("MTeam.selectByPage", queryEntity);
	}
	/**
	 * 插入对象
	 * @param entity
	 * @return
	 */
	public int insert(MTeam entity){
	    return mTeamDaoImpl.insert(entity);
	}
	/**
	 * 查询一个对象 
	 * @param entity
	 * @return
	 */
	public MTeam selectOneByEntity(MTeam entity){
	    return mTeamDaoImpl.selectOneByEntity(entity);
	}
	/**
	 * 更新对象
	 * @param entity
	 * @return
	 */
	public int updateByEntity(MTeam entity){
	    return mTeamDaoImpl.updateByEntity(entity);
	}
	
	/**
     * 删除对象
     * @param entity
     * @return
     */
	public int deleteByEntity(MTeam entity){
	    return mTeamDaoImpl.deleteByEntity(entity);
	}
	/**
	 * 根据wsCd查询施工队与施工队包含的人
	 * @param wsCd
	 * @return
	 */
	public List<MTeam> selectPersonInfoByTeamCd(String wsCd,String reportId){
	    List<MTeam> mteamList  = new ArrayList<MTeam>();
	    MTeamQuery team = new MTeamQuery();
	    team.setWsNm(wsCd);
	    List<MTeam> teamList = mTeamDaoImpl.selectByEntity(team);
	    ExPersonInfoQuery info =null;
	    if(teamList!=null){
	        for(MTeam t:teamList){
	            info =new ExPersonInfoQuery();
	            info.setTeamId(t.getTeamCd());
	            info.setReportId(reportId);
	            List<ExPersonInfo> infoList = exPersonInfoDaoImpl.selectByEntity(info);
	            t.setExPersonList(infoList);
	            mteamList.add(t);
	        }
	        
	    }
	    
	    
	    
	    return mteamList;
	}
}
