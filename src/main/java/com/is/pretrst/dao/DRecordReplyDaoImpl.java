package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DRecordReply;


/**
 *
 * @ClassName: DRecordReplyDaoImpl
 * @Description: DRecordReply表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:34 *
 */
@Component 
public class DRecordReplyDaoImpl extends Mybatis3Dao<DRecordReply>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DRecordReplyDaoImpl.class);

    protected static final String NAMESPACE = "DRecordReply";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}