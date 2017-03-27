package com.is.ggkz.entity;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * <p>文件名称: GgkzPostInfo.java</p>
 * <p>文件描述: 本类描述</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月14日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class GgkzPostInfo extends AbstractBaseEntity
{

    private static final long  serialVersionUID     = 1L;

    /* 部门代码 */
    public static final String ALIAS_POST_ID        = "postId";

    /* 上级部门代码 */
    public static final String ALIAS_HIGHER_POST_ID = "higherPostId";

    /* 部门名称 */
    public static final String ALIAS_POST_NAME      = "postName";

    /* 备注 */
    public static final String ALIAS_NOTE           = "note";

    /* 部门代码 */
    private String             postId;

    /* 上级部门代码 */
    private String             higherPostId;

    /* 部门名称 */
    private String             postName;

    /* 备注 */
    private String             note;

    /**
     * 执行存储过程返回结果
     */
    private Integer            result;

    public String getPostId()
    {
        return postId;
    }

    public void setPostId(String postId)
    {
        this.postId = postId;
    }

    public String getHigherPostId()
    {
        return higherPostId;
    }

    public void setHigherPostId(String higherPostId)
    {
        this.higherPostId = higherPostId;
    }

    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Integer getResult()
    {
        return result;
    }

    public void setResult(Integer result)
    {
        this.result = result;
    }

}