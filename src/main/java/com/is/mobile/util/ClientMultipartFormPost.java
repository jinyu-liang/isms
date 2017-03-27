package com.is.mobile.util;

import java.io.File;  
import java.io.IOException;  
  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.mime.MultipartEntity;  
import org.apache.http.entity.mime.content.FileBody;  
import org.apache.http.entity.mime.content.StringBody;  
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.util.EntityUtils; 

public class ClientMultipartFormPost {

	public static void main(String[] args) throws ClientProtocolException, IOException {  
        
	      HttpClient httpclient = new DefaultHttpClient();  
	          
	      try {  
	            HttpPost httppost = new HttpPost("http://localhost:8080/ISMS/mobile/mobile-purch!uploadPurchImage.action?userId=0035&reportId=purch2013100919024662012403161");  
	          
	            FileBody bin = new FileBody(new File("d:/123.txt"));  
	              
//	            StringBody comment = new StringBody("just test");  
	          
	            MultipartEntity reqEntity = new MultipartEntity();  
	             
	            reqEntity.addPart("userfile", bin);//upload为请求后台的File upload;属性  
	            FileBody bin1 = new FileBody(new File("d:/234.txt")); 
	            reqEntity.addPart("userfile", bin1);
	              
	            //reqEntity.addPart("str", comment);//str 为请求后台的String str;属性  
	          
	            httppost.setEntity(reqEntity);  
	          
	            HttpResponse response = httpclient.execute(httppost);  
	              
	            int statusCode = response.getStatusLine().getStatusCode();  
	              
	            if(statusCode == HttpStatus.SC_OK){  
	                  
	                HttpEntity resEntity = response.getEntity();  
	                  
	  
	            }  
	              
	        } finally {  
	            try {   
	                httpclient.getConnectionManager().shutdown();   
	            } catch (Exception ignore) {  
	                  
	            }  
	        }  
	    }  

}
