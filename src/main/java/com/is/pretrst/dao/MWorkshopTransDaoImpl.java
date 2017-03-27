package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.MWorkshopTrans;


/**
 *
 * @ClassName: DScrapTransDaoImpl
 * @Description: DScrapTrans表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:53 *
 */
@Component 
public class MWorkshopTransDaoImpl extends Mybatis3Dao<MWorkshopTrans>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopTransDaoImpl.class);

    protected static final String NAMESPACE = "MWorkshopTrans";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}