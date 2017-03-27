package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DScrapTransImage;


/**
 *
 * @ClassName: DScrapTransImageDaoImpl
 * @Description: DScrapTransImage表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:47 *
 */
@Component 
public class DScrapTransImageDaoImpl extends Mybatis3Dao<DScrapTransImage>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DScrapTransImageDaoImpl.class);

    protected static final String NAMESPACE = "DScrapTransImage";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}