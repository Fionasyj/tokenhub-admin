package com.yinzhi.platform.module.system.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.system.UserLog;
import com.yinzhi.platform.module.system.service.IUserLogService;


@Controller
@RequestMapping("/system/userlog")
public class UserLogController extends BaseController{
	@Autowired
	private IUserLogService userLogService;
	
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage(UserLog userLog, HttpServletRequest request) {
		return this.userLogService.getPage(userLog, getStart(request), getLimit(request));
	}
	
	
}
