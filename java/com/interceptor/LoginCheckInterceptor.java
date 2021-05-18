package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginCheckInterceptor  extends HandlerInterceptorAdapter{
@Override
public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
	System.out.println("preHandle");
	HttpSession session = request.getSession();
	if(session.getAttribute("login") == null) {
		session.setAttribute("mesg", "로그인이 필요한 작업입니다.");	
		response.sendRedirect("../login_register");//servlet-context.xml 
		return false;  //주의
	}else {
		return true; //주의
	}
}
@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	System.out.println("postHandle");
		
	}
@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	System.out.println("afterCompletion");
	
	}

}
