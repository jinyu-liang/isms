package com.is.mobile.util;

import java.util.ArrayList;
import java.util.List;

import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.utils.StringUtils;


public class WsUtil
{   
    
    /**
     * 根据用户id得到职务id
     * @param userId
     * @return
     */
    public static String getPostByUserId(String userId){
        GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl = (GgkzUserInfoServiceImpl) SpringContextHolder
        .getBean("ggkzUserInfoServiceImpl");
        GgkzUserInfo entity = new GgkzUserInfo();
        entity.setUserId(userId);
        entity = ggkzUserInfoServiceImpl.selectUserByEntity(entity);
        if(entity!=null&&!StringUtils.isEmpty(entity.getPost())){
            return entity.getPost();
        }
        return "";
    }
    
    /**
     * 根据用户id得到职务id
     * @param userId
     * @return
     */
    public static String getPostByDivcd(String userId){
        GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl = (GgkzUserInfoServiceImpl) SpringContextHolder
        .getBean("ggkzUserInfoServiceImpl");
        GgkzUserInfo entity = new GgkzUserInfo();
        entity.setUserId(userId);
        entity = ggkzUserInfoServiceImpl.selectUserByEntity(entity);
        if(entity!=null&&!StringUtils.isEmpty(entity.getPost())){
            return entity.getDepartId();
        }
        return "";
    }
    /**
     * 根据区域部长id获得下属项目经理的userId
     * @param eareaheadUserId 区域部长id
     * @return
     */
    public static List<String> getSiteUserIdByEareaheadUserId(String eareaheadUserId){
        List<String> strList = new ArrayList<String>();
        GgkzUserInfoServiceImpl stbdWineInfoServiceImpl = (GgkzUserInfoServiceImpl) SpringContextHolder
        .getBean("ggkzUserInfoServiceImpl");
        GgkzUserInfoQuery queryInfo = new GgkzUserInfoQuery();
        queryInfo.setHeadUserCd(eareaheadUserId);
       List<GgkzUserInfo> infoList =  stbdWineInfoServiceImpl.selectByEnity(queryInfo);
       for(GgkzUserInfo info:infoList){
           strList.add(info.getUserId());
       }
       return strList;
    }
    
    public static void main(String[] args)
    {

        
    }
}
