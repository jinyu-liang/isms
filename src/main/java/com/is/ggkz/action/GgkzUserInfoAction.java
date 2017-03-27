package com.is.ggkz.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzResourceInfo;
import com.is.ggkz.entity.GgkzRoleInfo;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.GgkzUserRole;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzRoleInfoServiceImpl;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.sys.entity.SysDict;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.ztree.ZTreeData;
import com.is.utils.ztree.ZtreeHelper;

/**
 * 
 * @ClassName: GgkzUserInfoAction
 * @Description: GgkzUserInfo表对应的Action类
 * @author 
 * @date 2013-02-27 14:18:49 *
 */
@Namespace("/ggkz")
public class GgkzUserInfoAction extends BaseStruts2Action
{
    private static final long       serialVersionUID = 1L;

    private static final Logger     LOGGER           = LoggerFactory.getLogger(GgkzUserInfoAction.class);

    private GgkzUserInfo            entity;

    private GgkzUserInfoQuery       queryEntity;

    /** 角色service */
    private GgkzRoleInfoServiceImpl ggkzRoleInfoServiceImpl;

    /** 角色列表 */
    private List<GgkzRoleInfo>      ggkzRoleInfoList;

    /** 选中的角色列表 */
    private Map<String, String>     checkedRoleList;

    /** 用户角色 */
    private String[]                roles;

    /** 性别字典 */
    private List<SysDict>           sexList;

    /** 证件类型 */
    private List<SysDict>           certificateTypeList;

    /** 用户状态 */
    private List<SysDict>           userStateList;

    /** 用户职位 */
    private List<SysDict>           userPostList;

    /** 用户部门 */
    private List<SysDict>           userDepartList;

    /** 区域部长 */
    private List<SysDict>           headUserList;
    
    private int                 containSelf      = 0;

    public List<SysDict> getUserStateList()
    {
        return userStateList;
    }

