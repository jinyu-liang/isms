package com.is.sys.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.sys.entity.SysAttach;

/**
 *
 * @ClassName: AttachDaoImpl
 * @Description: sysAttach表对应的数据库操作类
 * @author 
 * @date 2011-12-21 09:30:01 *
 */
@Component
public class SysAttachDaoImpl extends Mybatis3Dao<SysAttach>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SysAttachDaoImpl.class);

    protected static final String NAMESPACE = "SysAttach";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    /**
     * sysAttach
     * @return 其他:成功 ;0：失败
     */
    public SysAttach getOneSysAttach(SysAttach sysAttach){
    	return (SysAttach) getSqlSessionTemplate().selectOne("SysAttach.selectOneByEntity", sysAttach);
    }
}