package com.yinzhi.platform.global;

import javax.servlet.http.HttpServletRequest;

import com.yinzhi.platform.entity.system.User;




public class UserUtil {
	
	public static User getUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute("USER");
	}
	
}