package com.tuzhi.pcinfo.service;

import java.util.List;
import java.util.Map;
import com.tuzhi.pcinfo.dao.IPoliceInfoDao;
import com.tuzhi.pcinfo.entity.Atsmart_police_info;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月20日	
 * @Copyright:
 */
public class PoliceInfoService implements IPoliceInfoService {

	private IPoliceInfoDao policeInfoDao;

	public IPoliceInfoDao getPoliceInfoDao() {
		return policeInfoDao;
	}
	public void setPoliceInfoDao(IPoliceInfoDao policeInfoDao) {
		this.policeInfoDao = policeInfoDao;
	}
	
	@Override
	public List<Atsmart_police_info> getPoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.getPoliceInfo(map);
	}
	
	@Override
	public Integer insertPoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return policeInfoDao.insertPoliceInfo(map);
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

}
