package com.is.pms.msginteract.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pms.msginteract.entity.MsgMessageInfo;

/**
 * 
 * <p>文件名称: MsgMessageInfoDaoImpl.java</p>
 * <p>文件描述: 消息互动DAO</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月18日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Component
public class MsgMessageInfoDaoImpl extends Mybatis3Dao<MsgMessageInfo>
{

    private static final Logger   LOGGER    = LoggerFactory.getLogger(MsgMessageInfoDaoImpl.class);

    protected static final String NAMESPACE = "MsgMessageInfo";

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }

    @Override
    @Deprecated
    public int insert(MsgMessageInfo entity)
    {
        if (LOGGER.isErrorEnabled())
        {
            LOGGER.error("该方法已经过时，不能再用！");
        }
        return 0;
    }

    @Override
    @Deprecated
    public int deleteByEntity(MsgMessageInfo entity)
    {
        if (LOGGER.isErrorEnabled())
        {
            LOGGER.error("该方法已经过时，不能再用！");
        }
        return 0;
    }

    @Override
    @Deprecated
    public int updateByEntity(MsgMessageInfo entity)
    {
        if (LOGGER.isErrorEnabled())
        {
            LOGGER.error("该方法已经过时，不能再用！");
        }
        return 0;
    }

    @Override
    @Deprecated
    public MsgMessageInfo selectOneByEntity(MsgMessageInfo entity)
    {
        if (LOGGER.isErrorEnabled())
        {
            LOGGER.error("该方法已经过时，不能再用！");
        }
        return null;
    }

    @Override
    @Deprecated
    public List<MsgMessageInfo> selectAll()
    {
        if (LOGGER.isErrorEnabled())
        {
            LOGGER.error("该方法已经过时，不能再用！");
        }
        return null;
    }

    @Override
    @Deprecated
    public List<MsgMessageInfo> selectByEntity(MsgMessageInfo entity)
    {
        if (LOGGER.isErrorEnabled())
        {
            LOGGER.error("该方法已经过时，不能再用！");
        }
        return null;
    }

    /**
     * 插入发件箱
     * @param entity
     * @return
     */
    public int insertEntitySend(MsgMessageInfo entity)
    {
        return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertSend", entity);
    }

    /**
     * 插入收件箱
     * @param entity
     * @return
     */
    public int insertEntityReceive(MsgMessageInfo entity)
    {
        return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertReceive", entity);
    }

    /**
     * 插入草稿箱
     * @param entity
     * @return
     */
    public int insertEntityDraft(MsgMessageInfo entity)
    {
        return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertDrft", entity);
    }

    /**
     * 清空垃圾箱
     * @param entity
     * @return
     */
    public int deleteGarbage(MsgMessageInfo entity)
    {
        return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteGarbageByEntity", entity);
    }

    /**
     * 删除发件箱插入垃圾箱
     * @param ids
     * @return
     */
    public int insertGarbageselect(List<String> ids, String tp)
    {
        if ("send".equals(tp))
        {
            return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectSend", ids);
        }
        else if ("draft".equals(tp))
        {
            return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectDraft", ids);
        }
        else if ("garbage".equals(tp))
        {
            return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectGarbage", ids);
        }
        else if ("receive".equals(tp))
        {
            return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectReceive", ids);
        }
        return 0;
    }

    /**
     * 通过ids删除发件箱
     * @param ids
     * @return
     */
    public int deleteMsgByIds(List<String> ids, String tp)
    {
        if ("send".equals(tp))
        {
            return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteSendByIds", ids);
        }
        else if ("draft".equals(tp))
        {
            return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteDraftByIds", ids);
        }
        else if ("garbage".equals(tp))
        {
            return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteGarbageByIds", ids);
        }
        else if ("receive".equals(tp))
        {
            return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteReceiveByIds", ids);
        }
        return 0;
    }

    /**
     * 删除收件箱消息
     * @param ids 消息发件id
     * @return
     */
    public int deleteReceiveMsgByIds(List<String> ids)
    {
        return getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteReceiveBySendIds", ids);
    }

    /**
     * 查询发件箱
     * @param entity
     * @return
     */
    public MsgMessageInfo selectSendByEntity(MsgMessageInfo entity)
    {
        return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectSendByEntity", entity);
    }

    /**
     * 移动消息
     * @param ids
     * @param msgbox
     * @param tp
     * @return
     */
    public int insertRemove(List<String> ids, String msgbox, String tp)
    {
        if ("send".equals(tp))
        {
            if ("draft".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertDraftSelectSend", ids);
            }
            else if ("garbage".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectSend", ids);
            }
            else if ("receive".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertReceiveSelectSend", ids);
            }
        }
        else if ("draft".equals(tp))
        {
            if ("garbage".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectDraft", ids);
            }
            else if ("receive".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertReceiveSelectDraft", ids);
            }
            else if ("send".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertSendSelectDraft", ids);
            }
        }
        else if ("garbage".equals(tp))
        {
            if ("draft".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertDraftSelectGarbage", ids);
            }
            else if ("receive".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertReceiveSelectGarbage", ids);
            }
            else if ("send".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertSendSelectGarbage", ids);
            }
        }
        else if ("receive".equals(tp))
        {
            if ("draft".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertDraftSelectReceive", ids);
            }
            else if ("garbage".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertGarbageSelectReceive", ids);
            }
            else if ("send".equals(msgbox))
            {
                return getSqlSessionTemplate().insert(getIbatisMapperNamesapce() + ".insertSendSelectReceive", ids);
            }
        }
        return 0;
    }

    /**
     * 删除消息
     * @param ids
     * @param tp
     * @return
     */
    public int deleteRemove(List<String> ids, String tp)
    {
        if ("send".equals(tp))
        {
            getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteSendByIds", ids);
        }
        else if ("draft".equals(tp))
        {
            getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteDraftByIds", ids);
        }
        else if ("garbage".equals(tp))
        {
            getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteGarbageByIds", ids);
        }
        else if ("receive".equals(tp))
        {
            getSqlSessionTemplate().delete(getIbatisMapperNamesapce() + ".deleteReceiveByIds", ids);
        }
        return 0;
    }

    /**
     * 查询消息
     * @param queryEntity
     * @param tp
     * @return
     */
    public MsgMessageInfo selectOneEntityByEntity(MsgMessageInfo queryEntity, String tp)
    {
        if ("send".equals(tp))
        {
            return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectSendByEntity", queryEntity);
        }
        else if ("draft".equals(tp))
        {
            return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectDraftByEntity", queryEntity);
        }
        else if ("garbage".equals(tp))
        {
            return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectGarbageByEntity", queryEntity);
        }
        else if ("receive".equals(tp))
        {
            return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectReceiveByEntity", queryEntity);
        }
        else if ("search".equals(tp))
        {
            return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectSerachByPage", queryEntity);
        }
        else if ("allmsg".equals(tp))
        {
            return (MsgMessageInfo) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectSendByEntity", queryEntity);
        }
        return null;
    }

    /**
     * 更新消息状态
     * @param queryEntity
     * @param tp
     * @return
     */
    public int updateEntityByEntity(MsgMessageInfo queryEntity, String tp)
    {
        if ("receive".equals(tp))
        {
            return getSqlSessionTemplate().update(getIbatisMapperNamesapce() + ".updateReceiveByEntity", queryEntity);
        }
        return 0;
    }

    /**
     * 查询消息
     * @param ids
     * @param tp
     * @return
     */
    public List<MsgMessageInfo> selectEntityByIds(List<String> ids, String tp)
    {
        if (ids == null || ids.isEmpty())
        {
            return null;
        }
        if ("send".equals(tp))
        {
            return getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".selectSendByIds", ids);
        }
        else if ("draft".equals(tp))
        {
            return getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".selectDraftByIds", ids);
        }
        else if ("garbage".equals(tp))
        {
            return getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".selectGarbageByIds", ids);
        }
        else if ("receive".equals(tp))
        {
            return getSqlSessionTemplate().selectList(getIbatisMapperNamesapce() + ".selectReceiveByIds", ids);
        }
        return null;
    }

    /**
     * 查询未读消息总数
     * @param entity
     * @return
     */
    public int selectUnreadMsgCount(MsgMessageInfo entity)
    {
        return (Integer) getSqlSessionTemplate().selectOne(getIbatisMapperNamesapce() + ".selectReceiveByPageCount", entity);
    }

}