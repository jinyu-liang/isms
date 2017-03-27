package com.is.ggkz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzOrgInfo;

/**
 *
 * @ClassName: GgkzOrgInfoDaoImpl
 * @Description: GgkzOrgInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:34 *
 */
@Component
public class GgkzOrgInfoDaoImpl extends Mybatis3Dao<GgkzOrgInfo> {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GgkzOrgInfoDaoImpl.class);

    protected static final String NAMESPACE = "GgkzOrgInfo";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}