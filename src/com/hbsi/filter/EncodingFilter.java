package com.hbsi.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class EncodingFilter implements Filter {

    public EncodingFilter() {
        
    }

	
	public void destroy() {
		
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
	    //设置请求数据字符编码是utf-8
		request.setCharacterEncoding("utf-8");
		//设置写出数据的字符编码是utf-8
//		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//设置请求或应答转发给下一个资源
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
