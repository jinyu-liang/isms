package com.is.ggkz.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzPostInfoDaoImpl;
import com.is.ggkz.entity.GgkzPostInfo;

/**
 *
 * @ClassName: GgkzPostInfoServiceImpl
 * @Description: GgkzPostInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:39 *
 */
// Spring Bean的标识.
@Component
//默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzPostInfoServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger      logger = LoggerFactory.getLogger(GgkzPostInfoServiceImpl.class);

    public GgkzPostInfoDaoImpl ggkzPostInfoDaoImpl;

    public List<GgkzPostInfo> selectPostAll(GgkzPostInfo queryEntity)
    {
        return ggkzPostInfoDaoImpl.selectByEntity(queryEntity);
    }

    public int insertEntity(GgkzPostInfo entity)
    {
        return ggkzPostInfoDaoImpl.insert(entity);
    }

    public int selectDeptIdByEntityProc(GgkzPostInfo entity)
    {
        return ggkzPostInfoDaoImpl.selectDeptIdByEntityProc(entity);
    }

    public int deleteByEntityCascade(GgkzPostInfo entity)
    {
        return ggkzPostInfoDaoImpl.deleteByEntityCascade(entity);
    }

    @Autowired
    public void setGgkzPostInfoDaoImpl(GgkzPostInfoDaoImpl ggkzPostInfoDaoImpl)
    {
        this.ggkzPostInfoDaoImpl = ggkzPostInfoDaoImpl;
    }
}
