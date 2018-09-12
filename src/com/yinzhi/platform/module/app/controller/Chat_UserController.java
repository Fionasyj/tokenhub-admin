package com.yinzhi.platform.module.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.app.Chat_User;
import com.yinzhi.platform.module.app.service.IChat_UserService;


@Controller
@RequestMapping("/app/chat_user")
public class Chat_UserController extends BaseController {
	@Autowired
	private IChat_UserService chat_userService;
	
	/**
	 * 显示首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		return "/app/chat_user/index";
	}
	
	/**
	 * 聊天室用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage(String chat_roomId,String nick,String status, HttpServletRequest request) {
		return this.chat_userService.getPage(chat_roomId, nick,status,getStart(request), getLimit(request));
	}
	
	/**
	 * 显示修改页面
	 * @param userId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(String userId, Model model, HttpServletRequest request) {
		model.addAttribute("user", this.chat_userService.getChat_User(userId));
		
		return "/app/chat_user/edit";
	}
	
	
	@RequestMapping("/getChat_RoomList")
	@ResponseBody
	public List<Chat_User> getChat_UserList() {
		return this.chat_userService.getChat_UserList();
	}
	
	/**
	 * 显示添加页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/add", method = RequestMethod.GET)
	protected String add(Model model, HttpServletRequest request) {
		return "/app/chat_room/add";
	}
	

	/**
	 * 禁言
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "forbid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forbid(String userId) {
		
		this.chat_userService.forbid(userId);
		return jsonView(true, "禁言成功");
	}
	
	/**
	 * 解禁
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "release", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(String userId) {
		
		this.chat_userService.release(userId);
		
		return jsonView(true, "解禁成功");
	}
}
