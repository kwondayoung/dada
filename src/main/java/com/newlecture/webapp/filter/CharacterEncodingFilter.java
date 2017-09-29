package com.newlecture.webapp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
//필터는 서블릿으로 가는 요청을 가로채는 것
//
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("헬러 필터~"); //서블릿 또는 이후 필터가 실행되기 전에 실행
		chain.doFilter(request, response);
		System.out.println("잘가 필터"); //후에 실행
		
	}
	
	
	 
	 

}
