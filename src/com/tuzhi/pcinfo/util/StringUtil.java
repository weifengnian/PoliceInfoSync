package com.tuzhi.pcinfo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import net.arnx.jsonic.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月22日	
 * @Copyright:
 */
public class StringUtil {
	
//	private final static Logger log = LoggerFactory.getLogger(StringUtil.class);
	
	/**
	 * @param jsonStr
	 * @return
	 */
	public static Map<String, String> poiliceInfo(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject  = JSONObject.fromObject(jsonStr);
		String status = jsonObject.getString("status");
		if(!"0".equals(status)) return map;
		String result = jsonObject.getString("result");
		JSONObject jsonObject1  = JSONObject.fromObject(result);
		String list = jsonObject1.getString("list");
		JSONArray arr = JSONArray.fromObject(list);
		for (int i = 0; i < arr.size(); i++) {
			JSONObject oj1 = arr.getJSONObject(i);
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("userid", "1");
			map1.put("stamp", "rrr"); 
			map1.put("id", oj1.getString("id")); 
			map1.put("policeid", oj1.getString("policeid"));
			//查询 详细 明细
			String json = JSON.encode(map1);
		    String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"police_detail", json, TransUtil.ENCODING);
		    if(!StringUtil.isBlank(resultJson)){
		    	JSONObject ob = JSONObject.fromObject(resultJson);
		    	String stu = ob.getString("status");
		    	if("0".equals(stu)){
		    		String resultDetailJson = ob.getString("result");
		    		StringUtil.pcMap(map1,resultDetailJson,map);
		    	}
		    }
		}
		return map;
	}

	public static void pcMap(Map<String, String> map1,String resultJson,Map<String, String> map){
		JSONObject jb = JSONObject.fromObject(resultJson); 
//		map.put("id", ""); //
		map.put("id_remote", ""); //sqlserver支持的一种编码
		map.put("police_id", jb.getString("policenumber")); //警员编号
		map.put("police_name", map1.get("name")); //警员姓名
		map.put("organ_code", map1.get("policeid")); //组织编码
		map.put("sex", "0".equals(jb.getString("sex"))?"2":jb.getString("sex")); //性别
		map.put("card_id", map1.get("id")); //身份证号
		map.put("duty", jb.getString("job")); //职位
		map.put("image_url", ""); //头像
		map.put("organization_id", jb.getString("deptid")); //组织id
		map.put("synchro_time", ""); //同步时间
		map.put("update_time", jb.getString("updatetime")); //更新时间
		map.put("is_user", ""); //
		map.put("description", jb.getString("memo")); //描述
		map.put("nation", jb.getString("nation")); //民族
		map.put("politices_status", jb.getString("politicesstatus")); //政治面貌
		map.put("address", jb.getString("address")); //住址
		map.put("phone1", jb.getString("phone1")); //电话1
		map.put("phone2", jb.getString("phone2")); //电话2
		map.put("phone3", jb.getString("phone3")); //电话3
		map.put("policetype", jb.getString("policetype")); //警种
		map.put("jingxian", jb.getString("jingxian")); //警衔
	}
	
	//组织明细
	public static void pcOrganizationInfo(String resultJson,Map<String, String> map){
		JSONObject jb = JSONObject.fromObject(resultJson);
//		map.put("id", ""); //
		map.put("organization_id", jb.getString("deptid")); //组织id
		map.put("organ_code", jb.getString("organCode")); //组织编码
		map.put("p_org_id", jb.getString("pdeptid")); //组织父节点id
		map.put("organization_name", jb.getString("deptname")); //组织名称
		map.put("org_short_name", ""); //组织简称
		map.put("synchro_time", ""); //同步时间
		map.put("update_time", ""); //更新时间
		map.put("is_user", "1"); //
		map.put("description", ""); //描述
	} 
	
	//组织编号
	public static void pcOrganization(Map<String, String> map1,String resultJson){
		JSONObject jb = JSONObject.fromObject(resultJson);
		map1.put("userid", "1");
		map1.put("stamp", "rrr");
		map1.put("topdeptid", jb.getString("deptid"));
	} 
	
	public static void main(String[] args) {
        Map<String,String> map = new HashMap<String,String>();
        map.put("userid", "1");
        map.put("stamp", "rrr"); 
        map.put("deptid", "FAA32549-06FF-4DF3-8D8B-095D115A2608"); 
        String json = JSON.encode(map);
        String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"police_list", json, TransUtil.ENCODING);
        System.out.println("police_list:"+resultJson);
		StringUtil.poiliceInfo(resultJson);
	}

//	{"result":{"total":3,"list":[{"id":"532228198110021937","name":"郭龙文","policeid":"059984"},{"id":"532224197704190025","name":"严惠云","policeid":"025956"},{"id":"532228198011011952","name":"朱聪","policeid":"058699"}]},"status":0}	
//	{"result":{"name":"严惠云","sex":0,"nation":"汉族","politicesstatus":"非中共党员","address":"云南省曲靖市陆良县南门东小街129号","phone1":"13887466800","phone2":"","phone3":"","deptid":"FAA32549-06FF-4DF3-8D8B-095D115A2608","deptname":"陆良县公安局","job":null,"level":null,"policetype":null,"policenumber":null,"jingxian":"三级警督","memo":"","updatetime":null},"status":0}

	
	/**
	 * 判断字符是否为空
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (null == str)
			return true;
		if ("".equals(str.replaceAll(" "," ").trim()))
			return true;
		return false;
	}
	
	
	/**
	 * 获得date时间
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	private static final SimpleDateFormat DF_YMDHMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static String getDisplayYMDHMS(){
		try {
			Calendar calendar = Calendar.getInstance();
			return ((SimpleDateFormat) DF_YMDHMS.clone()).format(calendar
					.getTime());
		} catch (Exception e) {
			return null;
		}		
	}
	
	/**
	 * 获得date时间
	 * @param date
	 * @return yyyy-MM-dd
	 */
	private static final SimpleDateFormat DF_YMD = new SimpleDateFormat("yyyy-MM-dd");
	public static String getDisplayYMD(){
		try {
			Calendar calendar = Calendar.getInstance();
			return ((SimpleDateFormat) DF_YMD.clone()).format(calendar
					.getTime());
		} catch (Exception e) {
			return null;
		}		
	}
	
	/**
	 * 获取uuid
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().toUpperCase();
	}
	public static String getShortUUID() {
		String s = getUUID();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
		+ s.substring(19, 23) + s.substring(24);
	}

}
