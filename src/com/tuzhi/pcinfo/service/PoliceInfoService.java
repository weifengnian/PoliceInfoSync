package com.tuzhi.pcinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuzhi.pcinfo.dao.IPoliceInfoDao;
import com.tuzhi.pcinfo.entity.Atsmart_police_info;
import com.tuzhi.pcinfo.entity.Atsmart_police_organization;

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
			JSONObject resultObj = JSONObject.fromObject(JsonStr);
			Map<String, String> map = new HashMap<String, String>();
			map.put("organization_id", resultObj.getString("deptid"));
			policeInfoDao.deleteOrganization(map);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("--Exception:"+e.getMessage());
			strCode="99";
		}
		
		return strCode;
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
