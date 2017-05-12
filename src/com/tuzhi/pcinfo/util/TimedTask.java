package com.tuzhi.pcinfo.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TimedTask {
	
	private static final Logger log = LoggerFactory.getLogger(TimedTask.class);
	
	public void registerRun() throws Exception{
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate=new Date();
			log.info("--------DateTime:"+format.format(beginDate));
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(TransUtil.LOCURL+"register.action");
			client.executeMethod(method);
			String result = new String(method.getResponseBody());
			log.info("--------result:"+result);
		} catch (HttpException e) {
			// TODO: handle exception
			log.info("--------HttpException:"+e.getMessage());
		} catch (IOException e){
			log.info("--------IOException:"+e.getMessage());
		}
	}
	
	public void plice_infoRun() throws Exception{
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate=new Date();
			log.info("--------DateTime:"+format.format(beginDate));
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(TransUtil.LOCURL+"plice_info.action");
			client.executeMethod(method);
			String result = new String(method.getResponseBody());
			log.info("--------result:"+result);
		} catch (HttpException e) {
			// TODO: handle exception
			log.info("--------HttpException:"+e.getMessage());
		} catch (IOException e){
			log.info("--------IOException:"+e.getMessage());
		}
	}
	
	public void organaizationRun() throws Exception{
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate=new Date();
			log.info("--------DateTime:"+format.format(beginDate));
			HttpClient client = new HttpClient();
			PostMethod method = new PostMethod(TransUtil.LOCURL+"organaization.action");
			client.executeMethod(method);
			String result = new String(method.getResponseBody());
			log.info("--------result:"+result);
		} catch (HttpException e) {
			// TODO: handle exception
			log.info("--------HttpException:"+e.getMessage());
		} catch (IOException e){
			log.info("--------IOException:"+e.getMessage());
		}
	}

}
