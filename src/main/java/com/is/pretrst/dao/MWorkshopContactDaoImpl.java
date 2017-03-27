package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.pretrst.entity.MWorkshopContact;
import com.is.pretrst.entity.query.MWorkshopContactQuery;


/**
 *
 * @ClassName: MWorkshopContactDaoImpl
 * @Description: MWorkshopContact表对应的数据库操作类
 * @author 
 * @date *
 */
@Component 
public class MWorkshopContactDaoImpl extends Mybatis3Dao<MWorkshopContact>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopContactDaoImpl.class);

    protected static final String NAMESPACE = "MWorkshopContact";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

}