package com.joy.utils.threadlocal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 类描述：
 * 1：私有属性ThreadLocal并实例化线程池，并提供静态set方法（给外界调用把resquest/response放入给线程池保存）
   	写一个过滤器配置过滤所有请求，调用这个类的set方法把请求中的resquest/response对象 放到线程池中
 * 2：提供静态get方法（方便方法中没有传入request的等相关信息时，直接调用该工具类获取resquest/response里的信息

 * 类名：com.joy.xwb.util.SysContext
 * 时间：2017年4月13日   下午8:14:57
 */
public class SysContext {
	//实例化两个线程池ThreadLocal
	private static ThreadLocal<HttpServletRequest> requestLocal=
		new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal=
		new ThreadLocal<HttpServletResponse>();
	
    //通过set方法，把HttpServletRequest/HttpServletResponse放入线程池中
    public static void setRequest(HttpServletRequest request){  
        requestLocal.set(request);  
    }  
	
    public static void setResponse(HttpServletResponse response){  
        responseLocal.set(response);  
    }  
    /**
     * 
     *  以下三个方法用于 方便方法中没有传入request的等相关信息时，
     *  直接调用该工具类获取HttpServletRequest/HttpServletResponse里的信息
     *  （例如aop切入日志功能，通知方法中不能随意添加其他参数）
     */
     
    //提供set方法 取出线程池中的HttpServletRequest/HttpServletResponse对象
	public static HttpServletRequest getRequest(){  
        return requestLocal.get();
    }  
      
    public static HttpServletResponse getResponse(){  
        return responseLocal.get();  
    }  
      
    //通过线程池中的HttpServletRequest获取HttpSession，方便调用
    public static HttpSession getSession(){  
        return (HttpSession)(getRequest()).getSession();  
    }  
    
   
}
