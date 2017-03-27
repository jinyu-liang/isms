package com.is.ggkz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzPostInfo;

/**
 *
 * @ClassName: GgkzPostInfoDaoImpl
 * @Description: GgkzPostInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:37 *
 */
@Component
public class GgkzPostInfoDaoImpl extends Mybatis3Dao<GgkzPostInfo>
{

    @SuppressWarnings("unused")
    private static final Logger   LOGGER    = LoggerFactory.getLogger(GgkzPostInfoDaoImpl.class);

    protected static final String NAMESPACE = "GgkzPostInfo";

    public int deleteByEntityCascade(GgkzPostInfo entity)
    {
        return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteByEntityCascade", entity);
    }

    public int selectDeptIdByEntityProc(GgkzPostInfo entity)
    {
        int rst = getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".selectDeptIdProc", entity);
        if (rst == 1)
        {
            return entity.getResult();
        }
        return rst;
    }

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }

}