    public void setUserStateList(List<SysDict> userStateList)
    {
        this.userStateList = userStateList;
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

    public String[] getRoles()
    {
        return roles;
    }

    public void setRoles(String[] roles)
    {
        this.roles = roles;
    }

    private GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl;

    @Autowired
    public void setGgkzUserInfoServiceImpl(GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl)
    {
        this.ggkzUserInfoServiceImpl = ggkzUserInfoServiceImpl;
    }

    public GgkzUserInfoAction()
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
    public String list() throws Exception
    {
        initUserPostList();
        initUserDepartList();
        queryEntity.setUserState("Y");
        if (containSelf == 0)
        {
            queryEntity.setDepartId(sessionUser.getDepartId());
        }
        page = ggkzUserInfoServiceImpl.selectUserByPostFilterPage(queryEntity);
        return "ggkz-user-info/list";
    }

    /**
     * 转向用户输入页面
     */
    public String toAdd() throws Exception
    {
        //        dictInit();
        initUserPostList();
        initUserDepartList();
        initHeadUserList();
        // 查询角色列表
        ggkzRoleInfoList = ggkzRoleInfoServiceImpl.selectAll();
        checkedRoleList = new HashMap<String, String>();
        return "ggkz-user-info/add";
    }

    /**
     * 转向用户输入页面
     */
    public String toEdit() throws Exception
    {
        //        dictInit();
        initUserPostList();
        initUserDepartList();
        initHeadUserList();
        // 查询角色列表
        ggkzRoleInfoList = ggkzRoleInfoServiceImpl.selectAll();
        entity = ggkzUserInfoServiceImpl.selectUserByEntity(entity);
        List<GgkzUserRole> list = entity.getGgkzUserRoles();
        if (checkedRoleList == null)
        {
            checkedRoleList = new HashMap<String, String>();
        }
        if (list != null && list.size() > 0)
        {
            for (int i = 0; i < list.size(); i++)
            {
                // checkedRoleList.add(list.get(i).getRoleId());
                checkedRoleList.put(list.get(i).getRoleId(), list.get(i).getRoleId());
            }
        }
        return "ggkz-user-info/edit";
    }

    /**
     * 转向用户查看页面
     */
    public String view() throws Exception
    {
        initUserPostList();
        initUserDepartList();
        initHeadUserList();
        entity = ggkzUserInfoServiceImpl.selectUserByEntity(entity);
        return "ggkz-user-info/view";
    }

    //    private void dictInit()
    //    {
    //        userStateList = SysDictUtil.getDictByType("userState");
    //        sexList = SysDictUtil.getDictByType("Sex");
    //        certificateTypeList = SysDictUtil.getDictByType("certType");
    //    }

    /**
     * 保存数据并转向用户查看页面
     * 
     * @return
     * @throws Exception
     */
    public String add() throws Exception
    {
        entity.setUserId(KeyGen.getCommonKeyGen(PublicDict.USER_PREFIX));
        if (StringUtils.isNotEmpty(entity.getPassword()) && "1".equals(entity.getPassword()))
        {
            entity.setPassword(entity.getLoginName());
        }
        // 判断工号是否重复
        GgkzUserInfoQuery tmp = new GgkzUserInfoQuery();
        tmp.setWorkCode(entity.getWorkCode());
        List<GgkzUserInfo> tmpCodeList = ggkzUserInfoServiceImpl.selectByEnity(tmp);
        if (tmpCodeList != null && tmpCodeList.size()>0)
        {
            setWarnMessage("工号重复，请使用其它工号");
            return JSON_DATA;
        }

        // 判断用户名是否重复
        tmp = new GgkzUserInfoQuery();
        tmp.setLoginName(entity.getLoginName());
        List<GgkzUserInfo> tmpNameList = ggkzUserInfoServiceImpl.selectByEnity(tmp);
        if (tmpNameList != null && tmpNameList.size()>0)
        {
            setWarnMessage("登录名重复，请使用其它登录名");
            return JSON_DATA;
        }
        entity.setUserState("Y");
        Date ctm = new Date();
        entity.setCreateTm(ctm);
        entity.setUpdateTm(ctm);
        ggkzUserInfoServiceImpl.insertUserAndRela(entity, roles);
        setMessage("添加成功");
        // 是否默认设置密码和用户名相同
        return JSON_DATA;
    }

    /**
     * 更新数据并转向用户查看页面
     * 
     * @return
     * @throws Exception
     */
    public String edit() throws Exception
    {
        // 判断工号是否重复
        GgkzUserInfoQuery tmpQuery = new GgkzUserInfoQuery();
        tmpQuery.setWorkCode(entity.getWorkCode());
        List<GgkzUserInfo> tmpCodeList = ggkzUserInfoServiceImpl.selectByEnity(tmpQuery);
        if(tmpCodeList.size()>1){
        	setWarnMessage("工号重复，请使用其它工号");
            return JSON_DATA;
        }else if(tmpCodeList.size()==1){
        	GgkzUserInfoQuery info = new GgkzUserInfoQuery();
        	GgkzUserInfo oldInfo = new GgkzUserInfo();
        	info.setUserId(entity.getUserId());
        	oldInfo = ggkzUserInfoServiceImpl.selectOneByEntity(info);
        	if(!oldInfo.getUserId().equals(tmpCodeList.get(0).getUserId())){
        		 setWarnMessage("工号重复，请使用其它工号");
                 return JSON_DATA;
        	}
        }
//        if (tmpQuery != null)
//        {
//            if (!entity.getUserId().equals(tmpQuery.getUserId()))
//            {
//                setWarnMessage("工号重复，请使用其它工号");
//                return JSON_DATA;
//            }
//        }

        // 判断登录名是否重复
        GgkzUserInfo tmp = new GgkzUserInfo();
        tmp.setLoginName(entity.getLoginName());
        tmp = ggkzUserInfoServiceImpl.selectUserCheckById(tmp);
        if (tmp != null)
        {
            if (!entity.getUserId().equals(tmp.getUserId()))
            {
                setWarnMessage("登录名重复，请使用其它登录名");
                return JSON_DATA;
            }
        }
        entity.setUpdateTm(new Date());
        ggkzUserInfoServiceImpl.updateUserAndRela(entity, roles);
        setMessage("修改成功");
        //        return SUCCESS;
        return JSON_DATA;
    }

    /**
     * 删除数据并刷新列表页面
     * 
     * @return
     * @throws Exception
     */
    public String delete() throws Exception
    {
        if (entity == null || StringUtils.isEmpty(entity.getUserId()))
        {
        	setInfoMessage("删除失败");
        }
        String delFlag=ggkzUserInfoServiceImpl.deleteUser(entity);
        if(delFlag.equals("0")){
        	setMessage("删除成功");
        }else{
        	setInfoMessage("该用户有负责工地，不可删除");
        }
        
        return JSON_DATA;
    }

    /**
     * 获取资源菜单
     * 
     * @return
     * @throws Exception
     */
    public String menuList() throws Exception
    {
        List<GgkzResourceInfo> resourceList = sessionUser.getResource();
        List<ZTreeData> treeData = new ArrayList<ZTreeData>();
        if (resourceList != null && resourceList.size() > 0)
        {
            for (int i = 0; i < resourceList.size(); i++)
            {
                GgkzResourceInfo resource = resourceList.get(i);
                ZTreeData data = new ZTreeData();
                data.setId(resource.getResourceId());
                data.setpId(resource.getHigerResourceCode());
                data.setName(resource.getResourceName());
                data.setUrl(resource.getResourceUrl());
                data.setOpen(true);
                treeData.add(data);
            }
        }
        jsondata = ZtreeHelper.ztreeData(treeData);
        return JSON_DATA;
    }

    /**
     * 转向密码修改页面
     * 
     * @return
     * @throws Exception
     */
    public String toModiPassword() throws Exception
    {
        // String userId = sessionUser.getUserId();
        // GgkzUserInfo info = new GgkzUserInfo();
        // info.setUserId(userId);
        // entity = ggkzUserInfoServiceImpl.selectUserByEntity(info);
        return "ggkz-user-info/to-modi-password";
    }

    /**
     * 密码修改
     * 
     * @return
     * @throws Exception
     */
    public String modiPassword() throws Exception
    {
        String userId = sessionUser.getUserId();
        // 判断原密码是否正确
        GgkzUserInfo tmp = new GgkzUserInfo();
        tmp.setUserId(userId);
        tmp = ggkzUserInfoServiceImpl.selectUserByEntity(tmp);
        if (tmp == null)
        {
            setWarnMessage("用户不存在");
            return JSON_DATA;
        }
        if (StringUtils.isEmpty(tmp.getNewPassword()))
        {
            if (!tmp.getPassword().equals(entity.getPassword()))
            {
                setWarnMessage("原密码不正确");
                return JSON_DATA;
            }
        }
        else if (!tmp.getNewPassword().equals(entity.getPassword()))
        {
            setWarnMessage("原密码不正确");
            return JSON_DATA;
        }
        // 判断两个新密码是否相同
        if (!entity.getNewPassword().equals(entity.getName()))
        {
            setWarnMessage("两次输入密码不相同");
            return JSON_DATA;
        }

        GgkzUserInfo info = new GgkzUserInfo();
        info.setUserId(userId);
        info.setNewPassword(entity.getNewPassword());
        ggkzUserInfoServiceImpl.updateUserInfo(info);
        setMessage("修改成功");
        return JSON_DATA;
    }

    /**
     * 重置密码
     * 
     * @return
     * @throws Exception
     */
    public String resetPassword() throws Exception
    {
        //        if (entity == null || StringUtils.isEmpty(entity.getUserId()))
        //        {
        //            jsondata = "0";
        //        }
        //        int result = ggkzUserInfoServiceImpl.resetPassword(entity);
        //        if (result == 0)
        //        {
        //            jsondata = "0";
        //        }
        //        else
        //        {
        //            jsondata = "1";
        //        }
        //        return JSON_DATA;
        entity.setNewPassword("123456");
        int rst = ggkzUserInfoServiceImpl.updateUserInfo(entity);
        if (rst == 1)
        {
            setMessage("重置密码成功!");
        }
        else
        {
            setWarnMessage("重置密码失败!");
        }
        return JSON_DATA;
    }

    /**
     * 禁用
     * 
     * @return
     * @throws Exception
     */
    public String unableUser() throws Exception
    {
        if (entity == null || StringUtils.isEmpty(entity.getUserId()))
        {
            jsondata = "0";
        }
        int result = ggkzUserInfoServiceImpl.unableUser(entity);
        if (result == 0)
        {
            jsondata = "0";
        }
        else
        {
            jsondata = "1";
        }
        return JSON_DATA;
    }

    /**
     * 启用
     * 
     * @return
     * @throws Exception
     */
    public String enableUser() throws Exception
    {
        if (entity == null || StringUtils.isEmpty(entity.getUserId()))
        {
            jsondata = "0";
        }
        int result = ggkzUserInfoServiceImpl.enableUser(entity);
        if (result == 0)
        {
            jsondata = "0";
        }
        else
        {
            jsondata = "1";
        }
        return JSON_DATA;
    }

    /**
     * 登陆后转向主页面
     * 
     * @return
     * @throws Exception
     */
    public String mainIndex() throws Exception
    {
        return "index";
    }

    /**
     * 转向密码修改页面（新）
     * 
     * @return
     * @throws Exception
     */
    public String toResetPwd() throws Exception
    {
        if(StringUtils.isNotEmpty(queryEntity.getUserId())){
            entity = ggkzUserInfoServiceImpl.selectOneByEntity(queryEntity);
        }
        return "ggkz-user-info/reset-password";
    }
    /**
     * 修改密码（新）
     * @return
     */
    public String resetPwd(){
        if (!entity.getNewPassword().equals(entity.getName()))
        {
            setWarnMessage("两次输入密码不相同");
            return JSON_DATA;
        }
        GgkzUserInfo info = new GgkzUserInfo();
        info.setUserId(entity.getUserId());
        info.setNewPassword(entity.getNewPassword());
        int rst = ggkzUserInfoServiceImpl.updateUserInfo(info);
        if (rst == 1)
        {
            setMessage("修改密码成功!");
        }
        else
        {
            setInfoMessage("修改密码失败!");
        }
        return JSON_DATA;
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

    public void setQueryEntity(GgkzUserInfoQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public List<GgkzRoleInfo> getGgkzRoleInfoList()
    {
        return ggkzRoleInfoList;
    }

    public void setGgkzRoleInfoList(List<GgkzRoleInfo> ggkzRoleInfoList)
    {
        this.ggkzRoleInfoList = ggkzRoleInfoList;
    }

    @Autowired
    public void setGgkzRoleInfoServiceImpl(GgkzRoleInfoServiceImpl ggkzRoleInfoServiceImpl)
    {
        this.ggkzRoleInfoServiceImpl = ggkzRoleInfoServiceImpl;
    }

    public Map<String, String> getCheckedRoleList()
    {
        return checkedRoleList;
    }

    public void setCheckedRoleList(Map<String, String> checkedRoleList)
    {
        this.checkedRoleList = checkedRoleList;
    }

    private void initUserPostList()
    {
        userPostList = PmsDictUtil.getDictByType("post");
    }

    public List<SysDict> getUserPostList()
    {
        return userPostList;
    }

    public void setUserPostList(List<SysDict> userPostList)
    {
        this.userPostList = userPostList;
    }

    private void initUserDepartList()
    {
        userDepartList = PmsDictUtil.getDictByTypeanden("depart",sessionUser.getDepartId());
    }

    public List<SysDict> getUserDepartList()
    {
        return userDepartList;
    }

    public void setUserDepartList(List<SysDict> userDepartList)
    {
        this.userDepartList = userDepartList;
    }

    private void initHeadUserList()
    {
        headUserList = PmsDictUtil.getDictByType("headUser");
    }

    public List<SysDict> getHeadUserList()
    {
        return headUserList;
    }

    public void setHeadUserList(List<SysDict> headUserList)
    {
        this.headUserList = headUserList;
    }

    @Override
    public String getWarnMessage()
    {
        // TODO Auto-generated method stub
        return super.getWarnMessage();
    }

    @Override
    public String getMessage()
    {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    @Override
    public String getInfoMessage()
    {
        // TODO Auto-generated method stub
        return super.getInfoMessage();
    }
    

}
