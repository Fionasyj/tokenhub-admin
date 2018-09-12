package com.yinzhi.platform.module.system.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yinzhi.platform.entity.system.User;
import com.yinzhi.platform.global.UserUtil;
import com.yinzhi.platform.module.system.service.IAppService;
import com.yinzhi.platform.module.system.service.IMenuService;


@Controller
public class AppController {
	@Autowired
	private IAppService appService;
	
	@Autowired
	private IMenuService menuService;
	
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		User user = UserUtil.getUser(request);
		
		List<Map<String, Object>> list = this.menuService.getUserMenuList(user.getId(),"0");
		
		model.addAttribute("moduleList", list);
		//model.addAttribute("jsList", getJs(request));
		model.addAttribute("user", user);
		return "index";
	}
	
	@RequestMapping("/main")
	public String main() {
		
		return "main";
	}
	
	
	@RequestMapping("/getMenuList")
	@ResponseBody
	public List<Map<String, Object>> getMenuList(String id, String pid, HttpServletRequest request) {
		if(id == null && pid != null){
			id = pid;
		}
		User user = UserUtil.getUser(request);
		return this.menuService.getUserMenuList(user.getId(), id);
	}
	
	
	
//	/**
//	 * 加载前台界面的js实体资源文件
//	 * @param request
//	 * @return
//	 */
//	public List<String> getJs(HttpServletRequest request){
//		String realPath = request.getSession().getServletContext().getRealPath("/resources/js/model/");
//		File file = new File(realPath);
//		
//		List<String> list = new ArrayList<String>();
//		
//		getJsList(file, list);
//		
//		return list;
//	}
	
	public void getJsList(File file, List<String> list) {
		if (file.isDirectory()) {
			File[] f = file.listFiles();
			for (int i = 0; i < f.length; i++) {
				this.getJsList(f[i], list);
			}
		}
		if (file.isFile()) {
			String filePath = file.getParent() + File.separator + file.getName();
			int index = filePath.indexOf("\\model\\");
			if(index < 0){
				index = filePath.indexOf("/model/");
			}
			
			String js = filePath.substring(index + 7, filePath.length()).replace("\\", "/");
			
			list.add(js);
		}
	}

}
