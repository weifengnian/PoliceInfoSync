package com.tuzhi.pcinfo.service;

import java.util.List;
import java.util.Map;

import com.tuzhi.pcinfo.entity.Atsmart_police_info;
import com.tuzhi.pcinfo.entity.Atsmart_police_organization;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月17日	
 * @Copyright:
 */
public interface IPoliceInfoService {
	
	/**
	 * 同步警信息
	 * @param JsonStr
	 * @return
	 */
	public String syncPoliceInfo(String userid,String stamp);
	
	/**
	 * 获取警员信息
	 * @param map
	 * @return
	 */
	public List<Atsmart_police_info> getPoliceInfo(Map<String, String> map);
	
	/**
	 * 添加警员信息
	 * @param map
	 * @return
	 */
	public Integer addPoliceInfo(Map<String, String> map);
	
	/**
	 * 修改警员信息
	 * @param map
	 * @return
	 */
	public Integer updatePoliceInfo(Map<String, String> map);
	
	/**
	 * 删除警员信息
	 * @param map
	 * @return
	 */
	public Integer deletePoliceInfo(Map<String, String> map);
	
	/**
	 * 获取组织（机构）
	 * @param map
	 * @return
	 */
	public List<Atsmart_police_organization> getOrganization(Map<String, String> map);
	
	/**
	 * 添加组织（机构）
	 * @param map
	 * @return
	 */
	public Integer addOrganization(Map<String, String> map);
	
	/**
	 * 修改组织（机构）
	 * @param map
	 * @return
	 */
	public Integer updateOrganization(Map<String, String> map);
	
	/**
	 * 删除组织（机构）
	 * @param map
	 * @return
	 */
	public Integer deleteOrganization(Map<String, String> map);
	
	/**
	 * 执行存储过程
	 * @param JsonStr
	 * @return
	 */
	public String executeProcedure(Map<String, String> map);
	
}
