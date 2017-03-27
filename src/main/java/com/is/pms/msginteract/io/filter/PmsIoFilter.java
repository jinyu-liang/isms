package com.is.pms.msginteract.io.filter;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.IoFilterAdapter;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.core.write.DefaultWriteRequest;
import org.apache.mina.core.write.WriteRequest;

import com.is.pms.msginteract.io.msg.PmsRecevieMessage;
import com.is.pms.msginteract.io.msg.PmsWriteMessage;
import com.is.pms.msginteract.io.policy.PolicyFileLoader;

public class PmsIoFilter extends IoFilterAdapter
{

    @Override
    public void messageReceived(NextFilter nextFilter, IoSession session, Object message) throws Exception
    {

        IoBuffer buffer = (IoBuffer) message;
        String s = new String(buffer.array(), Charset.forName("utf-8")).trim();
        if (!s.equals(PolicyFileLoader.POLICY_FILE_REQUEST_HEADER))
        {
            super.messageReceived(nextFilter, session, new PmsRecevieMessage(s));
        }
        else
        {
            session.write(IoBuffer.wrap(PolicyFileLoader.getPolicyFileContent().getBytes()));
        }
    }

    @Override
    public void filterWrite(NextFilter nextFilter, IoSession session, WriteRequest writeRequest) throws Exception
    {

        if (writeRequest.getMessage() instanceof PmsWriteMessage)
        {
            super.filterWrite(
                    nextFilter,
                    session,
                    new DefaultWriteRequest(IoBuffer.wrap(((PmsWriteMessage) writeRequest.getMessage()).transToJson().getBytes(
                            Charset.forName("utf-8")))));
        }
        else if (writeRequest.getMessage() instanceof IoBuffer)
        {
            super.filterWrite(nextFilter, session, writeRequest);
        }
    }
}
