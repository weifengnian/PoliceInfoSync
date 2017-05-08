package com.tuzhi.pcinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.arnx.jsonic.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuzhi.pcinfo.dao.IPoliceInfoDao;
import com.tuzhi.pcinfo.entity.Atsmart_police_info;
import com.tuzhi.pcinfo.entity.Atsmart_police_organization;
import com.tuzhi.pcinfo.util.HttpClientUtil;
import com.tuzhi.pcinfo.util.StringUtil;
import com.tuzhi.pcinfo.util.TransUtil;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月20日	
 * @Copyright:
 */
public class PoliceInfoService implements IPoliceInfoService {
	
	private final static Logger log = LoggerFactory.getLogger(PoliceInfoService.class);

	private IPoliceInfoDao policeInfoDao;

	public IPoliceInfoDao getPoliceInfoDao() {
		return policeInfoDao;
	}
	public void setPoliceInfoDao(IPoliceInfoDao policeInfoDao) {
		this.policeInfoDao = policeInfoDao;
	}
	
	@Override
	public String syncPoliceInfo(String userid,String stamp) {
		// TODO Auto-generated method stub
		//结果码（00成功，99程序异常）
		String strCode = "00";
		try {
			//组织构架协议（organization）
	        Map<String,String> map = new HashMap<String,String>();
	        map.put("userid", userid);
	        map.put("stamp", stamp); 
	        map.put("topdeptid", ""); 
	        String json = JSON.encode(map);
	        String rltOrganizationStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", json, TransUtil.ENCODING);
	        //组织
	        organization(rltOrganizationStr);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("--Exception:"+e.getMessage());
			strCode="99";
		}
		return strCode;
	}
	
	//组织
	public Map<String, String> organization(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject  = JSONObject.fromObject(jsonStr);
		String status = jsonObject.getString("status");
		String result = jsonObject.getString("result");
		if("0".equals(status) && result.length()>2){
			JSONArray arr = JSONArray.fromObject(result);
			for (int i = 0; i < arr.size(); i++) {
				JSONObject oj1 = arr.getJSONObject(i);
				//删除，添加组织
				adOgz(oj1);
				Map<String, String> map1 = new HashMap<String, String>();
				StringUtil.pcOrganization(map1,oj1);
//				String json = JSON.encode(map);
//		        String rltOrganizationStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", json, TransUtil.ENCODING);
//		        organization(rltOrganizationStr);
			}
		}
		return map;
	}
	
	//删除，添加组织
	public void adOgz(JSONObject oj1){
		Map<String, String> map = new HashMap<String, String>();
		//构建组织信息（map）
		StringUtil.pcOrganizationInfo(oj1,map);
		//先删除组织
		int num = policeInfoDao.deleteOrganization(map);
		//添加组织信息
		num = policeInfoDao.addOrganization(map);
		
		/**
		 * 查询警员信息
		 */
		policeInfoList(oj1);
	}
	
	//警员信息
	public void policeInfoList(JSONObject oj1){
		Map<String,String> map = new HashMap<String,String>();
		map.put("userid", "1");
		map.put("stamp", "rrr"); 
		map.put("deptid", oj1.getString("deptid")); 
		String json = JSON.encode(map);
		String resultJson = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"police_list", json, TransUtil.ENCODING);
		if(!StringUtil.isBlank(resultJson)){
			policeInfoDetail(resultJson);
		}
	}
	
	//警员详细
	public Map<String, String> policeInfoDetail(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject  = JSONObject.fromObject(jsonStr);
		String status = jsonObject.getString("status");
		String result = jsonObject.getString("result");
		if("0".equals(status)){
			JSONObject jsonObject1  = JSONObject.fromObject(result);
			String list = jsonObject1.getString("list");
			if(list.length()>2){
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
				    	String resultDetailJson = ob.getString("result");
				    	if("0".equals(stu) && resultDetailJson.length()>2){
				    		StringUtil.pcMap(map1,resultDetailJson,map);
				    		//删除警员信息
				    		int num = policeInfoDao.deletePoliceInfo(map);
				    		//添加警员信息
				    		num = policeInfoDao.addPoliceInfo(map);
				    	}
				    }
				}
			}
		}
		return map;
	}
	
	@Override
	public List<Atsmart_police_info> getPoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.getPoliceInfo(map);
	}
	
	@Override
	public Integer addPoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.addPoliceInfo(map);
	}
	
	@Override
	public Integer updatePoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.updatePoliceInfo(map);
	}
	
	@Override
	public Integer deletePoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.deletePoliceInfo(map);
	}
	
	@Override
	public List<Atsmart_police_organization> getOrganization(
			Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.getOrganization(map);
	}
	
	@Override
	public Integer addOrganization(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.addOrganization(map);
	}
	
	@Override
	public Integer updateOrganization(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.updateOrganization(map);
	}
	
	@Override
	public Integer deleteOrganization(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.deleteOrganization(map);
	}

}
