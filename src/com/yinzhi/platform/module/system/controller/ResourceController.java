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
import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.module.system.service.IResourceService;


@Controller
@RequestMapping("/system/resource")
public class ResourceController extends BaseController{
	@Autowired
	private IResourceService resourceService;
	
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage(HttpServletRequest request) {
		return this.resourceService.getPage(getStart(request), getLimit(request));
	}
	
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUser(Resource resource) {
		resource.setId(AppUtil.getUUID());
		this.resourceService.saveResource(resource);
		return jsonView(true, SAVE_SUCCESS);
	}
	
	@RequestMapping(value = "/view/edit", method = RequestMethod.GET)
	protected String edit(Model model, String resourceId) {
		model.addAttribute("resource", this.resourceService.getResource(resourceId));
		return "/system/resource/edit";
	}
	
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editResource(Resource resource) {
		
		this.resourceService.updateResource(resource);
		
		return jsonView(true, SAVE_SUCCESS);
	}
	
	@RequestMapping("/getResourceList")
	@ResponseBody
	public List<Resource> getResourceList() {
		return this.resourceService.getResourceList();
	}

	
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeResource(String resourceId) {
		
		try {
			this.resourceService.removeResource(resourceId);
		} catch (Exception e) {
			throw new RuntimeException(AppUtil.getExMsg("系统异常，或者该数据已关联其他项目！"));
		}
		
		return jsonView(true, REMOVE_SUCCESS);
	}
}
