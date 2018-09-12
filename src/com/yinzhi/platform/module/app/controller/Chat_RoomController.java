package com.yinzhi.platform.module.app.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yinzhi.platform.core.AppUtil;
import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.app.Chat_Room;
import com.yinzhi.platform.entity.system.Dept;
import com.yinzhi.platform.entity.system.User;
import com.yinzhi.platform.global.UserUtil;
import com.yinzhi.platform.module.app.service.IChat_RoomService;
import com.yinzhi.platform.module.system.service.IDeptService;


@Controller
@RequestMapping("/app/chat_room")
public class Chat_RoomController extends BaseController {
	@Autowired
	private IChat_RoomService chat_roomService;
	
	@Autowired
	private IDeptService deptService;
	
	/**
	 * 显示首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		return "/app/chat_room/index";
	}
	
	/**
	 * 聊天室列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage( Chat_Room chat_room, HttpServletRequest request) {
		return this.chat_roomService.getPage(chat_room, getStart(request), getLimit(request));
	}
	
	/**
	 * 显示修改页面
	 * @param chat_roomId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(String chat_roomId, Model model, HttpServletRequest request) {
		model.addAttribute("chat_room", this.chat_roomService.getChat_Room(chat_roomId));
		return "/app/chat_room/edit";
	}
	
	/**
	 * 查询聊天室列表
	 * @return
	 */
	@RequestMapping("/getChat_RoomList")
	@ResponseBody
	public List<Chat_Room> getChat_RoomList() {
		List<Chat_Room> list = this.chat_roomService.getChat_RoomList();
		return list;
	}
	
	/**
	 * 显示添加公告页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/addNotice", method = RequestMethod.GET)
	protected String addNotice(Model model, String chat_roomId,HttpServletRequest request) {
		model.addAttribute("chat_roomId", chat_roomId);
		return "/app/chat_room/addNotice";
	}
	
	/**
	 * 保存公告
	 * @param chat_Room
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "addNotice", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> addNotice(Chat_Room chat_Room) throws IllegalStateException, IOException {
		this.chat_roomService.addNotice(chat_Room);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	/**
	 * 显示添加页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/view/add", method = RequestMethod.GET)
	protected String add(Model model, HttpServletRequest request) {
		User user = UserUtil.getUser(request);
		Dept dept = this.deptService.getDept(user.getDeptId());
		model.addAttribute("dept", dept);
		return "/app/chat_room/add";
	}
	
	/**
	 * 保存聊天室
	 * @param file
	 * @param chat_Room
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveChat_Room(@RequestParam(value = "file", required = true) MultipartFile file, Chat_Room chat_Room) throws IllegalStateException, IOException {
		chat_Room.setCreated_at(new Date().getTime());
		 String pic_path = "D:\\admin\\tokenhub-admin\\WebContent\\images\\";
	        String fileName = file.getOriginalFilename();
	        File targetFile = new File(pic_path, fileName);
	        if (!targetFile.exists()) {
	            targetFile.mkdirs();
	        }
	        file.transferTo(targetFile);
	        String fileUrl = fileName;
	        chat_Room.setAvatar(fileUrl);
	        chat_Room.setStatus(0);
		this.chat_roomService.saveChat_Room(chat_Room);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	/**
	 * 修改聊天室
	 * @param file
	 * @param chat_Room
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editChat_Room(@RequestParam(value = "file", required = true) MultipartFile file, Chat_Room chat_Room) throws IllegalStateException, IOException {
		 String pic_path = "D:\\admin\\tokenhub-admin\\WebContent\\images\\";
	        String fileName = file.getOriginalFilename();
	        File targetFile = new File(pic_path, fileName);
	        if (!targetFile.exists()) {
	            targetFile.mkdirs();
	            file.transferTo(targetFile);
	        }
	        String fileUrl = fileName;
	        if(fileUrl.equals("")) {
	        	String avatar = chat_Room.getAvatar();
	        	chat_Room.setAvatar(chat_Room.getAvatar());
	        }else {
	        	chat_Room.setAvatar(fileUrl);
	        }
	        this.chat_roomService.updateChat_Room(chat_Room);
		return jsonView(true, UPDATE_SUCCESS);
	}
	
	
	/**
	 * 显示聊天室用户列表
	 * @param chat_roomId
	 * @return
	 */
	@RequestMapping(value = "toChatUserIndex", method = RequestMethod.GET)
	protected String toChatUserIndex(Model model, String room_id) {
		model.addAttribute("chat_room", room_id);
		return "/app/chat_user/index";
	}
	
	/**
	 * 禁用
	 * @param chat_roomId
	 * @return
	 */
	@RequestMapping(value = "forbidden", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> forbidden(String chat_roomId) {
		
		this.chat_roomService.forbidden(chat_roomId);
		
		return jsonView(true, "禁用信息成功");
	}
	
	/**
	 * 启用
	 * @param chat_roomId
	 * @return
	 */
	@RequestMapping(value = "release", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> release(String chat_roomId) {
		
		this.chat_roomService.release(chat_roomId);
		
		return jsonView(true, "启用信息成功");
	}
}
