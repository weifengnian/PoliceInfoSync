package com.tuzhi.pcinfo.dao;

import java.util.List;
import java.util.Map;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import com.tuzhi.pcinfo.entity.Atsmart_police_info;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月17日	
 * @Copyright:
 */
public class PoliceInfoDao extends SqlSessionDaoSupport implements IPoliceInfoDao {

	@Override
	public List<Atsmart_police_info> getPoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("PoliceInfoDaoMapper.getPoliceInfo",map);
	}

	@Override
	public Integer insertPoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().insert("PoliceInfoDaoMapper.insertPoliceInfo",map);
	}

	@Override
	public Integer updatePoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().update("PoliceInfoDaoMapper.updatePoliceInfo",map);
	}

	@Override
	public Integer deletePoliceInfo(Map<String, String> map) {
		// TODO Auto-generated method stub
		return getSqlSession().delete("PoliceInfoDaoMapper.deletePoliceInfo",map);
	}

}
