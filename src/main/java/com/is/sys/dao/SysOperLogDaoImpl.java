package com.is.sys.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.sys.entity.SysOperLog;

/**
 *
 * @ClassName: SysOperLogDaoImpl
 * @Description: SysOperLog表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:20:26 *
 */
@Component
public class SysOperLogDaoImpl extends Mybatis3Dao<SysOperLog> {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SysOperLogDaoImpl.class);

    protected static final String NAMESPACE = "SysOperLog";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}