package com.tuzhi.pcinfo.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import net.arnx.jsonic.JSON;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuzhi.pcinfo.entity.Atsmart_police_info;
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
			String resultStr = policeInfoService.syncPoliceInfo(map.get("userid"),jbo.getString("stamp"));
			log.info("--resultStr:"+resultStr);
			//如果不等于00，程序异常，重新执行该方法
			if(!"00".equals(resultStr)){
			  int num = StringUtil.cnt();
				if(num==-1){
					return;
				}
				this.register();
			}
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---register--Exception:"+e.getMessage());
			int num = StringUtil.cnt();
			if(num==-1){
				return;
			}
			this.register();
		}
	}
	
	public void plice_info(){
		try {
			//应用认证协议（Register）
			String rltRegisterStr = HttpClientUtil.doGet(TransUtil.REGISTER+"plice_info_tb",TransUtil.ENCODING);
			log.info("--plice_info:"+rltRegisterStr);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---plice_info--Exception:"+e.getMessage());
		}
	}
	
	public void organaization(){
		try {
			//应用认证协议（Register）
			String rltRegisterStr = HttpClientUtil.doGet(TransUtil.REGISTER+"organaization_tb",TransUtil.ENCODING);
			log.info("--organaization:"+rltRegisterStr);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---organaization--Exception:"+e.getMessage());
		}
	}
	
	//执行存储过程
	public void executeProcedure(){
		try {
			Map<String,String> map = new HashMap<String,String>();
			//执行存储过程
			policeInfoService.executeProcedure(map);
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---organaization--Exception:"+e.getMessage());
		}
	}
	
	/**
	 * 测试
	 */
	public void testPoliceUrl(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			//应用认证协议（Register）
			Map<String,String> map = new HashMap<String,String>();
			map.put("userid", "1");
			map.put("app_id", "1"); //{"result":{"stamp":"rrr","app_name":"app1"},"status":0}
			String json = JSON.encode(map);
			String rltRegisterStr = HttpClientUtil.jsonDoPost(TransUtil.REGISTER+"Register", json, TransUtil.ENCODING);
			log.info("--rltRegisterStr:"+rltRegisterStr);
			if(rltRegisterStr.length()>10){
				response.getWriter().write("{\"status\":\"00\",\"retMsg\":\"成功,'"+rltRegisterStr+"'\"}");
			}else{
				response.getWriter().write("{\"status\":\"01\",\"retMsg\":\"失败，地址无法访问到数据\"}");			
			}
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---test--Exception:"+e.getMessage());
		}
	}
	
	/**
	 * 测试自己数据库链接是否可用
	 */
	public void testMeSqlUrl(){
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			Map<String,String> map = new HashMap<String,String>();
			List<Atsmart_police_info> list = policeInfoService.getPoliceInfo(map);
			log.info("--list:"+list);
			if(list.size()>0){
				response.getWriter().write("{\"status\":\"00\",\"retMsg\":\"成功\"}");
			}else{
				response.getWriter().write("{\"status\":\"01\",\"retMsg\":\"失败,自己数据库无法使用\"}");			
			}
			return;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("---test--Exception:"+e.getMessage());
		}
	}
	
	public IPoliceInfoService getPoliceInfoService() {
		return policeInfoService;
	}
	public void setPoliceInfoService(IPoliceInfoService policeInfoService) {
		this.policeInfoService = policeInfoService;
	}
}
