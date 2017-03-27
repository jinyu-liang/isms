package com.is.sys.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.sys.entity.SysAttachAuth;
import com.is.sys.entity.query.SysAttachAuthQuery;

/**
 *
 * @ClassName: BzzcAttachAuthDaoImpl
 * @Description: BzzcAttachAuth表对应的数据库操作类
 * @author 
 * @date 2012-01-29 16:57:21 *
 */
@Component
public class SysAttachAuthDaoImpl extends Mybatis3Dao<SysAttachAuth>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SysAttachAuthDaoImpl.class);

    protected static final String NAMESPACE = "SysAttachAuth";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
	/**
	 * 根据查询条件获取分页列表
	 * 
	 * @param ggkzUserInfoQuery
	 * @return
	 */
	public Page selectSysAttachAuthPage(SysAttachAuthQuery queryEntity) {
		return this.pageQuery("SysAttachAuth.selectByPage", queryEntity);
	}
}