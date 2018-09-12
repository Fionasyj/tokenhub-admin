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
import com.yinzhi.platform.entity.system.Role;
import com.yinzhi.platform.module.system.service.IRoleService;


@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController{
	@Autowired
	private IRoleService roleService;
	
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage(HttpServletRequest request) {
		return this.roleService.getPage(getStart(request), getLimit(request));
	}
	
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(Model model, String roleId) {
		model.addAttribute("role", this.roleService.getRole(roleId));
		return "/system/role/edit";
	}
	
	@RequestMapping("/getRoleMenuCheckList")
	@ResponseBody
	public List<Map<String, Object>> getRoleMenuCheckList(String roleId, String id) {
		if(id == null || id.equals("")){
			id = "0";
		}
		return this.roleService.getRoleMenuCheckList(roleId, id);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUser(Role role) {
		role.setId(AppUtil.getUUID());
		this.roleService.saveRole(role);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editRole(Role role) {

		this.roleService.updateRole(role);
		
		return jsonView(true, SAVE_SUCCESS);
	}
	
	@RequestMapping(value = "set", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> menuSet(String roleId, String menuIds) {
		if(null == roleId || "".equals(roleId)){
			throw new RuntimeException(AppUtil.getExMsg("角色参数无效！"));
		}
		if(null == menuIds){
			throw new RuntimeException(AppUtil.getExMsg("参数无效！"));
		}
		
		this.roleService.saveRoleMenu(roleId, menuIds);
		
		return jsonView(true, SAVE_SUCCESS);
	}

	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeRole(String roleId) {
		this.roleService.removeRole(roleId);
		return jsonView(true, REMOVE_SUCCESS);
	}
}
