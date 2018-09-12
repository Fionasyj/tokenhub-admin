package com.yinzhi.platform.module.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhi.platform.core.BaseController;
import com.yinzhi.platform.entity.system.Menu;
import com.yinzhi.platform.module.system.service.IMenuService;


@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController{
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/getMenuList")
	@ResponseBody
	public List<Map<String, Object>> getMenuList(String id) {
		if(null == id || "".equals(id)){
			id = "0";
		}
		return this.menuService.getMenuListByPid(id);
	}
	
	/**
	 * 下拉树列表，显示根节点“系统菜单”
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMenuComboTreeList")
	@ResponseBody
	public List<Map<String, Object>> getMenuComboTreeList(String id) {
		if(null == id || "".equals(id)){
			id = "-1";
		}
		return this.menuService.getMenuListByPid(id);
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveMenu(Menu menu) {
		this.menuService.saveMenu(menu);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(Model model, String menuId) {
		model.addAttribute("menu", this.menuService.getMenu(menuId));
		return "/system/menu/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> Menu(Menu menu) {

		this.menuService.updateMenu(menu);
		
		return jsonView(true, SAVE_SUCCESS);
	}

	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeMenu(String menuId) {
		this.menuService.removeMenu(menuId);
		return jsonView(true, REMOVE_SUCCESS);
	}
}
