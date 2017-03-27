package com.is.utils.webServices;

import javax.xml.rpc.ParameterMode;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.ggkz.action.GgkzDepartInfoAction;
import com.is.ggkz.entity.GgkzDepartInfo;
import com.is.pretrst.entity.ExPersonInfoPay;


public class send extends BaseStruts2Action{
	
	Service service = new Service();
	Call call = null;
	
	 private static final long         serialVersionUID = 1L;

	    @SuppressWarnings("unused")
	 private static final Logger       LOGGER           = LoggerFactory.getLogger(GgkzDepartInfoAction.class);

	    // -- 页面属性 --//
	  private GgkzDepartInfo            entity;
	
	public String Sendtoliucheng(Page page) throws Exception {
		String result="";
		try {
			call = (Call) service.createCall();
			String URL = "";
			
			System.out.println("----------##########eomstoresources.url："+URL+"##########----------");
			call.setTargetEndpointAddress(URL);
			
			call.setOperationName("RequestResData");

			call.addParameter("TYPE", XMLType.XSD_STRING, ParameterMode.IN);
			call.addParameter("RequestData", XMLType.XSD_STRING, ParameterMode.IN);
			
			call.setReturnType(XMLType.XSD_STRING);
			
			String requestData = "";
			
			System.out.println("----------##########调用xmlrequestData："+requestData+"##########----------");
			
			result = (String) call.invoke(new Object[]{ "ER1071" , requestData });

			System.out.println("----------##########调用结果result："+result+"##########----------");
						
			System.out.println("");
			
			
		} catch (Exception e) {
			System.out.println("----------@@@@@@@@@@创建调用类Call异常：" + e.getMessage()+"@@@@@@@@@@----------");
		}
		
		return "";
	}
	
	public String getxml(Page page)
	{
		StringBuffer result= new StringBuffer();

		for (int i=0;i<page.getData().size();i++)
		{
			ExPersonInfoPay exp = (ExPersonInfoPay) page.getData().get(i);
		}
		return result.toString();
	}
}
