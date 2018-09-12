package com.yinzhi.platform.module.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.system.User;
import com.yinzhi.platform.entity.system.Community;
import com.yinzhi.platform.global.UserUtil;
import com.yinzhi.platform.module.system.service.ICommunityService;

@Controller
@RequestMapping("/system/community")
public class CommunityController extends BaseController {
	
	@Autowired
	private ICommunityService service;
	
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		
		User user = UserUtil.getUser(request);
		List<Community> communityList = this.service.getCommunityListById(1);
		Community community = new Community();
		if(communityList.size() > 0){
			community = communityList.get(0);
		}
		model.addAttribute("community", community);
		return "/system/community/index";
	}
	
}
