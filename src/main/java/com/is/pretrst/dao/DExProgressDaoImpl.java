package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DExItemExt;
import com.is.pretrst.entity.DExProgress;
import com.is.pretrst.entity.DExProgressExt;


/**
 *
 * @ClassName: DExProgressDaoImpl
 * @Description: DExProgress表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:25:53 *
 */
@Component 
public class DExProgressDaoImpl extends Mybatis3Dao<DExProgress>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DExProgressDaoImpl.class);

    protected static final String NAMESPACE = "DExProgress";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
    /**
     * 根据 用户id查询所掌管的正在进行的项目
     * @param userId
     * @return
     
    public List<DExProgress> selectProgressAndProjectByUserId(String userId){
        return this.getSqlSessionTemplate().selectList("DExProgress.selectProgressAndProjectByUserId", userId);
    }*/
    public DExProgress selectOneByEntityMobile(DExProgress entity){
        return (DExProgress) this.getSqlSessionTemplate().selectOne("DExProgress.selectOneByEntityMobile", entity);
    }
    public DExProgress selectGressByLastReportId(DExProgress entity){
        return (DExProgress) this.getSqlSessionTemplate().selectOne("DExProgress.selectGressByLastReportId", entity);
    }
    
    /* 根据项目WSCD查询负责人id */
    public String selectMangerIdByWsCd(String wsCd){
        return  (String) this.getSqlSessionTemplate().selectOne("DExProgress.selectMangerIdByWsCd", wsCd);
    }
    
    /* 根据报告id查询进度与项目信息 */
    public DExProgressExt selectProgressExtByReportId(String reportId){
        return  (DExProgressExt) this.getSqlSessionTemplate().selectOne("DExProgress.selectProgressByReportId", reportId);
    }
    /* 根据报告id查询进度图片 */
    public List<String> selectImgByReportId(String reportId){
        return  this.getSqlSessionTemplate().selectList("DExProgress.selectImgByReportId", reportId);
    }
    /* 根据工程ID查询工地产品详情 */
    public List<DExItemExt> selectItemByProjectId(String projectId){
        return  this.getSqlSessionTemplate().selectList("DExProgress.selectItemByProjectId", projectId);
    }
    /* 根据工程ID查询工程详情 (进度里展示的三个字段和一个主键)*/
    public DExProgressExt selectProjectByProjectId(String projectId){
        return  (DExProgressExt) this.getSqlSessionTemplate().selectOne("DExProgress.selectProjectByProjectId", projectId);
    }
    
    
}