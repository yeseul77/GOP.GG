package com.gg.gop.service;

import java.io.*;

import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

// 403(권한) 오류를 처리하는 핸들러
@Component
public class MyAccessDenialHandler implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		HttpSession session = req.getSession();
		session.setAttribute("msg", "잘못된 작업 요청입니다");
		res.sendRedirect("/"); //"index.jsp"
	}
}
