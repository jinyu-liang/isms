package com.is.session.listener;

import javax.servlet.http.HttpSessionEvent;

import org.springframework.security.web.session.HttpSessionEventPublisher;

import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.session.context.IsSessionContext;
import com.is.session.context.SessionObject;

public class IsSessionListener extends HttpSessionEventPublisher
{
    private IsSessionContext isSessionContext = IsSessionContext.getInstance();

    @Override
    public void sessionCreated(HttpSessionEvent event)
    {
        super.sessionCreated(event);
        if (isSessionContext != null)
        {
            UserDetail userDetail = (UserDetail) SpringSecurityUtils.getCurrentUser();
            if (userDetail != null)
            {
                SessionObject sessionObject = new SessionObject(event.getSession().getId(), userDetail.getUserId(), null);
                isSessionContext.AddSession(event.getSession().getId(), sessionObject);
            }
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event)
    {
        super.sessionDestroyed(event);
        if (isSessionContext != null)
        {
            isSessionContext.DelSession(event.getSession().getId(), "SessionOutTime", true);
        }
    }

}
