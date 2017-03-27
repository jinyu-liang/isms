package com.is.ggkz.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzAuthInfo;
import com.is.ggkz.entity.GgkzAuthMobileInfo;
import com.is.ggkz.entity.GgkzRoleInfo;
import com.is.ggkz.entity.query.GgkzRoleInfoQuery;
import com.is.ggkz.service.GgkzRoleInfoServiceImpl;
import com.is.utils.StringUtils;

/**
 * 
 * @ClassName: GgkzRoleInfoAction
 * @Description: GgkzRoleInfo表对应的Action类
 * @author 
 * @date 2013-02-27 14:18:56 *
 */
@Namespace("/ggkz")
public class GgkzRoleInfoAction extends BaseStruts2Action
{
    private static final long        serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger      LOGGER           = LoggerFactory.getLogger(GgkzRoleInfoAction.class);

    @Autowired
    private GgkzRoleInfoServiceImpl  ggkzRoleInfoServiceImpl;

    // 页面属性
    private GgkzRoleInfo             entity;                                                              // 实体对象

    private List<GgkzAuthInfo>       ggkzAuthInfoList;                                                    // 所有角色权限对象

    private List<GgkzAuthMobileInfo> ggkzAuthMobileInfoList;                                              // 所有角色权限对象

    private List<GgkzAuthInfo>       ggkzAuthInfoListChecked;                                             // 角色拥有的权限对象

    private List<GgkzAuthMobileInfo> ggkzAuthMobileInfoListChecked;                                       // 角色拥有的终端权限对象

    private GgkzRoleInfoQuery        ggkzRoleInfoQuery;                                                   // query对象

    private Map<String, Integer>     checkedReView;

    private Map<String, Integer>     checkedMobileReView;

    // private String authIds;// 选中的权限ids
    // private String mobileAuthIds;// 选中的终端权限ids
    private String[]       checkbox;

    private String[] mobileCheckbox;

    public GgkzRoleInfoAction()
    {
        super();
        if (entity == null)
        {
            entity = new GgkzRoleInfo();
        }
        if (ggkzRoleInfoQuery == null)
        {
            ggkzRoleInfoQuery = new GgkzRoleInfoQuery();
        }
    }

    /**
     * 删除角色信息及角色权限对象
     * 
     * @return
     * @throws Exception
     */
    public String delete() throws Exception
    {

        if (entity == null || StringUtils.isEmpty(entity.getRoleId()))
        {
            setWarnMessage("删除失败");
        }
        ggkzRoleInfoServiceImpl.deleteManyRoleInfo(entity.getRoleId());
        setMessage("删除成功");
        return JSON_DATA;
    }

    /**
     * 跳转到list页面
     * 
     * @return
     * @throws Exception
     */
    public String list() throws Exception
    {
        page = ggkzRoleInfoServiceImpl.pageQuery(ggkzRoleInfoQuery);
        return "ggkz-role-info/list";
    }

    /**
     * 跳转到创建页面
     * 
     * @return
     * @throws Exception
     */
    public String toCreate() throws Exception
    {
        ggkzAuthMobileInfoList = ggkzRoleInfoServiceImpl.selectAuthMobileInfo();
        ggkzAuthInfoList = ggkzRoleInfoServiceImpl.selectAuthInfo();// 初始添加角色页面，查询所有权限对象
        if (ggkzAuthInfoList == null)
        {
            ggkzAuthInfoList = new ArrayList<GgkzAuthInfo>();
        }
        if (ggkzAuthMobileInfoList == null)
        {
            ggkzAuthMobileInfoList = new ArrayList<GgkzAuthMobileInfo>();
        }
        return "ggkz-role-info/add";
    }

    /**
     * 保存角色对象
     */
    public String create() throws Exception
    {
        if (isExist(entity.getRoleName()))
        {
            // 存在重复的
            setWarnMessage("角色名称已经存在");
        }
        if (ggkzRoleInfoServiceImpl.saveRoleInfoAndAuth(entity, checkbox, mobileCheckbox) != 0)
        {// 保存角色和角色权限对象
            setMessage("添加成功");
        }
        else
        {
            setWarnMessage("添加失败");
        }
        return JSON_DATA;
    }
    
    /**
     * 保存角色对象
     */
    public String create1() throws Exception
    {
        setMessage("添加成功");
        return JSON_DATA;
    }

