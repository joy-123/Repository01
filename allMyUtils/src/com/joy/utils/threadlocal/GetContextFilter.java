package com.joy.utils.threadlocal;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 类描述：该过滤器，调用SysContext类的set方法  把请求中的resquest/response对象 放到线程池中
 * 类名：com.joy.xwb.util.GetContextFileter
 * 时间：2017年4月13日   下午8:20:55
 */
public class GetContextFilter implements Filter{

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		SysContext.setRequest((HttpServletRequest) request);
		SysContext.setResponse((HttpServletResponse)response);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

}
