package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DReportImage;


/**
 *
 * @ClassName: DReportImageDaoImpl
 * @Description: DReportImage表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:44 *
 */
@Component 
public class DQualityImageDaoImpl extends Mybatis3Dao<DReportImage>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DQualityImageDaoImpl.class);

    protected static final String NAMESPACE = "DReportImage";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}