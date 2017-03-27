package com.is.ggkz.dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzAuthInfo;

/**
 *
 * @ClassName: GgkzAuthInfoDaoImpl
 * @Description: GgkzAuthInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:22 *
 */
@Component
public class GgkzAuthInfoDaoImpl extends Mybatis3Dao<GgkzAuthInfo> {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GgkzAuthInfoDaoImpl.class);

    protected static final String NAMESPACE = "GgkzAuthInfo";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }


}