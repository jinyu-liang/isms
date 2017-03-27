package com.is.ggkz.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.base.security.SpringSecurityUtils;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.is.ggkz.dao.GgkzResourceInfoDaoImpl;
import com.is.ggkz.dao.GgkzRoleAuthDaoImpl;
import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.dao.GgkzUserRoleDaoImpl;
import com.is.ggkz.entity.GgkzAuthInfo;
import com.is.ggkz.entity.GgkzResourceInfo;
import com.is.ggkz.entity.GgkzRoleInfo;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.GgkzUserRole;
import com.is.ggkz.entity.UserInfoMobile;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.mq.util.MessageTool;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.utils.DateUtils;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * @ClassName: GgkzUserInfoServiceImpl
 * @Description: GgkzUserInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:18:52 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzUserInfoServiceImpl implements UserDetailsService
{
    @SuppressWarnings("unused")
    private static Logger          logger = LoggerFactory.getLogger(GgkzUserInfoServiceImpl.class);

    public GgkzUserInfoDaoImpl     ggkzUserInfoDaoImpl;

    /** 用户和角色关联dao */
    public GgkzUserRoleDaoImpl     ggkzUserRoleDaoImpl;

    /** 角色和权限关联dao */
    public GgkzRoleAuthDaoImpl     ggkzRoleAuthDaoImpl;

    /** 菜单资源dao */
    public GgkzResourceInfoDaoImpl ggkzResourceInfoDaoImpl;

    @Autowired
    private MWorkshopDaoImpl       mWorkshopDaoImpl;

    /** 权限模板id列表 */
    public List<String>            authIdList;

    public List<String> getAuthIdList()
    {
        return authIdList;
    }

    public void setAuthIdList(List<String> authIdList)
    {
        this.authIdList = authIdList;
    }

    @Autowired
    public void setGgkzResourceInfoDaoImpl(GgkzResourceInfoDaoImpl ggkzResourceInfoDaoImpl)
    {
        this.ggkzResourceInfoDaoImpl = ggkzResourceInfoDaoImpl;
    }

    @Autowired
    public void setGgkzRoleAuthDaoImpl(GgkzRoleAuthDaoImpl ggkzRoleAuthDaoImpl)
    {
        this.ggkzRoleAuthDaoImpl = ggkzRoleAuthDaoImpl;
    }

    @Autowired
    public void setGgkzUserInfoDaoImpl(GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl)
    {
        this.ggkzUserInfoDaoImpl = ggkzUserInfoDaoImpl;
    }

    /**
     * 获取用户Detail信息的回调函数.
     */
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException
    {
        GgkzUserInfo user = new GgkzUserInfo();
        user.setLoginName(userName);
        user.setName(userName);
        user = ggkzUserInfoDaoImpl.selectLoginEntity(user);
        if (user == null)
        {
            throw new DisabledException("nouser");
        }
        else if ("N".equals(user.getUserState()))
        {
            throw new DisabledException("unable");
        }
        GrantedAuthority[] grantedAuths = obtainGrantedAuthorities(user);

        // 此处要获取该用户可以访问的资源（类型为menu），里面包括资源id，资源名称，上级资源id，资源url，资源排序
        List<GgkzResourceInfo> resource = new ArrayList<GgkzResourceInfo>();
        if (authIdList != null && authIdList.size() > 0)
        {
            // 根据权限id查找资源
            resource = ggkzResourceInfoDaoImpl.selectResourceByAuthIds(authIdList);
        }

        // 无以下属性,暂时全部设为true.
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        String password = "";
        if (StringUtils.isNotEmpty(user.getNewPassword()))
        {
            password = user.getNewPassword();
        }
        else
        {
            password = user.getPassword();
        }

        UserDetail userDetail = new UserDetail(password, user.getName(), user.getUserId(), user.getLoginName(), user.getPost(),
                DateUtils.dateTimeToString(new Date()), resource, grantedAuths, accountNonExpired, accountNonLocked, credentialsNonExpired, enabled,user.getDepartId());
        return userDetail;
    }

    /**
     * 获得用户所有角色的权限.
     */
    private GrantedAuthority[] obtainGrantedAuthorities(GgkzUserInfo user)
    {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        authIdList = new ArrayList<String>();
        for (GgkzRoleInfo role : user.getGgzkRoleBeans())
        {
            for (GgkzAuthInfo authority : role.getGgkzAuthInfos())
            {
                authSet.add(new GrantedAuthorityImpl(authority.getAuthName()));
                authIdList.add(authority.getAuthId());
            }
        }
        return authSet.toArray(new GrantedAuthority[authSet.size()]);
    }

    /**
     * 保存用户信息
     * 
     * @param user
     * @return
     */
    public int saveUserInfo(GgkzUserInfo user)
    {
        int result = ggkzUserInfoDaoImpl.insert(user);
        // 添加日志
        OperLogUtil.insertOperLog(user.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_ADD, "增加", "增加用户", "添加成功", "",
                "ggkz_user_info");
        return result;
    }

    /**
     * 修改用户
     * 
     * @param info
     * @return
     */
    public int updateUserInfo(GgkzUserInfo info)
    {
        int result = ggkzUserInfoDaoImpl.updateByEntity(info);
        // 添加日志
        OperLogUtil.insertOperLog(info.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "修改用户", "修改成功", "修改密码",
                "ggkz_user_info");
        return result;
    }

    /**
     * 重置密码
     * 
     * @param info
     * @return
     */
    public int resetPassword(GgkzUserInfo info)
    {
        int result = ggkzUserInfoDaoImpl.resetPassword(info);
        // 添加日志
        OperLogUtil.insertOperLog(info.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "重置密码", "重置成功", "重置密码",
                "ggkz_user_info");
        return result;
    }

    /**
     * 删除用户信息
     * 
     * @param info
     * @return
     */
    @TriggersRemove(cacheName = "DEFAULT_CACHE", removeAll = true)
    public String deleteUser(GgkzUserInfo info)
    {
        info = ggkzUserInfoDaoImpl.selectOneByEntity(info);
        if (info.getPost().equals("38"))
        {//濡傛灉琚垹闄ょ殑鏄尯鍩熼儴闀匡紝鍒欐妸鐢ㄦ埛鐨勫尯鍩熼儴闀夸负琚垹闄ょ殑浜虹殑鍖哄煙閮ㄩ暱涓�鏍忚缃负null
            ggkzUserInfoDaoImpl.updatePost(info.getUserId());
        }

        MWorkshopQuery shopQuery = new MWorkshopQuery();
        shopQuery.setManagerUserId(info.getUserId());
        List<MWorkshop> shopList = new ArrayList<MWorkshop>();
        shopList = mWorkshopDaoImpl.selectByEntity(shopQuery);
        if (shopList != null && shopList.size() > 0)
        {
            return "1";
        }
        else
        {
            info.setUpdateTm(new Date());
            info.setUserState("N");
            if (ggkzUserInfoDaoImpl.updateByEntity(info) == 1)
            {
                MessageTool.unsubscribConsumer(info);
                // 添加日志
                OperLogUtil.insertOperLog(info.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_DEL, "删除", "删除用户", "删除成功",
                        "删除用户", "ggkz_user_info");
            }
            return "0";
        }

    }

    /**
     * 根据查询条件获取分页列表
     * 
     * @param ggkzUserInfoQuery
     * @return
     */
    @Transactional(readOnly = true)
    public Page selectUserPage(GgkzUserInfoQuery ggkzUserInfoQuery)
    {
        return ggkzUserInfoDaoImpl.selectUserPage(ggkzUserInfoQuery);
    }

    /**
     * 根据条件查询用户列表分页（支持用职位条件过滤）
     * @param ggkzUserInfoQuery
     * @return
     */
    @Transactional(readOnly = true)
    public Page selectUserByPostFilterPage(GgkzUserInfoQuery ggkzUserInfoQuery)
    {
        return ggkzUserInfoDaoImpl.selectUserByPostFilterPage(ggkzUserInfoQuery);
    }

    /**
     * 根据查询条件条件查询单个用户信息
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public GgkzUserInfo selectUserByEntity(GgkzUserInfo entity)
    {
        return ggkzUserInfoDaoImpl.selectOneByEntity(entity);
    }

    /**
     * 检查员工编号重复问题
     * @param entity
     * @return
     */
    @Transactional(readOnly = true)
    public GgkzUserInfo selectUserCheckById(GgkzUserInfo entity)
    {
        return ggkzUserInfoDaoImpl.selectUserCheckById(entity);
    }

    /**
     * 添加人员信息，并且增加角色关联
     * 
     * @param entity
     * @param roles
     */
    @TriggersRemove(cacheName = "DEFAULT_CACHE", removeAll = true)
    public void insertUserAndRela(GgkzUserInfo entity, String[] roles)
    {
        // 查找这些角色下都可以访问哪些资源，将资源id返回，中间以逗号分隔，保存在用户表中，以便登录时快速查找资源
        // String resourceIds = resourceIdsUnderRoles(roles);
        // entity.setResourceZone(resourceIds);
        ggkzUserInfoDaoImpl.insert(entity);
        // 添加角色关联
        insertRoleRela(entity.getUserId(), roles);
        OperLogUtil.insertOperLog(entity.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_ADD, "增加", "增加用户", "添加成功", "",
                "ggkz_user_info,ggkz_user_role");
    }

    /**
     * 修改人员信息，并且修改角色关联
     * 
     * @param entity
     * @param roles
     */
    @TriggersRemove(cacheName = "DEFAULT_CACHE", removeAll = true)
    public void updateUserAndRela(GgkzUserInfo entity, String[] roles)
    {
        GgkzUserInfo info = new GgkzUserInfo();
        info.setUserId(entity.getUserId());
        info = ggkzUserInfoDaoImpl.selectOneByEntity(info);
        if (info.getPost().equals("38") && !entity.getPost().equals("38"))
        {//濡傛灉鍦ㄤ慨鏀逛箣鍓嶆槸鍖哄煙閮ㄩ暱,淇敼涔嬪悗 涓嶆槸鍖哄煙閮ㄩ暱锛屽垯鎶婄敤鎴锋墍灞炲尯鍩熼儴闀夸负褰撳墠鐢ㄦ埛鐨勭殑鍖哄煙閮ㄩ暱涓�鏍忚缃负null
            ggkzUserInfoDaoImpl.updatePost(info.getUserId());
        }
        // 查找这些角色下都可以访问哪些资源，将资源id返回，中间以逗号分隔，保存在用户表中，以便登录时快速查找资源
        // String resourceIds = resourceIdsUnderRoles(roles);
        // entity.setResourceZone(resourceIds);
        ggkzUserInfoDaoImpl.updateByEntity(entity);
        // 删除角色关联
        GgkzUserRole userRole = new GgkzUserRole();
        userRole.setUserId(entity.getUserId());
        ggkzUserRoleDaoImpl.deleteByEntity(userRole);
        // 添加角色关联
        insertRoleRela(entity.getUserId(), roles);
        OperLogUtil.insertOperLog(entity.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "修改用户", "修改成功", "",
                "ggkz_user_info,ggkz_user_role");
    }

    /**
     * 查找用户所拥有的角色可以访问的菜单的url，类型是menu
     * 
     * @param roles
     * @return
     */
    @Transactional(readOnly = true)
    public String resourceIdsUnderRoles(String[] roles)
    {
        String resourceIds = "";
        if (roles == null || roles.length < 1)
        {
            return resourceIds;
        }
        // 获取角色下的所有权限
        List<String> roleIds = new ArrayList<String>();
        for (int i = 0; i < roles.length; i++)
        {
            if (StringUtils.isNotEmpty(roles[i]))
            {
                roleIds.add(roles[i]);
            }
        }
        List<String> authIds = ggkzRoleAuthDaoImpl.selectAuthIdsByRoles(roleIds);
        if (authIds == null || authIds.size() < 1)
        {
            return resourceIds;
        }
        // 获取权限下的资源
        List<String> resourceIdList = ggkzResourceInfoDaoImpl.selectResourceIdsByAuthIds(authIds);
        if (resourceIdList == null || resourceIdList.size() < 1)
        {
            return resourceIds;
        }
        for (int i = 0; i < resourceIdList.size(); i++)
        {
            if (StringUtils.isNotEmpty(resourceIdList.get(i)))
            {
                resourceIds += resourceIdList.get(i) + ",";
            }
        }
        return resourceIds;
    }

    /**
     * 添加用户和角色关联关系
     * 
     * @param userId
     * @param roles
     */
    private void insertRoleRela(String userId, String[] roles)
    {
        if (roles == null || roles.length < 1 || StringUtils.isEmpty(userId))
        {
            return;
        }
        // 关联用户和角色关系
        List<GgkzUserRole> list = new ArrayList<GgkzUserRole>();
        for (int i = 0; i < roles.length; i++)
        {
            if (StringUtils.isEmpty(roles[i]))
            {
                continue;
            }
            GgkzUserRole role = new GgkzUserRole();
            role.setUserId(userId);
            role.setRoleId(roles[i]);
            role.setOperUserId(SpringSecurityUtils.getCurrentUser().getUserId());
            list.add(role);
        }
        ggkzUserRoleDaoImpl.batchInsertUserRole(list);
    }

    @Autowired
    public void setGgkzUserRoleDaoImpl(GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl)
    {
        this.ggkzUserRoleDaoImpl = ggkzUserRoleDaoImpl;
    }

    /**
     * 根据用户id查找用户
     * 
     * @param userIds
     * @return
     */
    @Transactional(readOnly = true)
    public List<GgkzUserInfo> selectUsersByIds(String userIds)
    {
        List<GgkzUserInfo> userList = new ArrayList<GgkzUserInfo>();
        if (StringUtils.isNotEmpty(userIds))
        {
            String[] ids = userIds.split(",");
            List<String> userIdList = new ArrayList<String>();
            for (int i = 0; i < ids.length; i++)
            {
                if (StringUtils.isNotEmpty(ids[0]))
                {
                    userIdList.add(ids[i]);
                }
            }
            userList = ggkzUserInfoDaoImpl.selectUsersByIds(userIdList);
        }
        return userList;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheName = "DEFAULT_CACHE")
    public List<GgkzUserInfo> selectUsers()
    {
        return ggkzUserInfoDaoImpl.selectUsers();
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheName = "DEFAULT_CACHE")
    public List<UserInfoMobile> selectMoblieUsers(GgkzUserInfoQuery ggkzUserInfoQuery)
    {
        return ggkzUserInfoDaoImpl.selectMobileUsers(ggkzUserInfoQuery);
    }

    /**
     * 禁用用户
     * 
     * @param entity
     * @return
     */
    public int unableUser(GgkzUserInfo entity)
    {
        entity.setUserState("N");
        int result = ggkzUserInfoDaoImpl.updateByEntity(entity);
        OperLogUtil.insertOperLog(entity.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_UPDATE, "禁用", "禁用用户", "禁用成功", "",
                "ggkz_user_info");
        return result;
    }

    /**
     * 启用用户
     * 
     * @param entity
     * @return
     */
    public int enableUser(GgkzUserInfo entity)
    {
        entity.setUserState("Y");
        int result = ggkzUserInfoDaoImpl.updateByEntity(entity);
        OperLogUtil.insertOperLog(entity.getUserId(), PublicDict.MODEL_GGKZ, "公共控制", PublicDict.OPER_LOG_EVENT_UPDATE, "启用", "启用用户", "启用成功", "",
                "ggkz_user_info");
        return result;
    }

    /**
     * 根据查询条件查找多个用户
     * 
     * @param entity
     * @return
     */
    public List<GgkzUserInfo> selectByEnity(GgkzUserInfoQuery queryEntity)
    {
        return ggkzUserInfoDaoImpl.selectByEntity(queryEntity);
    }

    /**
     * 根据查询条件查找单个用户
     * @param queryEntity
     * @return
     */
    public GgkzUserInfo selectOneByEntity(GgkzUserInfoQuery queryEntity)
    {
        return ggkzUserInfoDaoImpl.selectOneByEntity(queryEntity);
    }

    /**
     * 查询部长或者总经理的数据
     * 
     * @param entity
     * @return
     */
    public List<GgkzUserInfo> getDepartOrManager(String userId)
    {
        GgkzUserInfoQuery query = new GgkzUserInfoQuery();
        query.setPost("1");
        query.setUserId(userId);
        List<GgkzUserInfo> newList = new ArrayList<GgkzUserInfo>();
        newList.addAll(ggkzUserInfoDaoImpl.selectByEntityNotSelf(query));

        query = new GgkzUserInfoQuery();
        query.setPost("3");
        query.setUserId(userId);
        newList.addAll(ggkzUserInfoDaoImpl.selectByEntityNotSelf(query));
        query = new GgkzUserInfoQuery();
        query.setPost("38");
        query.setUserId(userId);
        newList.addAll(ggkzUserInfoDaoImpl.selectByEntityNotSelf(query));

        return newList;
    }

}
