package com.tuzhi.pcinfo.service;

import java.util.List;
import java.util.Map;
import com.tuzhi.pcinfo.entity.Atsmart_police_info;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月17日	
 * @Copyright:
 */
public interface IPoliceInfoService {
	
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
	public Integer insertPoliceInfo(Map<String, String> map);
	
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
	
}
