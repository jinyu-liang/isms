package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DProgressImage;


/**
 *
 * @ClassName: DProgressImageDaoImpl
 * @Description: DProgressImage表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:25 *
 */
@Component 
public class DProgressImageDaoImpl extends Mybatis3Dao<DProgressImage>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DProgressImageDaoImpl.class);

    protected static final String NAMESPACE = "DProgressImage";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}