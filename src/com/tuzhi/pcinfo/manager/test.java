package com.tuzhi.pcinfo.manager;

import java.util.HashMap;
import java.util.Map;

import net.arnx.jsonic.JSON;
import net.sf.json.JSONObject;

import com.tuzhi.pcinfo.util.HttpClientUtil;
import com.tuzhi.pcinfo.util.TransUtil;

public class test {
	
	public static void main(String[] args) {
		//Register
//		Map<String,String> map = new HashMap<String,String>();
//		map.put("userid", "1");
//		map.put("app_id", "1"); //{"result":{"stamp":"rrr","app_name":"app1"},"status":0}
//		String json = JSON.encode(map);
//		String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"Register", json, TransUtil.ENCODING);
//        System.out.println(resultJson);
        
        //organization
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("userid", "1");
//        map.put("stamp", "rrr"); 
//        map.put("topdeptid", ""); 
//        String json = JSON.encode(map);
//        String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", json, TransUtil.ENCODING);
//        System.out.println(resultJson);
//        {"result":[{"deptid":"00000000-0000-0000-0000-000000000000","pdeptid":"","deptname":"曲靖市","organCode":null}],"status":0}
	
		 //police_list
        Map<String,String> map = new HashMap<String,String>();
        map.put("userid", "1");
        map.put("stamp", "rrr"); 
        map.put("deptid", "00000000-0000-0000-0000-000000000000"); 
        String json = JSON.encode(map);
        String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"police_list", json, TransUtil.ENCODING);
        System.out.println(resultJson);
//		{"result":{"total":3,"list":[{"id":"532228198110021937","name":"郭龙文","policeid":"059984"},{"id":"532224197704190025","name":"严惠云","policeid":"025956"},{"id":"532228198011011952","name":"朱聪","policeid":"058699"}]},"status":0}

        
        //police_detail
//        Map<String,String> map = new HashMap<String,String>();
//        map.put("userid", "1");
//        map.put("stamp", "rrr"); 
//        map.put("id", "532224197704190025"); 
//        map.put("policeid", ""); 
//        String json = JSON.encode(map);
//        String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"police_detail", json, TransUtil.ENCODING);
//        System.out.println(resultJson);
//        {"result":{"name":"严惠云","sex":0,"nation":"汉族","politicesstatus":"非中共党员","address":"云南省曲靖市陆良县南门东小街129号","phone1":"13887466800","phone2":"","phone3":"","deptid":"FAA32549-06FF-4DF3-8D8B-095D115A2608","deptname":"陆良县公安局","job":null,"level":null,"policetype":null,"policenumber":null,"jingxian":"三级警督","memo":"","updatetime":null},"status":0}

	}
}
