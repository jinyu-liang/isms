package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DContractImage;


/**
 *
 * @ClassName: DContractImageDaoImpl
 * @Description: DReportImage表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:44 *
 */
@Component 
public class DContractImageDaoImpl extends Mybatis3Dao<DContractImage>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DContractImageDaoImpl.class);

    protected static final String NAMESPACE = "DContractImage";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}