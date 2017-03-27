package com.is.session.context;

import javax.jms.Session;

import org.apache.mina.core.session.IoSession;

public class SessionObject
{

    private String    thatSessionId;

    private String    sessionId;

    private String    userId;

    private IoSession ioSession;

    private Session   jmssession;

    public SessionObject()
    {

    }

    public String getThatSessionId()
    {
        return thatSessionId;
    }

    public void setThatSessionId(String thatSessionId)
    {
        this.thatSessionId = thatSessionId;
    }

    public SessionObject(String sessionId, String userId, IoSession ioSession)
    {
        this.sessionId = sessionId;
        this.userId = userId;
        this.ioSession = ioSession;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public IoSession getIoSession()
    {
        return ioSession;
    }

    public void setIoSession(IoSession ioSession)
    {
        this.ioSession = ioSession;
    }

    public Session getJmssession()
    {
        return jmssession;
    }

    public void setJmssession(Session jmssession)
    {
        this.jmssession = jmssession;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if ((this.getUserId() == null || "".equals(this.getUserId().trim())) && (this.getIoSession() == null))
        {
            return false;
        }
        if (obj instanceof SessionObject)
        {
            SessionObject that = (SessionObject) obj;
            if (userId == null || "".equals(userId.trim()))
            {
                if (that.getIoSession() == null)
                {
                    return false;
                }
                else
                {
                    setThatSessionId(that.getSessionId());
                    return ioSession.getId() == that.getIoSession().getId();
                }
            }
            else
            {
                setThatSessionId(that.getSessionId());
                return this.getUserId().equals(that.getUserId());
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        int result = 17; //任意素数
        result = 31 * result + (userId == null ? 0 : userId.hashCode());
        return result;
    }
    //
    //  public int hashCode() {
    //    int result = 17;  //任意素数
    //  result = 31*result +c1; //c1,c2是什么看下文解释
    //   result = 31*result +c2;
    //    return result;
    //  } 其中c1，c2是我们生成的你要计算在内的字段的代码，生成规则如下：
    //  如果字段是boolean 计算为(f?1:0);
    // 如果字段是byte,char,short,int则计算为 (int)f;
    // 如果字段是long 计算为 (int)(f^(f>>32));
    // 如果字段是float 计算为 Float.floatToLongBits(f);
    // 如果字段是一个引用对象，那么直接调用对象的hashCode方法，如果需要判空，可以加上如果为空就返回0;
    // 如果字段是一个数组则需要遍历所有元素，按上面几种方法计算；

}