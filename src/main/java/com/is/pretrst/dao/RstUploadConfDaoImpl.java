package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.RstUploadConf;


/**
 *
 * @ClassName: RstUploadConfDaoImpl
 * @Description: RstUploadConf表对应的数据库操作类
 * @author 
 * @date 2013-10-26 12:44:20 *
 */
@Component 
public class RstUploadConfDaoImpl extends Mybatis3Dao<RstUploadConf>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(RstUploadConfDaoImpl.class);

    protected static final String NAMESPACE = "RstUploadConf";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
    /**
     * 删除所有数据重新添加
     * @return
     */
    public int deleteAll(){
       return this.getSqlSessionTemplate().delete(getIbatisMapperNamesapce()+".deleteAll");
    }
    
    
}