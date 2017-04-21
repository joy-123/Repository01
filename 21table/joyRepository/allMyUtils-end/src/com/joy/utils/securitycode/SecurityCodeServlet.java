package com.joy.utils.securitycode;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 类描述：用于servlet的验证码的servlet
 * 类名：com.joy.springmvc.controller.SecurityCodeServlet
 * 时间：2017年4月7日   下午7:36:07
 */
public class SecurityCodeServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
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
		 * <img id="yzm" src="securityCodeServlet?" onclick="this.src='securityCodeServlet?'+new Date()"/>
		 <!-- 添加时间，防止浏览器缓存。因为每次请求的时间都会变化，请求url就不同，就不会从浏览器缓存中找到 -->
		 */
}
	
	
	
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}



	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	
	

}
