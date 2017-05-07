package com.tuzhi.pcinfo.manager;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import net.arnx.jsonic.JSON;
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
			Map<String,String> Remap = new HashMap<String,String>();
			Remap.put("userid", "1");
			Remap.put("app_id", "2"); //{"result":{"stamp":"rrr","app_name":"app1"},"status":0}
			String Rejson = JSON.encode(Remap);
			String rltRegisterStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"Register", Rejson, TransUtil.ENCODING);
			log.info("--rltRegisterStr:"+rltRegisterStr);
			if(StringUtil.isBlank(rltRegisterStr)){
				return;
			}
			
	        //组织构架协议（organization）
	        Map<String,String> Ozmap = new HashMap<String,String>();
	        Ozmap.put("userid", "1");
	        Ozmap.put("stamp", "rrr"); 
	        Ozmap.put("topdeptid", "00000000-0000-0000-0000-000000000000"); 
	        String Ozjson = JSON.encode(Ozmap);
	        String rltOrganizationStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"organization", Ozjson, TransUtil.ENCODING);
	        log.info("--rltOrganizationStr:"+rltOrganizationStr);
	        if(StringUtil.isBlank(rltOrganizationStr)){
				return;
			}
			
			String resultStr = policeInfoService.syncPoliceInfo(rltOrganizationStr);
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
