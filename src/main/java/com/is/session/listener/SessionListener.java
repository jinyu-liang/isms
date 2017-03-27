package com.is.session.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.session.context.IsSessionContext;
import com.is.session.context.SessionObject;

public class SessionListener implements HttpSessionListener
{

    private IsSessionContext isSessionContext = null;

    public SessionListener()
    {
        isSessionContext = IsSessionContext.getInstance();
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent)
    {
        if (isSessionContext != null)
        {
            UserDetail userDetail = (UserDetail) SpringSecurityUtils.getCurrentUser();
            if (userDetail != null)
            {
                SessionObject sessionObject = new SessionObject(httpSessionEvent.getSession().getId(), userDetail.getUserId(), null);
                isSessionContext.AddSession(httpSessionEvent.getSession().getId(), sessionObject);
            }
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent)
    {
        if (isSessionContext != null)
        {
            isSessionContext.DelSession(httpSessionEvent.getSession().getId(), "SessionOutTime", true);
        }
    }
}
