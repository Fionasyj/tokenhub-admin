package com.yinzhi.platform.module.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhi.platform.core.AppUtil;
import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.system.Dept;
import com.yinzhi.platform.entity.system.User;
import com.yinzhi.platform.global.UserUtil;
import com.yinzhi.platform.module.system.service.IDeptService;


@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController{
	@Autowired
	private IDeptService deptService;
	
	@RequestMapping(value = "/view/index", method = RequestMethod.GET)
	protected String index(Model model, HttpServletRequest request) {
		
		User user = UserUtil.getUser(request);
		Dept dept = this.deptService.getDept(user.getDeptId());
		model.addAttribute("dept", dept);
		return "/system/dept/index";
	}
	
	
	@RequestMapping("/getDeptList")
	@ResponseBody
	public List<Map<String, Object>> getDeptList(Dept dept) {
		if(null == dept.getId() || "".equals(dept.getId())){
			dept.setId("0");
		}
		return this.deptService.getDeptTreeList(dept);
	}
	/**
	 * 获取没有图标的树列表
	 * @param dept
	 * @return
	 */
	@RequestMapping("/getDeptLists")
	@ResponseBody
	public List<Map<String, Object>> getDeptLists(Dept dept) {
		if(null == dept.getId() || "".equals(dept.getId())){
			dept.setId("0");
		}
		System.out.println("----"+dept.getPid());
		return this.deptService.getDeptTreeLists(dept);
	}
	
	@RequestMapping(value = "/view/add", method = RequestMethod.GET)
	protected String add(Model model, HttpServletRequest request) {
		User user = UserUtil.getUser(request);
		Dept dept = this.deptService.getDept(user.getDeptId());
		model.addAttribute("dept", dept);
		return "/system/dept/add";
	}
	
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveDept(Dept dept) {
		dept.setId(AppUtil.getUUID());
		this.deptService.saveDept(dept);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(String deptId, Model model, HttpServletRequest request) {
		
		model.addAttribute("dept", this.deptService.getDept(deptId));
		
		User user = UserUtil.getUser(request);
		Dept dept = this.deptService.getDept(user.getDeptId());
		model.addAttribute("pDept", dept);
		return "/system/dept/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editDept(Dept dept) {
		this.deptService.updateDept(dept);
		
		return jsonView(true, SAVE_SUCCESS);
	}

//	@RequestMapping("/getDeptList")
//	@ResponseBody
//	public List<Map<String, Object>> getDeptList() {
//		return this.deptService.getDeptList();
//	}

	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeDept(String deptId) {
		
		this.deptService.removeDept(deptId);
		
		return jsonView(true, REMOVE_SUCCESS);
	}
}
