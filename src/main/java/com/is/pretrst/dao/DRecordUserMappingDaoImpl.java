package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DRecordUserMapping;


/**
 *
 * @ClassName: DRecordUserMappingDaoImpl
 * @Description: DRecordUserMapping表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:38 *
 */
@Component 
public class DRecordUserMappingDaoImpl extends Mybatis3Dao<DRecordUserMapping>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DRecordUserMappingDaoImpl.class);

    protected static final String NAMESPACE = "DRecordUserMapping";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}