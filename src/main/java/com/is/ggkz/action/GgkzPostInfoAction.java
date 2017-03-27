package com.is.ggkz.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzPostInfo;
import com.is.ggkz.entity.query.GgkzPostInfoQuery;
import com.is.ggkz.service.GgkzPostInfoServiceImpl;

/**
 *
 * @ClassName: GgkzPostInfoAction
 * @Description: GgkzPostInfo表对应的Action类
 * @author 
 * @date 2013-02-27 14:19:37 *
 */
@Namespace("/ggkz")
public class GgkzPostInfoAction extends BaseStruts2Action
{
    private static final long       serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger     LOGGER           = LoggerFactory.getLogger(GgkzPostInfoAction.class);

    // -- 页面属性 --//
    private GgkzPostInfo            entity;

    private GgkzPostInfoQuery       queryEntity;

    @Autowired
    private GgkzPostInfoServiceImpl ggkzPostInfoServiceImpl;

    public String list() throws Exception
    {
        return "ggkz-post-info/list";
    }

    public String toAdd()
    {
        String deptId = entity.getPostId();
        if (deptId == null || "".equals(deptId))
        {
            int rst = ggkzPostInfoServiceImpl.selectDeptIdByEntityProc(entity);
            if (rst == 1)
            {
                entity.setPostId(entity.getHigherPostId() + entity.getPostId());
            }
            else
            {
                setInfoMessage("生成部门ID失败！");
                return SUCCESS;
            }
        }
        return "ggkz-post-info/add";
    }

    public String create()
    {
        int rst = ggkzPostInfoServiceImpl.insertEntity(entity);
        if (rst == 1)
        {
            setInfoMessage("添加成功！");
            return SUCCESS;
        }
        else
        {
            setWarnMessage("添加失败！");
            return SUCCESS;
        }
    }

    public String delete()
    {
        entity.setPostId(entity.getPostId() + "%");
        int rst = ggkzPostInfoServiceImpl.deleteByEntityCascade(entity);
        if (rst == 1)
        {
            setInfoMessage("删除成功！");
            return SUCCESS;
        }
        else
        {
            setWarnMessage("删除失败！");
            return SUCCESS;
        }
    }

    public GgkzPostInfo getEntity()
    {
        return entity;
    }

    public void setEntity(GgkzPostInfo entity)
    {
        this.entity = entity;
    }

    public GgkzPostInfoQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(GgkzPostInfoQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

}
