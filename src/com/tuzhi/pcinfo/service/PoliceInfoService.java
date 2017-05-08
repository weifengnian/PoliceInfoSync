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
	public String syncPoliceInfo(String JsonStr) {
		// TODO Auto-generated method stub
		//结果码（00成功，99程序异常）
		String strCode = "00";
		
		try {
			
			//组织构架协议（organization）
	        Map<String,String> Ozmap = new HashMap<String,String>();
	        Ozmap.put("userid", "1");
	        Ozmap.put("stamp", "rrr"); 
	        Ozmap.put("topdeptid", ""); 
	        String Ozjson = JSON.encode(Ozmap);
	        String rltOrganizationStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", Ozjson, TransUtil.ENCODING);
			Map<String, String> map = new HashMap<String, String>();
			//构建组织信息（map）
			StringUtil.pcOrganizationInfo(rltOrganizationStr,map);
			//先删除组织
			policeInfoDao.deleteOrganization(map);
			//添加组织信息
			policeInfoDao.addOrganization(map);
			//添加警员信息
			PoliceInfo(rltOrganizationStr);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("--Exception:"+e.getMessage());
			strCode="99";
		}
		return strCode;
	}
	
	public Map<String, String> PoliceInfo(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();
		JSONObject jsonObject  = JSONObject.fromObject(jsonStr);
		String status = jsonObject.getString("status");
		if("0".equals(status)){
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
			    		//删除警员信息
			    		policeInfoDao.deletePoliceInfo(map);
			    		//添加警员信息
			    		policeInfoDao.addPoliceInfo(map);
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
