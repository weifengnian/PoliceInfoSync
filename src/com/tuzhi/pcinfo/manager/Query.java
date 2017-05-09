package com.tuzhi.pcinfo.manager;

import java.util.HashMap;
import java.util.Map;

import net.arnx.jsonic.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tuzhi.pcinfo.util.HttpClientUtil;
import com.tuzhi.pcinfo.util.StringUtil;
import com.tuzhi.pcinfo.util.TransUtil;

public class Query {
	
	public static int cnt = 0;
	
	public static void main(String[] args) {
		//组织构架协议（organization）
        Map<String,String> map = new HashMap<String,String>();
        map.put("userid", "1");
        map.put("stamp", "rrr"); 
        map.put("topdeptid", ""); 
        String json = JSON.encode(map);
        String rltOrganizationStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", json, TransUtil.ENCODING);
        //组织
       organization(rltOrganizationStr);
	}
	
	//组织
	public static Map<String, String> organization(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject  = JSONObject.fromObject(jsonStr);
		String status = jsonObject.getString("status");
		String result = jsonObject.getString("result");
		if("0".equals(status) && result.length()>2){
			System.out.println(cnt++);
			JSONArray arr = JSONArray.fromObject(result);
			for (int i = 0; i < arr.size(); i++) {
				JSONObject oj1 = arr.getJSONObject(i);
				//删除，添加组织
//				Map<String, String> map1 = new HashMap<String, String>();
				StringUtil.pcOrganization(map,oj1);
				String json = JSON.encode(map);
		        String rltOrganizationStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", json, TransUtil.ENCODING);
		        organization(rltOrganizationStr);
			}
		}
		return map;
	}

}
