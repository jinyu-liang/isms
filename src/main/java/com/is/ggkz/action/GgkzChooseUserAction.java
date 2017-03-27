package com.is.ggkz.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.dict.SysDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.sys.entity.SysDict;

/**
 * 
 * @ClassName: GgkzUserInfoAction
 * @Description: GgkzUserInfo表对应的Action类
 * @author 
 * @date 2013-02-27 14:18:49 *
 */
@Namespace("/ggkz")
public class GgkzChooseUserAction extends BaseStruts2Action
{
    private static final long   serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER           = LoggerFactory.getLogger(GgkzChooseUserAction.class);

    private GgkzUserInfo        entity;

    private GgkzUserInfoQuery   queryEntity;

    /** 性别字典 */
    private List<SysDict>       sexList;

    /** 证件类型 */
    private List<SysDict>       certificateTypeList;

    private List<SysDict>       stateList;

    private List<GgkzUserInfo>  userInfoList;

    /** 用户职位 */
    private List<SysDict>       userPostList;

    private String              checkid;

    private String              checkname;

    private String              previewids;

    private int                 containSelf      = 0;

    public List<SysDict> getStateList()
    {
        return stateList;
    }

    public void setStateList(List<SysDict> stateList)
    {
        this.stateList = stateList;
    }

    /**
     * 控件类别填充dom元素ID
     */
    private String toolTpid;

    public String getPreviewids()
    {
        return previewids;
    }

    public void setPreviewids(String previewids)
    {
        this.previewids = previewids;
    }

    public String getCheckid()
    {
        return checkid;
    }

    public void setCheckid(String checkid)
    {
        this.checkid = checkid;
    }

    public String getCheckname()
    {
        return checkname;
    }

    public void setCheckname(String checkname)
    {
        this.checkname = checkname;
    }

    /**
     * json格式数据的字符串形式
     */
    public String jsondata;

    public String getJsondata()
    {
        return jsondata;
    }

    public List<SysDict> getCertificateTypeList()
    {
        return certificateTypeList;
    }

    public void setCertificateTypeList(List<SysDict> certificateTypeList)
    {
        this.certificateTypeList = certificateTypeList;
    }

    public void setJsondata(String jsondata)
    {
        this.jsondata = jsondata;
    }

    public List<SysDict> getSexList()
    {
        return sexList;
    }

    public void setSexList(List<SysDict> sexList)
    {
        this.sexList = sexList;
    }

    private GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl;

    @Autowired
    public void setGgkzUserInfoServiceImpl(GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl)
    {
        this.ggkzUserInfoServiceImpl = ggkzUserInfoServiceImpl;
    }

    public GgkzChooseUserAction()
    {
        super();
        if (entity == null)
        {
            entity = new GgkzUserInfo();
        }
        if (queryEntity == null)
        {
            queryEntity = new GgkzUserInfoQuery();
        }
    }

    /**
     * 用户列表
     * 
     * @return
     * @throws Exception
     */
    public String publicMultiChooseUser() throws Exception
    {
        //        dictInit();
        initUserPostList();
        queryEntity.setUserState("Y");
        if (containSelf == 0)
        {
            queryEntity.setExcludeUser(sessionUser.getUserId());
        }
        page = ggkzUserInfoServiceImpl.selectUserPage(queryEntity);

        userInfoList = ggkzUserInfoServiceImpl.selectUsersByIds(previewids);
        // 查找已经选中的用户
        return "ggkz-user-info/public-multi-choose-user";
    }

    /**
     * 根据条件查询用户列表分页（支持用职位条件过滤）
     * @return
     * @throws Exception
     */
    public String publicMultiChooseUserByPostFilter() throws Exception
    {
        //        dictInit();
        initUserPostList();
        queryEntity.setUserState("Y");
        if (containSelf == 0)
        {
            queryEntity.setExcludeUser(sessionUser.getUserId());
        }
        page = ggkzUserInfoServiceImpl.selectUserByPostFilterPage(queryEntity);

        userInfoList = ggkzUserInfoServiceImpl.selectUsersByIds(previewids);
        // 查找已经选中的用户
        return "ggkz-user-info/public-multi-choose-user";
    }

    /**
     * 多选用户列表数据
     * 
     * @return
     * @throws Exception
     */
    public String publicMultiChooseUserByPostFilterData() throws Exception
    {
        //        dictInit();
        initUserPostList();
        queryEntity.setUserState("Y");
        if (containSelf == 0)
        {
            queryEntity.setExcludeUser(sessionUser.getUserId());
        }
        page = ggkzUserInfoServiceImpl.selectUserByPostFilterPage(queryEntity);
        return "ggkz-user-info/public-multi-choose-user";
    }

    /**
     * 单选用户列表
     * 
     * @return
     * @throws Exception
     */
    public String publicSingleChooseUser() throws Exception
    {
        initUserPostList();
        //        dictInit();
        queryEntity.setUserState("Y");
        if (containSelf == 0)
        {
            queryEntity.setExcludeUser(sessionUser.getUserId());
        }
        page = ggkzUserInfoServiceImpl.selectUserPage(queryEntity);
        return "ggkz-user-info/public-single-choose-user";
    }

    public String publicSingleChooseUserByPostFilter() throws Exception
    {
        //initUserPostList();
        //        dictInit();
        initUserPostList();
        initSexList();
        queryEntity.setUserState("Y");
        if (containSelf == 0)
        {
        	queryEntity.setDepartId(sessionUser.getDepartId());
        }
        page = ggkzUserInfoServiceImpl.selectUserByPostFilterPage(queryEntity);
        return "ggkz-user-info/public-single-choose-user";
    }

    //    private void dictInit()
    //    {
    //        stateList = SysDictUtil.getDictByType("userState");
    //        sexList = SysDictUtil.getDictByType("Sex");
    //        certificateTypeList = SysDictUtil.getDictByType("certType");
    //    }

    private void initSexList()
    {
        sexList = SysDictUtil.getDictByType("Sex");
    }

    private void initUserPostList()
    {
        userPostList = PmsDictUtil.getDictByType("post");
    }

    public GgkzUserInfo getEntity()
    {
        return entity;
    }

    public void setEntity(GgkzUserInfo entity)
    {
        this.entity = entity;
    }

    public GgkzUserInfoQuery getQueryEntity()
    {
        return queryEntity;
    }

    public List<GgkzUserInfo> getUserInfoList()
    {
        return userInfoList;
    }

    public void setUserInfoList(List<GgkzUserInfo> userInfoList)
    {
        this.userInfoList = userInfoList;
    }

    public void setQueryEntity(GgkzUserInfoQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public String getToolTpid()
    {
        return toolTpid;
    }

    public void setToolTpid(String toolTpid)
    {
        this.toolTpid = toolTpid;
    }

    public List<SysDict> getUserPostList()
    {
        return userPostList;
    }

    public void setUserPostList(List<SysDict> userPostList)
    {
        this.userPostList = userPostList;
    }

    public int getContainSelf()
    {
        return containSelf;
    }

    public void setContainSelf(int containSelf)
    {
        this.containSelf = containSelf;
    }

}
