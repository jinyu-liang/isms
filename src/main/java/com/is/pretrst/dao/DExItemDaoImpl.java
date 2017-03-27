package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DExItem;


/**
 *
 * @ClassName: DExItemDaoImpl
 * @Description: DExItem表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:01 *
 */
@Component 
public class DExItemDaoImpl extends Mybatis3Dao<DExItem>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DExItemDaoImpl.class);

    protected static final String NAMESPACE = "DExItem";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}