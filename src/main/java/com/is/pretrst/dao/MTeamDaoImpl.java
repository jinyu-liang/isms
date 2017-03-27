package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.MTeam;


/**
 *
 * @ClassName: MTeamDaoImpl
 * @Description: MTeam表对应的数据库操作类
 * @author 
 * @date 2013-12-18 15:07:36 *
 */
@Component 
public class MTeamDaoImpl extends Mybatis3Dao<MTeam>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MTeamDaoImpl.class);

    protected static final String NAMESPACE = "MTeam";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
}