    /**
     * 查询角色对象跳转到编辑页面
     */
    public String toEdit() throws Exception
    {
        entity = ggkzRoleInfoServiceImpl.getRoleInfo(entity.getRoleId());
        ggkzAuthInfoList = ggkzRoleInfoServiceImpl.selectAuthInfo();// 查询所有权限对象
        ggkzAuthMobileInfoList = ggkzRoleInfoServiceImpl.selectAuthMobileInfo();
        if (ggkzAuthInfoList == null)
        {
            ggkzAuthInfoList = new ArrayList<GgkzAuthInfo>();
        }
        if (ggkzAuthMobileInfoList == null)
        {
            ggkzAuthMobileInfoList = new ArrayList<GgkzAuthMobileInfo>();
        }
        ggkzAuthInfoListChecked = ggkzRoleInfoServiceImpl.selectAuthInfoByRoleId(entity.getRoleId());// 查询用户所拥有的权限对象
        ggkzAuthMobileInfoListChecked = ggkzRoleInfoServiceImpl.selectAuthMobileInfoByRoleId(entity.getRoleId());
        if (checkedReView == null)
        {
            checkedReView = new HashMap<String, Integer>();
        }
        for (GgkzAuthInfo item : ggkzAuthInfoList)
        {
            String key = item.getAuthName();
            if (key != null && !"".equals(key))
            {
                key = key.split("-")[0];
                if (checkedReView.containsKey(key))
                {
                    checkedReView.put(key, checkedReView.get(key) + 1);
                }
                else
                {
                    checkedReView.put(key, 1);
                }
            }
        }
        for (GgkzAuthInfo item : ggkzAuthInfoListChecked)
        {
            String key = item.getAuthName();
            if (key != null && !"".equals(key))
            {
                key = key.split("-")[0];
                if (checkedReView.containsKey(key))
                {
                    if (checkedReView.get(key) > 0)
                    {
                        checkedReView.put(key, checkedReView.get(key) * -1);
                    }
                    checkedReView.put(key, checkedReView.get(key) + 1);
                }
            }
        }
        if (checkedMobileReView == null)
        {
            checkedMobileReView = new HashMap<String, Integer>();
        }
        for (GgkzAuthMobileInfo item : ggkzAuthMobileInfoList)
        {
            String key = item.getAuthName();
            if (key != null && !"".equals(key))
            {
                key = key.split("-")[0];
                if (checkedMobileReView.containsKey(key))
                {
                    checkedMobileReView.put(key, checkedMobileReView.get(key) + 1);
                }
                else
                {
                    checkedMobileReView.put(key, 1);
                }
            }
        }
        for (GgkzAuthMobileInfo item : ggkzAuthMobileInfoListChecked)
        {
            String key = item.getAuthName();
            if (key != null && !"".equals(key))
            {
                key = key.split("-")[0];
                if (checkedMobileReView.containsKey(key))
                {
                    if (checkedMobileReView.get(key) > 0)
                    {
                        checkedMobileReView.put(key, checkedMobileReView.get(key) * -1);
                    }
                    checkedMobileReView.put(key, checkedMobileReView.get(key) + 1);
                }
            }
        }
        return "ggkz-role-info/edit";
    }

    /**
     * 更新角色对象
     */
    public String edit() throws Exception
    {
        GgkzRoleInfo ggkzRoleInfoOld = new GgkzRoleInfo();
        ggkzRoleInfoOld.setRoleId(entity.getRoleId());
        ggkzRoleInfoOld = ggkzRoleInfoServiceImpl.selectOneGgkzRoleInfo(ggkzRoleInfoOld);
        if (ggkzRoleInfoOld != null && ggkzRoleInfoOld.getRoleName() != null)
        {
            if (!ggkzRoleInfoOld.getRoleName().equals(entity.getRoleName()))
            {
                if (isExist(entity.getRoleName()))
                {
                    // 存在重复的
                    setWarnMessage("角色名称已经存在");
                }
            }
        }
        if (ggkzRoleInfoServiceImpl.updateRoleInfoAndAuth(entity, checkbox, mobileCheckbox) != 0)
        {// 更新角色和角色权限对象
            setMessage("修改成功");
        }
        else
        {
            setWarnMessage("修改失败");
        }
        return JSON_DATA;
    }

