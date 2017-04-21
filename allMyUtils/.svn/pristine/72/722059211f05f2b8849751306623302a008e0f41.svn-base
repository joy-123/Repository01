package com.joy.utils.threadlocal;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;

/**
 * 类描述：做一个前置通知 
 * 不管是 谁来 操作我们的 文章有关的信息 都去记录
 * 类名：com.joy.xwb.controller.LogsAspect
 * 时间：2017年4月13日   下午8:37:50
 */
public class LogsAspect {
//	@Resource
//	private ILogsService logsService;
	public void before(JoinPoint joinPoint){//通知方法中不能传其他参数，需要通过工具类获取session中存有信息
		HttpServletRequest req=SysContext.getRequest();
		HttpSession session=req.getSession();
//		AdminUser adminUser=(AdminUser) session.getAttribute("user");
		
		String methodName=joinPoint.getSignature().getName();	//获取切入点方法名
//		Logs s=new Logs();
//		s.setCaozuo("调用了"+methodName+"方法");
//		s.setUserid(adminUser.getUserId());
//		s.setUsername(adminUser.getUserName());
//		logsService.add(s);
//		
	}

}
