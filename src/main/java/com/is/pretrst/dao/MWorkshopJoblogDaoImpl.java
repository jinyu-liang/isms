package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.pretrst.entity.MWorkshopJoblog;
import com.is.pretrst.entity.query.MWorkshopJoblogQuery;


/**
 *
 * @ClassName: MWorkshopJoblogDaoImpl
 * @Description: MWorkshopJoblog表对应的数据库操作类
 * @author 
 * @date *
 */
@Component 
public class MWorkshopJoblogDaoImpl extends Mybatis3Dao<MWorkshopJoblog>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopJoblogDaoImpl.class);

    protected static final String NAMESPACE = "MWorkshopJoblog";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

}