    /**
     * 查询角色对象跳转到查看页面
     */
    public String view() throws Exception
    {
        entity = ggkzRoleInfoServiceImpl.getRoleInfo(entity.getRoleId());
        ggkzAuthInfoListChecked = ggkzRoleInfoServiceImpl.selectAuthInfoByRoleId(entity.getRoleId());
        ggkzAuthMobileInfoListChecked = ggkzRoleInfoServiceImpl.selectAuthMobileInfoByRoleId(entity.getRoleId());
        return "ggkz-role-info/view";
    }

    /**
     * 判断角色名称是否已经存在
     * 
     * @param barnCode
     * @return boolean
     */
    public boolean isExist(String roleName)
    {
        GgkzRoleInfo ggkzRoleInfoTmp = new GgkzRoleInfo();
        ggkzRoleInfoTmp.setRoleName(roleName);
        ggkzRoleInfoTmp = ggkzRoleInfoServiceImpl.selectOneGgkzRoleInfo(ggkzRoleInfoTmp);

        if (ggkzRoleInfoTmp != null)
        {
            return true;// 存在返回true
        }
        return false;
    }

    // getter setter
    public GgkzRoleInfo getEntity()
    {
        return entity;
    }

    public void setEntity(GgkzRoleInfo entity)
    {
        this.entity = entity;
    }

    public List<GgkzAuthInfo> getGgkzAuthInfoList()
    {
        return ggkzAuthInfoList;
    }

    public void setGgkzAuthInfoList(List<GgkzAuthInfo> ggkzAuthInfoList)
    {
        this.ggkzAuthInfoList = ggkzAuthInfoList;
    }

    public List<GgkzAuthInfo> getGgkzAuthInfoListChecked()
    {
        return ggkzAuthInfoListChecked;
    }

    public void setGgkzAuthInfoListChecked(List<GgkzAuthInfo> ggkzAuthInfoListChecked)
    {
        this.ggkzAuthInfoListChecked = ggkzAuthInfoListChecked;
    }

    public GgkzRoleInfoQuery getGgkzRoleInfoQuery()
    {
        return ggkzRoleInfoQuery;
    }

    public void setGgkzRoleInfoQuery(GgkzRoleInfoQuery ggkzRoleInfoQuery)
    {
        this.ggkzRoleInfoQuery = ggkzRoleInfoQuery;
    }

    public void setGgkzRoleInfoServiceImpl(GgkzRoleInfoServiceImpl ggkzRoleInfoServiceImpl)
    {
        this.ggkzRoleInfoServiceImpl = ggkzRoleInfoServiceImpl;
    }

    public List<GgkzAuthMobileInfo> getGgkzAuthMobileInfoList()
    {
        return ggkzAuthMobileInfoList;
    }

    public void setGgkzAuthMobileInfoList(List<GgkzAuthMobileInfo> ggkzAuthMobileInfoList)
    {
        this.ggkzAuthMobileInfoList = ggkzAuthMobileInfoList;
    }

    public List<GgkzAuthMobileInfo> getGgkzAuthMobileInfoListChecked()
    {
        return ggkzAuthMobileInfoListChecked;
    }

    public void setGgkzAuthMobileInfoListChecked(List<GgkzAuthMobileInfo> ggkzAuthMobileInfoListChecked)
    {
        this.ggkzAuthMobileInfoListChecked = ggkzAuthMobileInfoListChecked;
    }


    public String[] getCheckbox()
    {
        return checkbox;
    }

    public void setCheckbox(String[] checkbox)
    {
        this.checkbox = checkbox;
    }

    public String[] getMobileCheckbox()
    {
        return mobileCheckbox;
    }

    public void setMobileCheckbox(String[] mobileCheckbox)
    {
        this.mobileCheckbox = mobileCheckbox;
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

    public Map<String, Integer> getCheckedReView()
    {
        return checkedReView;
    }

    public void setCheckedReView(Map<String, Integer> checkedReView)
    {
        this.checkedReView = checkedReView;
    }

    public Map<String, Integer> getCheckedMobileReView()
    {
        return checkedMobileReView;
    }

    public void setCheckedMobileReView(Map<String, Integer> checkedMobileReView)
    {
        this.checkedMobileReView = checkedMobileReView;
    }

}
