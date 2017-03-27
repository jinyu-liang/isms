package com.is.ggkz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzDepartInfo;

/**
 *
 * @ClassName: GgkzDepartInfoDaoImpl
 * @Description: GgkzDepartInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:37 *
 */
@Component
public class GgkzDepartInfoDaoImpl extends Mybatis3Dao<GgkzDepartInfo>
{

    @SuppressWarnings("unused")
    private static final Logger   LOGGER    = LoggerFactory.getLogger(GgkzDepartInfoDaoImpl.class);

    protected static final String NAMESPACE = "GgkzDepartInfo";

    public int deleteByEntityCascade(GgkzDepartInfo entity)
    {
        return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteByEntityCascade", entity);
    }
    
    public GgkzDepartInfo selectMaxEntity(GgkzDepartInfo queryEntity)
    {
        return (GgkzDepartInfo) getSqlSessionTemplate().selectOne("GgkzDepartInfo.selectMaxEntity", queryEntity);
    }
    
    public GgkzDepartInfo selectEntity(GgkzDepartInfo queryEntity)
    {
        return (GgkzDepartInfo) getSqlSessionTemplate().selectOne("GgkzDepartInfo.selectOneByEntity", queryEntity);
    }
    
	public String selectbaseid() {
		String affectCount = "";
		Object obj = getSqlSessionTemplate().selectOne("GgkzDepartInfo.selectbaseid", null);
		if (obj != null) {
			return affectCount = (String) obj;
		}
		return affectCount;	
	}

    public int selectDeptIdByEntityProc(GgkzDepartInfo entity)
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