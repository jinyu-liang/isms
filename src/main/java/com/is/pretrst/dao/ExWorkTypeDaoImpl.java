package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzDepartInfo;
import com.is.pretrst.entity.ExWorkType;
import com.is.pretrst.entity.query.ExWorkTypeQuery;


/**
 *
 * @ClassName: MTeamDaoImpl
 * @Description: MTeam表对应的数据库操作类
 * @author 
 * @date 2013-12-18 15:07:36 *
 */
@Component 
public class ExWorkTypeDaoImpl extends Mybatis3Dao<ExWorkType>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExWorkTypeDaoImpl.class);

    protected static final String NAMESPACE = "ExWorkType";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
    public ExWorkTypeQuery selectEntity(ExWorkTypeQuery queryEntity)
    {
        return (ExWorkTypeQuery) getSqlSessionTemplate().selectOne("ExWorkType.selectOneByEntity", queryEntity);
    }
    
}