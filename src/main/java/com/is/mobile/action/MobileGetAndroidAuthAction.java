package com.is.mobile.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.RequestAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.mobile.service.MobileGetAndroidAuthServiceImpl;
import com.is.mobile.service.MobileWorkShopServiceImpl;
import com.is.pretrst.entity.RstUploadConf;
import com.is.pretrst.entity.RstVerInfo;
import com.is.pretrst.service.RstUploadConfServiceImpl;
import com.is.pretrst.service.RstVerInfoServiceImpl;
import com.is.utils.StringUtils;
import com.is.utils.authUtil.MobileAuth;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileGetAndroidAuthAction extends BaseStruts2Action implements RequestAware
{
    private static final long               serialVersionUID = 1L;

    private static final Logger             LOGGER           = LoggerFactory.getLogger(MobileSendMetralAction.class);

    private String                          userId;

    private String                          post;

    private String                          loginName;

    private String                          password;

    private String                          newPassword;

    private String                          mobileSns;

    private String                          sessionId;

    private String                          mobileId;

    @Autowired
    private MobileWorkShopServiceImpl       mobileWorkShopServiceImpl;

    @Autowired
    private MobileGetAndroidAuthServiceImpl authServiceImpl;

    @Autowired
    private GgkzUserInfoServiceImpl         ggkzUserInfoServiceImpl;

    @Autowired
    private MobileGetAndroidAuthServiceImpl androidAuthServiceImpl;

    @Autowired
    private RstUploadConfServiceImpl        uploadConfServiceImpl;

    private String                          verCode;

    @Autowired
    private RstVerInfoServiceImpl           rstVerInfoServiceImpl;

    /**安卓版本更新接口
     * @param verCode 传入当前安卓手机端版本号
     * 
     * return 返回 flag,data
     * flag:
     * 1_版本有效，提示更新，如果不更新仍可以运行，是旧的版本
     * 0_提示版本无效，系统不能登录，需要更新版本后方可使用系统
     * Y_已经是最新版本，无需更新
     * N_当前客户端的版本号在服务器没有登记
     * data：最新版本信息
     */
    public void getAndroidAuth()
    {
        Map<String, String> rstList = new HashMap<String, String>();
        RstVerInfo verInfo = new RstVerInfo();
        String userName = "";
        String verFlag = "";
        String flag = "0";
        String imgSize = "";
        try
        { //获取最新的版本信息
            verInfo = rstVerInfoServiceImpl.getMaxVerCode();
            LOGGER.debug("传入的版本号为【{}】", verCode);
            verFlag = rstVerInfoServiceImpl.getVerValidFlag(verCode);
        }
        catch (Exception e1)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        //        this.jsonUtil1( verInfo,verFlag);
        if (verFlag.equals("1") || verFlag.equals("Y"))
        {

            ///////////////////////登录校验开始

            try
            {
                flag = androidAuthServiceImpl.getLoginFlag(loginName, password, mobileSns, mobileId);
                LOGGER.debug("mobileId===[{}],mobileSn[{}]", mobileId, mobileSns);
                if (flag.equals("3"))
                {
                    // password,mobileSn
                    userId = androidAuthServiceImpl.getUserIdByAndroid(loginName);
                    userName = androidAuthServiceImpl.getUserNameByAndroid(loginName);
                    GgkzUserInfo ggkzUserInfo=androidAuthServiceImpl.getUserByAndroid(loginName);
                    post=ggkzUserInfo.getPost();
                    List<String> roles = authServiceImpl.getRoles(userId);
                    rstList = MobileAuth.mobileAuthResourceByRoles(roles);
                }
            }
            catch (Exception e1)
            {
                flag = "1";
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("getSendMetralApproval异常：{}", e1);
                }
            }

            RstUploadConf conf = uploadConfServiceImpl.selectPicParam();
            if (conf != null && conf.getSizeConf() != 0)
            {
                imgSize = String.valueOf(conf.getSizeConf());
            }
            else
            {
                imgSize = "300";
            }

            ///////////////////////登录校验结束

        }
        this.jsonUtil(rstList, flag, userId,post, imgSize, verInfo, verFlag, userName);
    }
    /**
     * 根据mobileId和mobileSn判断设备是否可用
     */
    public void getMobileServerFlag()
    {
    	String mobileFlag="0";
    	try
    	{ 
    		mobileFlag = androidAuthServiceImpl.getMobileServerFlag(mobileSns, mobileId);
    	}
    	catch (Exception e1)
    	{
    		if (LOGGER.isErrorEnabled())
    		{
    			LOGGER.error("getSendMetralApproval异常：{}", e1);
    		}
    	}
    	this.updatejsonUtil(mobileFlag);
    }
    /**
     * 修改密码接口
     * 传入：
     * userId,
     * password,原密码
     * newPassowrd新密码
     * 返回flag:
     * Y,密码修改成功
     * N,修改失败
     * 0,原密码不正确
     * 
     */
    public void updatePassword()
    {
        String flag = "";
        //        String userId = sessionUser.getUserId();
        // 判断原密码是否正确
        GgkzUserInfo tmp = new GgkzUserInfo();
        tmp.setUserId(userId);
        tmp = ggkzUserInfoServiceImpl.selectUserByEntity(tmp);
        if (tmp == null)
        {
            //            setWarnMessage("用户不存在");
        }
        if (StringUtils.isEmpty(tmp.getNewPassword()))
        {
            if (!tmp.getPassword().equals(getPassword()))
            {
                //                setWarnMessage("原密码不正确");
                flag = "0";
            }
        }
        else if (!tmp.getNewPassword().equals(getPassword()))
        {
            //            setWarnMessage("原密码不正确");
            flag = "0";
        }
        if (!flag.equals("0"))
        {
            GgkzUserInfo info = new GgkzUserInfo();
            info.setUserId(userId);
            info.setNewPassword(getNewPassword());
            int i = ggkzUserInfoServiceImpl.updateUserInfo(info);
            //        setMessage("修改成功");
            if (i != 0)
            {
                flag = "Y";
            }
            else
            {
                flag = "N";
            }
        }
        this.updatejsonUtil(flag);

    }

    /**
     * 本类中共用的封装json字符串并返回给前台
     * 
     * @param rstList
     * @param flag
     */
    public void jsonUtil(Map<String, String> rstList, String flag, String userId, String post, String imgSize, RstVerInfo verInfo, String verFlag,
            String userName)
    {
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("userId", userId);
        secrityMap.put("post", post);
        secrityMap.put("userName", userName);
        secrityMap.put("data", rstList);
        if (!verFlag.equals("Y"))
        {
            secrityMap.put("verData", verInfo);
        }
        secrityMap.put("verFlag", verFlag);
        secrityMap.put("sessionId", sessionId);
        //        secrityMap.put("imgSize", imgSize);
        JSONObject jsonobj = new JSONObject();

        // json配置
        JsonConfig jsonConfig = new JsonConfig();
        // json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        // 注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        // 排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct"};
        // 注册排除字段
        jsonConfig.setExcludes(excludes);

        // 加载集合
        jsonobj.accumulateAll(secrityMap, jsonConfig);

        PrintWriter out = null;
        try
        {
            out = ServletActionContext.getResponse().getWriter();
            out.write(jsonobj.toString());
            out.flush();
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("login异常：{}", e);
            }
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    /**
     * 本类中共用的封装json字符串并返回给前台
     * 
     * @param rstList
     * @param flag
     */
    public void updatejsonUtil(String flag)
    {
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        JSONObject jsonobj = new JSONObject();
        // json配置
        JsonConfig jsonConfig = new JsonConfig();
        // json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        // 注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        // 排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct"};
        // 注册排除字段
        jsonConfig.setExcludes(excludes);

        // 加载集合
        jsonobj.accumulateAll(secrityMap, jsonConfig);

        PrintWriter out = null;
        try
        {
            out = ServletActionContext.getResponse().getWriter();
            out.write(jsonobj.toString());
            out.flush();
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("login异常：{}", e);
            }
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    //    public String getMobileSn()
    //    {
    //        return mobileSn;
    //    }
    //
    //    public void setMobileSn(String mobileSn)
    //    {
    //        this.mobileSn = mobileSn;
    //    }

    public GgkzUserInfoServiceImpl getGgkzUserInfoServiceImpl()
    {
        return ggkzUserInfoServiceImpl;
    }

    public String getMobileSns()
    {
        return mobileSns;
    }

    public void setMobileSns(String mobileSns)
    {
        this.mobileSns = mobileSns;
    }

    public void setGgkzUserInfoServiceImpl(GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl)
    {
        this.ggkzUserInfoServiceImpl = ggkzUserInfoServiceImpl;
    }

    public MobileGetAndroidAuthServiceImpl getAuthServiceImpl()
    {
        return authServiceImpl;
    }

    public void setAuthServiceImpl(MobileGetAndroidAuthServiceImpl authServiceImpl)
    {
        this.authServiceImpl = authServiceImpl;
    }

    /**
     * 
     * @return
     */
    public void ApprovalDispose()
    {

    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public void setMobileWorkShopServiceImpl(MobileWorkShopServiceImpl mobileWorkShopServiceImpl)
    {
        this.mobileWorkShopServiceImpl = mobileWorkShopServiceImpl;
    }

    public MobileWorkShopServiceImpl getMobileWorkShopServiceImpl()
    {
        return mobileWorkShopServiceImpl;
    }

    public void setMobileId(String mobileId)
    {
        this.mobileId = mobileId;
    }

    public String getMobileId()
    {
        return mobileId;
    }

    public String getLoginName()
    {
        return loginName;
    }

    public void setLoginName(String loginName)
    {
        this.loginName = loginName;
    }

    @Override
    public void setRequest(Map<String, Object> arg0)
    {
    }

    public void setUploadConfServiceImpl(RstUploadConfServiceImpl uploadConfServiceImpl)
    {
        this.uploadConfServiceImpl = uploadConfServiceImpl;
    }

    public String getVerCode()
    {
        return verCode;
    }

    public void setVerCode(String verCode)
    {
        this.verCode = verCode;
    }

    public RstVerInfoServiceImpl getRstVerInfoServiceImpl()
    {
        return rstVerInfoServiceImpl;
    }

    public void setRstVerInfoServiceImpl(RstVerInfoServiceImpl rstVerInfoServiceImpl)
    {
        this.rstVerInfoServiceImpl = rstVerInfoServiceImpl;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword(String newPassword)
    {
        this.newPassword = newPassword;
    }

}
