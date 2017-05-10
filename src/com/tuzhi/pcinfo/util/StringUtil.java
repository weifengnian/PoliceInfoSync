package com.tuzhi.pcinfo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.UUID;
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
	
	public static void pcMap(Map<String, String> map1,String resultJson,Map<String, String> map,String organ_code){
		JSONObject jb = JSONObject.fromObject(resultJson); 
//		map.put("id", ""); //
		map.put("id_remote", ""); //sqlserver支持的一种编码
		map.put("police_id", map1.get("policeid")); //警员编号
		map.put("police_name", jb.getString("name")); //警员姓名
		map.put("organ_code", organ_code); //组织编码
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
	public static void pcOrganizationInfo(JSONObject jb,Map<String, String> map){
//		JSONObject jb = JSONObject.fromObject(resultJson);
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
	public static void pcOrganization(Map<String, String> map1,JSONObject oj1){
		map1.put("userid", "1");
		map1.put("stamp", "rrr");
		map1.put("topdeptid", oj1.getString("deptid"));
	} 
	
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
	
	static int num = 0; 
	public static int cnt(){
		num++;
		if(num==10){
			num=-1;
		}
		return num;
	}

}
