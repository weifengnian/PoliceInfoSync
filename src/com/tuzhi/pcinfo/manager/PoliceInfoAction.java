package com.tuzhi.pcinfo.manager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import net.arnx.jsonic.JSON;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuzhi.pcinfo.service.IPoliceInfoService;
import com.tuzhi.pcinfo.util.HttpClientUtil;
import com.tuzhi.pcinfo.util.StringUtil;
import com.tuzhi.pcinfo.util.TransUtil;

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
	 * 应用认证协议
	 */
	public void register(){
		try {
			
			//应用认证协议（Register）
			Map<String,String> map = new HashMap<String,String>();
			map.put("userid", "1");
			map.put("app_id", "1"); //{"result":{"stamp":"rrr","app_name":"app1"},"status":0}
			String json = JSON.encode(map);
			String rltRegisterStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"Register", json, TransUtil.ENCODING);
			log.info("--rltRegisterStr:"+rltRegisterStr);
			if(StringUtil.isBlank(rltRegisterStr)){
				return;
			}
			JSONObject jb = JSONObject.fromObject(rltRegisterStr);
			String result = jb.getString("result");
			JSONObject jbo = JSONObject.fromObject(result);
			String resultStr = policeInfoService.syncPoliceInfo(map.get("userid"),jbo.getString("stamp").toString());
			log.info("--resultStr:"+resultStr);
			//如果不等于00，程序异常，重新执行该方法
			if(!"00".equals(resultStr)){
				PoliceInfoAction pa = new PoliceInfoAction();
				pa.register();
			}
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---register--Exception:"+e.getMessage());
			PoliceInfoAction pa = new PoliceInfoAction();
			pa.register();
		}
	}
	
	public IPoliceInfoService getPoliceInfoService() {
		return policeInfoService;
	}
	public void setPoliceInfoService(IPoliceInfoService policeInfoService) {
		this.policeInfoService = policeInfoService;
	}
}
