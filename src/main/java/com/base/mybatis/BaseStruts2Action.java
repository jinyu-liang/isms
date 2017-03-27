package com.base.mybatis;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.base.security.SpringSecurityUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.is.ggkz.entity.ext.UserDetail;

/**
 * @ClassName: BaseStruts2Action
 * @Description:基于Struts2的基础Action类
 * @author
 * @date 2010-8-7
 * 
 */
public class BaseStruts2Action extends ActionSupport implements SessionAware, ServletResponseAware
{

    private static final long     serialVersionUID = 1L;

    protected Map<String, Object> session;

    protected UserDetail          sessionUser;

    public BaseStruts2Action()
    {
        sessionUser = (UserDetail) SpringSecurityUtils.getCurrentUser();
    }

    /** 回调刷新页面的页面id变量 */
    private String callbackId;

    /** 页面id */
    private String _;

    /**
     * 提醒信息
     */
    private String message;

    private String infoMessage;

    private String warnMessage;

    public String getWarnMessage()
    {
        return warnMessage;
    }

    public void setWarnMessage(String warnMessage)
    {
        this.warnMessage = warnMessage;
    }

    public String getCallbackId()
    {
        return callbackId;
    }

    public void setCallbackId(String callbackId)
    {
        this.callbackId = callbackId;
    }

    /**
     * 定义转向页面-查看页面
     */
    protected static final String VIEW      = "view";

    /**
     * 定义转向页面-新增页面
     */
    protected static final String CREATE    = "create";

    /**
     * 定义转向页面-修改页面
     */
    protected static final String EDIT      = "edit";

    /**
     * 定义转向页面-查询列表页面
     */
    protected static final String LIST      = "list";

    /** 操作成功转向页面 */
    protected static final String SUCCESS   = "success";

    protected static final String MULTI     = "multi";

    /**
     * 返回流数据
     */
    protected static final String STREAM    = "stream";

    /**
     * 定义返回json格式数据，无页面
     */
    protected static final String JSON_DATA = "jsondata";

    /**
     * 定义分页数据对象
     */
    protected Page                page      = new Page();

    /**
     * 获取分页数据对象
     * 
     * @return
     */
    public Page getPage()
    {
        return page;
    }

    /**
     * 设置分页数据对象
     * 
     * @param Page
     */
    public void setPage(Page page)
    {
        this.page = page;
    }

    public void setSession(Map<String, Object> session)
    {
        this.session = session;
    }

    public UserDetail getSessionUser()
    {
        return sessionUser;
    }

    public void setSessionUser(UserDetail sessionUser)
    {
        this.sessionUser = sessionUser;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getInfoMessage()
    {
        //        if (StringUtils.isNotEmpty(warnMessage))
        //        {
        //            return "<div id=\"system_msg\" class=\"warn\">" + warnMessage + "</div>";
        //        }
        //        else if (StringUtils.isNotEmpty(infoMessage))
        //        {
        //            return "<div id=\"system_msg\" class=\"info\">" + infoMessage + "</div>";
        //        }
        return infoMessage;
    }

    public void setInfoMessage(String infoMessage)
    {
        this.infoMessage = infoMessage;
    }

    public String get_()
    {
        return _;
    }

    public void set_(String _)
    {
        this._ = _;
    }

    /**
     * 其他的都比较好理解，主要是关于Cache-control和Pragma的设置让人比较迷惑。
     * 关于Cache-Control的must-revalidate：强制页面不缓存，作用与no-cache相同，但更严格，强制意味更明显。详细作用请参考：http://hi.baidu.com/chenleibupt/blog/item/9627bec6932e5a179c163df2.html
     * 关于post-check和pre-check：Internet Explorer 5对于HTTP头信息使用两种新的时间间隔指示：pre-check 和post-check。pre-check扩展名定义了这样一段时间间隔（以秒记）：即在这段时间间隔之后，一个对象在显示给用户之前应被选中进行更新。选中对象也可以发生在该对象已经显示给用户之后，但是，要保证在用户下次想要看这个对象时，被高速缓存起来的副本是更新过的。post-check扩展名定义了这样一段时间间隔（以秒记）：即在这段时间之后，在显示给用户之前，该对象被选中进行更新。即post-check=0,pre-check=0是IE5.0才有的防cache声明。（参考自http://bbs.chinaunix.net/thread-704320-1-1.html和http://blog.sina.com.cn/s/blog_5595d51401000b23.html）
     * 关于Pragma:no-cache，跟Cache-Control: no-cache相同。Pragma: no-cache兼容http 1.0 ，Cache-Control: no-cache是http 1.1提供的。因此，Pragma: no-cache可以应用到http 1.0 和http 1.1，而Cache-Control: no-cache只能应用于http 1.1.
     * 关于Pragma:public 作用未知，还请阅读本篇文章的各位大侠给予解释。
     */
    @Override
    public void setServletResponse(HttpServletResponse response)
    {
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0, post-check=0, pre-check=0");
    }

}