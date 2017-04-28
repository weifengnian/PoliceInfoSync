package com.tuzhi.pcinfo.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tuzhi.pcinfo.entity.Atsmart_police_info;
import com.tuzhi.pcinfo.service.IPoliceInfoService;

/**
 * @Description: 
 * @company: 
 * @author: weifengnian
 * @Data: 2017年4月22日	
 * @Copyright:
 */
public class PoliceInfoAction extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
	
	//日志  
	private final static Logger log = LoggerFactory.getLogger(PoliceInfoAction.class);
	
	private IPoliceInfoService policeInfoService;
	
	/**
	 * 
	 */
	public void register(){
		try {
			
			Map<String,String> map = new HashMap<String,String>();
			map.put("name", "test");
			map.put("password", "test");
			map.put("IMSI", "1234");
			map.put("IMEI", "666");
			
			String status = "0";
			String retMsg = "成功";
			
			List<Atsmart_police_info> list = policeInfoService.getPoliceInfo(map);
			if(list.size()>0){
				System.out.println("123");
			}
		
			log.info("--status:"+status+"--retMsg:"+retMsg);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---register--Exception:"+e.getMessage());
		}
	}

	public IPoliceInfoService getPoliceInfoService() {
		return policeInfoService;
	}
	public void setPoliceInfoService(IPoliceInfoService policeInfoService) {
		this.policeInfoService = policeInfoService;
	}
}
