package com.joy.utils.securitycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;



/**
 * 类描述：用于springmvc的验证码的Controller   (验证码方法内容与servlet一样，前台获取也一样)
 * 类名：com.joy.springmvc.controller.SecurityCodeController
 * 时间：2017年4月7日   下午8:13:50
 */
//@Controller
public class SecurityCodeSpringMVC {
	//@RequestMapping("/util/getCode.do")  //注解请求
	public void getCode(ServletRequest request,HttpServletResponse res) throws IOException{
		//创建一个内存  影像  ：宽、高、RGB三原色
		BufferedImage image=new BufferedImage(80, 30, BufferedImage.TYPE_INT_RGB);	   
		// 获得画笔
		Graphics g=image.getGraphics();
		// 设置 背景颜色宽高
		g.fillRect(0, 0, 80, 30); //宽高在内存  影像范围内
		//初始化画笔颜色
		g.setColor(new Color(255,255,255));//白色	
		// 给笔设置我们的颜色 重置颜色随机
		Random r=new Random();
		g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
		// 给画笔重置字体
		g.setFont(new Font(null,Font.ITALIC,24));// 字体默认，   风格：斜体，   大小24
		// 生成我们的随机数 （即验证码元素库）		
		char[] cc={'A','B','C','D','E','F','G','H','P','1','2','3','4','5','6'};
		
		//随机获取指定位数的验证码
		String code="";
		for(int i=0;i<5;i++){//5位
			code+=String.valueOf(cc[r.nextInt(cc.length)]);//char[]的下标随机获取
		}
		/*
		 * 
		 * #获取验证码后放入session，用于验证时取出和输入的比对验证（用忽略String大小写比对）。这里不演示
		*/	
		HttpServletRequest req=(HttpServletRequest) request;
		HttpSession session=req.getSession();
		session.setAttribute("securityCode", code);
		
		
		
		
		
		// 绘制图片
		g.drawString(code, 5, 26); // 矩形左下角 的距离顶点的距离即xy坐标
		// 加干扰线：线的颜色随机；两点一线确定一条直线。（一个xy坐标一个点，注意点的位置都要在上面设置的背景范围内）
		for(int i=0;i<8;i++){
			g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(30),r.nextInt(80), r.nextInt(30));
		}
		
		/**
		 * 压缩图片并且输出
		 */
		//告诉浏览器 我们的返回时一张图片，格式固定jpeg
		res.setContentType("image/jpeg");
		// 使用字节流 输出
		OutputStream os=res.getOutputStream();
		// 压缩图片，并且用字节输出流输出
		javax.imageio.ImageIO.write(image, "jpeg", os);
		//关闭流
		os.close();
		
		
		/*
		 * 页面用img标签获取
		 * <!-- url添加时间，防止浏览器缓存。因为每次请求的时间都会变化，请求url就不同，就不会从浏览器缓存中找到 -->
		 * <img id="yzm" src="controllerGetCode.action?" onclick="this.src='controllerGetCode.action?'+new Date()"/>
		   <a onclick="dd();">下一张</a>
		 	
		    <script type="text/javascript">
		     function dd(){
		       document.getElementById("yzm").src="controllerGetCode.action?"+new Date();
		     }
		   </script>
  
		 *
		 *
		 *或者直接：
		 *<img id="num" src="<%=path %>/util/getCode.do" onclick="this.src='<%=path %>/util/getCode.do?'+new Date()"/>
		<a href="javascript:;" onclick="document.getElementById('num').src = '<%=path %>/util/getCode.do?'+new Date();">换一张</a>
								
		 */
		
		
		
		
	}
    
	
	//验证码验证（也可提交后在注册的addUser方法里验证）
	//@RequestMapping("/util/checkCode.do")
	public void login(HttpServletRequest req,HttpServletResponse res){
		String code=req.getParameter("code");
		HttpSession session=req.getSession();
		String securityCode=(String) session.getAttribute("securityCode");
	
		PrintWriter out=null;
		try {
			out=res.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(code.equalsIgnoreCase(securityCode)){
			out.print("ok");
		}else{
			out.print("error");
		}
		out.close();
	}
	
		//前台ajax获取返回值后判断是否“ok”，是则验证通过登陆/验证按钮可提交
}
