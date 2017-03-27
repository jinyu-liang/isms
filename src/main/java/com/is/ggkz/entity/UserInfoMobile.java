package com.is.ggkz.entity;

import java.io.Serializable;

import com.is.utils.ChineseFirstLetterUtil;

/**
 * 
 * <p>文件名称: UserInfoMobile.java</p>
 * <p>文件描述: 本类描述</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年10月30日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class UserInfoMobile implements Serializable
{

    private static final long serialVersionUID = 1L;

    /* 用户Id */
    private String            userId;

    /* 姓名 */
    private String            name;

    /* 用户状态 */
    private String            userState;

    private String            nameLetter;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
        this.nameLetter = ChineseFirstLetterUtil.getAllFirstLetter(name);
    }

    public String getUserState()
    {
        return userState;
    }

    public void setUserState(String userState)
    {
        this.userState = userState;
    }

    public String getNameLetter()
    {
        return nameLetter;
    }

    //    public void setNameLetter(String nameLetter)
    //    {
    //        this.nameLetter = nameLetter;
    //    }

}