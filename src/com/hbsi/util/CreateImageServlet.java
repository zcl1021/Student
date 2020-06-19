package com.hbsi.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class CreateImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
   //定义一个方法，用来获取某一颜色范围内的随机颜色
	Color getRandColor(int fc, int bc) {
		Random random = new Random();//定义获取随机数的随机选择器对象
	    if(fc>255) {
	    	fc=255;
	    }
	    if(bc>255) {
	    	bc=255;
	    }
	    //r表示红色分量值，random.nextInt(bc-fc)得到的值是0到bc-fc之间的随机整数，不包括bc-fc的值
	    
	    int r=fc+random.nextInt(bc-fc);
	    int g=fc+random.nextInt(bc-fc);//绿色分量
	    int b=fc+random.nextInt(bc-fc);//蓝色分量
	    //使用红绿蓝3个颜色分量创建一个颜色对象
	    Color color = new Color(r,g,b);
	    return color;//返回创建好的颜色对象
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//1.设置向客户端写回应答的数据类型
		response.setContentType("image/jpeg");
		//设置页面不适用缓存
		response.setHeader("Paragma", "No-cache");//响应信息不能缓存
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//2.开始画动态图片
		//d定义验证码图片大小
		int width=70;
		int height=30;
		//创建能够在内存中修改的图片对象
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		//获取绘制图片的Granphics对象(画笔)
		Graphics g = image.getGraphics();
		//画背景图片
		g.setColor(getRandColor(200,250));//设置画笔颜色
		g.fillRect(0, 0, width, height);//画背景图片
		//画干扰线
		g.setColor(getRandColor(160,200));//设置干扰线的颜色
		//创建一个随机器对象，用来设置干扰线的起始点坐标
		Random r =new Random();
		for(int i=0;i<100;i++) {//画100条干扰线
			//取0到width值之家你的一随机整数
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			//设置干扰线的结束点坐标
			int x1=r.nextInt(12);
			int y1=r.nextInt(12);
			//画干扰线
			g.drawLine(x, y, x+x1, y+y1);
			
			
		}
		//画随机验证码字符
		String codestr="";//定义字符串变量，保存验证码，设置会话属性值
		for(int i=0;i<4;i++) {//通过循环，画4个字符
			String rand="";
			rand= String.valueOf(r.nextInt(10));//随机去0到10之间的数
			//设置字体颜色
			g.setColor(getRandColor(20,130));
			//画出字符
			g.drawString(rand, 13*i+6, 16);
			//每一次循环把画出字符附加到codestr中
			codestr+=rand;
		}
		HttpSession session =request.getSession();
		session.setAttribute("code", codestr);//把验证码字符串设置为会话属性code的值
		//4.把缓存图片输出到客户端
		ImageIO.write(image, "JPEG", response.getOutputStream());
		//5.清空缓存，释放资源
		response.getOutputStream().flush();
		response.getOutputStream().close();
		response.flushBuffer();
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		doGet(request, response);
	}

}
