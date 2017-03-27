package com.is.ggkz.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;

/**
 * 
 * @ClassName: GgkzAuthInfoAction
 */
@Namespace("/")
public class MainIndexAction extends BaseStruts2Action
{
    private static final long   serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER           = LoggerFactory.getLogger(MainIndexAction.class);

    public MainIndexAction()
    {
        super();
    }

    /**
     * 主页
     * @return
     * @throws Exception
     */
    @Override
    public String execute() throws Exception
    {
        return "main";
    }

}
