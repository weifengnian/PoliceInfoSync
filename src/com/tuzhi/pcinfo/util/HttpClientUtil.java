package com.tuzhi.pcinfo.util;

import java.nio.charset.Charset;
import java.util.ArrayList;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.HttpClient;  
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils; 


/**
 * @Description: 请求的工具类 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年3月23日	
 * @Copyright:
 */
public class HttpClientUtil {  
	
	private static Log log = LogFactory.getLog(HttpClientUtil.class);
	
    @SuppressWarnings("rawtypes")
	public static String doPost(String url,Map<String,String> map,String charset){ 
    	
        HttpClient httpClient = null;  
        HttpPost httpPost = null;  
        String result = null;  
        try{  
        	
            httpClient = new HttpSSLClient(); 
            //请求超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000); 
            //读取超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
            httpPost = new HttpPost(url);  
            
            //设置参数  
            List<NameValuePair> list = new ArrayList<NameValuePair>();  
            Iterator iterator = map.entrySet().iterator();  
            while(iterator.hasNext()){  
                @SuppressWarnings("unchecked")
				Entry<String,String> elem = (Entry<String, String>) iterator.next();  
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));  
            }  
            if(list.size() > 0){  
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);  
                httpPost.setEntity(entity);  
            }  
            HttpResponse response = httpClient.execute(httpPost);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        }catch(Exception ex){  
        	log.info("--doPost--Exception:"+ex.getMessage());
            ex.printStackTrace();  
        }  
        return result;  
    }  
    
    /** 
     * 发送get请求 
     * @param url       链接地址 
     * @param charset   字符编码，若为null则默认utf-8 
     * @return 
     */  
    public static String doGet(String url,String charset){  
        if(null == charset){  
            charset = "utf-8";  
        }  
        HttpClient httpClient = null;  
        HttpGet httpGet= null;  
        String result = null;  
          
        try {  
          //  httpClient = new DefaultHttpClient();  
        	httpClient = new HttpSSLClient();  
        	//请求超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000); 
            //读取超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
            httpGet = new HttpGet(url);  
            
            HttpResponse response = httpClient.execute(httpGet);  
            if(response != null){  
                HttpEntity resEntity = response.getEntity();  
                if(resEntity != null){  
                    result = EntityUtils.toString(resEntity,charset);  
                }  
            }  
        } catch (Exception e) {
        	log.info("--doGet--Exception:"+e.getMessage());
            e.printStackTrace();  
        }  
          
        return result;  
    } 
    
    
   /**
    * Post请求字符集GBK，
    * @param url
    * @param jsonStr
    * @param Url
    * @param Key
    * @return
    */
   	public static String doPostJson(String url,String jsonStr,String Url,String Key){ 
       	
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {

			httpClient = new HttpSSLClient();
			// 请求超时
			httpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
			// 读取超时
			httpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 30000);
			httpPost = new HttpPost(url);
			httpPost.addHeader("Content-type", "application/json; charset=GBK");
			httpPost.setHeader("Accept", "application/json");
			httpPost.setEntity(new StringEntity(jsonStr, Charset.forName("GBK")));
			httpPost.setEntity(new StringEntity(Url, Charset.forName("GBK")));
			httpPost.setEntity(new StringEntity(Key, Charset.forName("GBK")));

			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity,
							Charset.forName("GBK"));
					log.info("--result--doPostJson:" + result);
				}
			}
		} catch (Exception ex) {
			log.info("--doPostJson--Exception:" + ex.getMessage());
			ex.printStackTrace();
		}
		return result;
	}
   	
   	
   	/**
     * Post请求字符集
     * @param url
     * @param jsonStr
     * @param Url
     * @param Key
     * @return
     */
    	public static String jsonDoPost(String url,String jsonStr,String charset){ 
        	
 		HttpClient httpClient = null;
 		HttpPost httpPost = null;
 		String result = null;
 		try {
 			
 			httpClient = new HttpSSLClient();
 			// 请求超时
 			httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
 			// 读取超时
 			httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
 			httpPost = new HttpPost(url);
 			httpPost.addHeader("Content-type","application/json; charset=UTF-8");  
            httpPost.setHeader("Accept", "application/json");
 			
 			httpPost.setEntity(new StringEntity(jsonStr, Charset.forName(charset)));

 			HttpResponse response = httpClient.execute(httpPost);
 			if (response != null) {
 				HttpEntity resEntity = response.getEntity();
 				if (resEntity != null) {
 					result = EntityUtils.toString(resEntity,
 						Charset.forName("UTF-8"));
 				}
 			}
 		} catch (Exception ex) {
 			log.info("--doPostJson--Exception:" + ex.getMessage());
 			ex.printStackTrace();
 		}
 		return result;
 	}
   
	
}  
