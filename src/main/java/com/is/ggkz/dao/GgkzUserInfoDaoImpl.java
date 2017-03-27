package com.is.ggkz.dao;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.UserInfoMobile;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.utils.StringUtils;

/**
 * 
 * @ClassName: GgkzUserInfoDaoImpl
 * @Description: GgkzUserInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:18:49 *
 */
@Component
public class GgkzUserInfoDaoImpl extends Mybatis3Dao<GgkzUserInfo>
{

    @SuppressWarnings("unused")
    private static final Logger   LOGGER    = LoggerFactory.getLogger(GgkzUserInfoDaoImpl.class);

    protected static final String NAMESPACE = "GgkzUserInfo";

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }
    @Autowired
    private PmsMessageTopicProducer messageTopicProducer;
    /**
     * 根据查询条件获取分页列表
     * 
     * @param ggkzUserInfoQuery
     * @return
     */
    public Page selectUserPage(GgkzUserInfoQuery queryEntity)
    {
        return this.pageQuery("GgkzUserInfo.selectByPage", queryEntity);
    }

    public GgkzUserInfo selectLoginEntity(GgkzUserInfo entity)
    {
        return (GgkzUserInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectLoginEntity", entity);
    }

    /**
     * 检查员工编号重复问题
     * @param entity
     * @return
     */
    public GgkzUserInfo selectUserCheckById(GgkzUserInfo entity)
    {
        return (GgkzUserInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectUserCheckById", entity);
    }

    /**
     * 根据条件查询用户列表分页（支持用职位条件过滤）
     * @param queryEntity
     * @return
     */
    public Page selectUserByPostFilterPage(GgkzUserInfoQuery queryEntity)
    {
        return this.pageQuery("GgkzUserInfo.selectByPostFilterByPage", queryEntity);
    }

    /**
     * 重置密码
     * 
     * @param info
     * @return
     */
    public int resetPassword(GgkzUserInfo info)
    {
        if (info == null || StringUtils.isEmpty(info.getUserId()))
        {
            return 0;
        }
        return getSqlSessionTemplate().update("GgkzUserInfo.resetPassword", info);
    }

    /**
     * 根据用户id查找用户
     * 
     * @param userIdList
     * @return
     */
    public List<GgkzUserInfo> selectUsersByIds(List<String> userIdList)
    {
        return getSqlSessionTemplate().selectList("GgkzUserInfo.selectUsersByIds", userIdList);
    }

    public List<GgkzUserInfo> selectUsers()
    {
        return getSqlSessionTemplate().selectList("GgkzUserInfo.selectAll", null);
    }

    /**
     * 更新对象所属区域部长id
     * @return
     */
    public int updatePost(String userId)
    {
        return getSqlSessionTemplate().update("GgkzUserInfo.updatePost", userId);
    }

    public List<UserInfoMobile> selectMobileUsers(GgkzUserInfoQuery ggkzUserInfoQuery)
    {
        return getSqlSessionTemplate().selectList("GgkzUserInfo.selectMobileAll", ggkzUserInfoQuery);
    }

    public List<GgkzUserInfo> selectByEntityNotSelf(GgkzUserInfoQuery query)
    {
        return getSqlSessionTemplate().selectList("GgkzUserInfo.selectByEntityNotSelf", query);

    }

}