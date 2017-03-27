package com.is.ggkz.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzRoleInfo;

/**
 *
 * @ClassName: GgkzRoleInfoDaoImpl
 * @Description: GgkzRoleInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:18:56 *
 */
@Component
public class GgkzRoleInfoDaoImpl extends  Mybatis3Dao<GgkzRoleInfo> {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GgkzRoleInfoDaoImpl.class);

    protected static final String NAMESPACE = "GgkzRoleInfo";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    /**
     * 删除多个资源
     * @return 其他:成功 0：失败
     */
    public int deleteManyRoleInfo(List<String> ids){
    	return getSqlSessionTemplate().delete("GgkzRoleInfo.batchRemoveRoleInfoByPks", ids);
    }
}