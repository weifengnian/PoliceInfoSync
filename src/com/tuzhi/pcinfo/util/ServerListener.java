package com.tuzhi.pcinfo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.tuzhi.pcinfo.manager.PoliceInfoAction;

public class ServerListener implements ServletContextListener {
	
	private static final Log log = LogFactory.getLog(ServerListener.class);

    static int count =0;

    @Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// TODO Auto-generated method stub
			TimerTask task = new TimerTask() {
		          @Override
		           public void run() {
		              ++count;
		              System.out.println("时间=" +new Date() + " 执行了" + count + "次"); // 1次
//			              PoliceInfoAction c = new PoliceInfoAction();
//			              c.register();
		           }
		       };

		       //设置执行时间
		       Calendar calendar =Calendar.getInstance();
		       //年
		       int year = calendar.get(Calendar.YEAR);
		       //月
		       int month = calendar.get(Calendar.MONTH);
		       //日
		       int day =calendar.get(Calendar.DAY_OF_MONTH);

		       //定制每天的21:09:00执行，
		       calendar.set(year, month, day, 8, 40, 00);

		       Date date = calendar.getTime();

		       Timer timer = new Timer();
		       
		       //每天的date时刻执行task, 仅执行一次
//		       timer.schedule(task, date);
		       //86400
		       //每天的date时刻执行task，每隔24小时重复执行  （1000毫秒=1秒）
		       int period =  86400 * 1000;
	    	   timer.schedule(task, date, period);
		       
//		       timer.scheduleAtFixedRate(task, date, period);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("-----Exception--ServerListener:"+e.getMessage());
		}
	}

//    public static void main(String[] args) {
//    	ServerListener s = new ServerListener();
//    	s.contextInitialized(null);
//    }
}
