package com.is.session.reg;

import java.util.List;

import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistryImpl;

import com.is.ggkz.entity.ext.UserDetail;
import com.is.session.context.IsSessionContext;
import com.is.session.context.SessionObject;

public class IsSessionRegistry extends SessionRegistryImpl
{

    private IsSessionContext isSessionContext = IsSessionContext.getInstance();

    @Override
    public List<Object> getAllPrincipals()
    {
        return super.getAllPrincipals();
    }

    @Override
    public List<SessionInformation> getAllSessions(Object arg0, boolean arg1)
    {
        return super.getAllSessions(arg0, arg1);
    }

    @Override
    public SessionInformation getSessionInformation(String sessionId)
    {
        return super.getSessionInformation(sessionId);
    }

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
        super.onApplicationEvent(event);
    }

    @Override
    public void refreshLastRequest(String sessionId)
    {
        super.refreshLastRequest(sessionId);
    }

    @Override
    public void registerNewSession(String sessionId, Object principal)
    {
        super.registerNewSession(sessionId, principal);
        if (isSessionContext != null)
        {
            if (principal != null && principal instanceof UserDetail)
            {
                UserDetail userDetail = (UserDetail) principal;
                SessionObject sessionObject = new SessionObject(sessionId, userDetail.getUserId(), null);
                isSessionContext.AddSession(sessionId, sessionObject);
            }
        }
    }

    @Override
    public void removeSessionInformation(String sessionId)
    {
        super.removeSessionInformation(sessionId);
        if (isSessionContext != null)
        {
            isSessionContext.DelSession(sessionId, "SessionOutTime", true);
        }
    }

}
