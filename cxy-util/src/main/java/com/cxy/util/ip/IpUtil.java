package com.cxy.util.ip;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.cxy.bean.SecretConstant;

public class IpUtil {
	
	public static Map<String, Object> getPhoneInfo(String ip){
		Map<String, Object> jo = new HashMap<String, Object>(0);
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(SecretConstant.IpUrl + "?phone=" + ip + "&key=" + SecretConstant.IpKey);
		ResponseHandler<JSONObject> responseHandler = new ResponseHandler<JSONObject>(){
			//成功调用连接后，对返回数据进行的操作  
            public JSONObject handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {  
                int status = response.getStatusLine().getStatusCode();  
                if (status >= 200 && status < 300) {
                 //获得调用成功后  返回的数据  
                    HttpEntity entity = response.getEntity();  
                    if(null!=entity){  
                     String result= EntityUtils.toString(entity);  
                        //根据字符串生成JSON对象  
                         JSONObject resultObj = JSONObject.fromObject(result);  
                         return resultObj;  
                    }else{  
                     return null;  
                    }  
                } else {  
                    throw new ClientProtocolException("Unexpected response status: " + status);  
                }  
            }
		};
		try {
			JSONObject responseBody = httpclient.execute(httpGet, responseHandler);
			jo.put("rs", "1");
			jo.put("result", responseBody);
		} catch (Exception e) {
			jo.put("rs", "-1");
		}
		return jo;
	}

}
