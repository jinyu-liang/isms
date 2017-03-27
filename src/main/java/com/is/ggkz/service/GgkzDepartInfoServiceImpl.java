package com.is.ggkz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzDepartInfoDaoImpl;
import com.is.ggkz.entity.GgkzDepartInfo;
import com.is.ggkz.entity.query.GgkzDepartInfoQuery;
import com.is.ggkz.entity.query.GgkzRoleInfoQuery;

/**
 *
 * @ClassName: GgkzDepartInfoServiceImpl
 * @Description: GgkzDepartInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:39 *
 */
// Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzDepartInfoServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger        logger = LoggerFactory.getLogger(GgkzDepartInfoServiceImpl.class);

    public GgkzDepartInfoDaoImpl ggkzDepartInfoDaoImpl;

    public List<GgkzDepartInfo> selectDepartAll(GgkzDepartInfo queryEntity)
    {
        return ggkzDepartInfoDaoImpl.selectByEntity(queryEntity);
    }
    
    public GgkzDepartInfo selectMaxEntity(GgkzDepartInfo queryEntity)
    {
        return ggkzDepartInfoDaoImpl.selectMaxEntity(queryEntity);
    }
    
    public GgkzDepartInfo selectEntity(GgkzDepartInfo queryEntity)
    {
        return ggkzDepartInfoDaoImpl.selectEntity(queryEntity);
    }
    

    public int insertEntity(GgkzDepartInfo entity)
    {
        return ggkzDepartInfoDaoImpl.insert(entity);
    }
    
    public String selectbaseid()
    {
        return ggkzDepartInfoDaoImpl.selectbaseid();
    }

    public int selectDeptIdByEntityProc(GgkzDepartInfo entity)
    {
        return ggkzDepartInfoDaoImpl.selectDeptIdByEntityProc(entity);
    }

    public int deleteByEntityCascade(GgkzDepartInfo entity)
    {
        return ggkzDepartInfoDaoImpl.deleteByEntityCascade(entity);
    }

    @Autowired
    public void setGgkzDepartInfoDaoImpl(GgkzDepartInfoDaoImpl ggkzDepartInfoDaoImpl)
    {
        this.ggkzDepartInfoDaoImpl = ggkzDepartInfoDaoImpl;
    }
    
	/**
	 * 分布查詢
	 * 
	 * @param query
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page pageQuery(GgkzDepartInfoQuery query) {
		return ggkzDepartInfoDaoImpl
				.pageQuery("GgkzDepartInfo.selectByPage", query);
	}
}
