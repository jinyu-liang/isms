package com.is.ggkz.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.sys.entity.SysDict;

/**
 * 
 * @ClassName: GgkzAuthInfoAction
 */
@Namespace("/")
public class LoginAction extends BaseStruts2Action
{
    private static final long   serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER           = LoggerFactory.getLogger(LoginAction.class);

    private int                 error;
    private List<SysDict> userPostList = PmsDictUtil.getDictByType("post");

    public LoginAction()
    {
        super();
    }

    /**
     * 登陆页
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception
    {
        return "login";
    }

    /**
     * 默认的转向登陆页 ，后续扩展个性化登陆页可以在这个方法里
     * 
     * @return
     * @throws Exception
     */
    public String index() throws Exception
    {
        return "index";
    }

    public int getError()
    {
        return error;
    }

    public void setError(int error)
    {
        this.error = error;
    }

	public List<SysDict> getUserPostList() {
		return userPostList;
	}

	public void setUserPostList(List<SysDict> userPostList) {
		this.userPostList = userPostList;
	}

}
