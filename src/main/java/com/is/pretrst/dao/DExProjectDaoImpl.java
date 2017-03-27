package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.DExProjectExt;


/**
 *
 * @ClassName: DExProjectDaoImpl
 * @Description: DExProject表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:06 *
 */
@Component 
public class DExProjectDaoImpl extends Mybatis3Dao<DExProject>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DExProjectDaoImpl.class);

    protected static final String NAMESPACE = "DExProject";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    /**
     * 查询正在进行中的工地所对应的项目
     * @param entity
     * @return
     */
    public List<DExProject> selectDoingProject(){
        return  this.getSqlSessionTemplate().selectList("DExProject.selectDoingProject");
    }
    
    /**
     * 查询所有正在进行中的扩展工地所对应的项目
     * @param entity
     * @return
     */
    public List<DExProjectExt> selectAllDoingProjectExt(){
        return  this.getSqlSessionTemplate().selectList("DExProject.selectAllDoingProjectExt");
    }
    /**
     * 查询项目经理正在进行中的扩展工地所对应的项目
     * @param userId 项目经理的id
     * @return
     */
    public List<DExProjectExt> selectSiteDoingProjectExt(String userId){
        return  this.getSqlSessionTemplate().selectList("DExProject.selectSiteDoingProjectExt",userId);
    }
    /**
     * 查询区域部长下属正在进行中的扩展工地所对应的项目
     * @param userId 区域部长的id
     * @return
     */
    public List<DExProjectExt> selectAreaDoingProjectExt(String userId){
        return  this.getSqlSessionTemplate().selectList("DExProject.selectAreaDoingProjectExt",userId);
    }
}