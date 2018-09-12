package com.yinzhi.platform.module.system.controller;

import java.util.Date;
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
import com.yinzhi.platform.entity.system.Resource;
import com.yinzhi.platform.entity.system.User;
import com.yinzhi.platform.global.UserUtil;
import com.yinzhi.platform.module.system.service.IDeptService;
import com.yinzhi.platform.module.system.service.IUserService;


@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	
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
		User user = UserUtil.getUser(request);
		Dept dept = this.deptService.getDept(user.getDeptId());
		model.addAttribute("dept", dept);
		return "/system/user/index";
	}
	
	/**
	 * 用户列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public Map<String, Object> getPage(User user, HttpServletRequest request) {
		String deptId = user.getDeptId();
		if(user.getDeptId() == null || "".equals(user.getDeptId())){
			deptId = UserUtil.getUser(request).getDeptId();
		}
		user.setDeptIds(this.deptService.getDeptIds(deptId));
		return this.userService.getPage(user, getStart(request), getLimit(request));
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
		
		User user = UserUtil.getUser(request);
		Dept dept = this.deptService.getDept(user.getDeptId());
		model.addAttribute("dept", dept);
		
		model.addAttribute("user", this.userService.getUser(userId));
		
		return "/system/user/edit";
	}
	
	/**
	 * 授权角色树列表
	 * @param userId
	 * @return
	 */
	@RequestMapping("/getUserRoleCheckList")
	@ResponseBody
	public List<Map<String, Object>> getUserRoleCheckList(String userId) {
		return this.userService.getUserRoleCheckList(userId);
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
		return "/system/user/add";
	}
	
	/**
	 * 保存用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> saveUser(User user) {
		user.setId(AppUtil.getUUID());
		user.setCtime(new Date());
		this.userService.saveUser(user);
		
		return jsonView(true, SAVE_SUCCESS);
	}
	
	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> editUser(User user) {
		
		this.userService.updateUser(user);
		
		return jsonView(true, SAVE_SUCCESS);
	}
	
	/**
	 * 用户授权
	 * @param userId
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "acc", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> userAcc(String userId, String roleIds, String roleNames) {
		if(null == userId || "".equals(userId)){
			throw new RuntimeException(AppUtil.getExMsg("用户参数无效！"));
		}
		if(null == roleIds){
			throw new RuntimeException(AppUtil.getExMsg("参数无效！"));
		}
		
		this.userService.saveUserRole(userId, roleIds, roleNames);
		
		return jsonView(true, SAVE_SUCCESS);
	}
	
	/**
	 * 删除用户
	 * @param userId
	 * @return
	 
	@RequestMapping(value = "remove", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> removeUser(String userId) {

		this.userService.removeUser(userId);
		
		return jsonView(true, REMOVE_SUCCESS);
	}
	*/
	
	
	/**
	 * 用户登陆
	 * @param request
	 * @param account
	 * @param pass
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, String account, String pass, String code) {
		String scode = (String)request.getSession().getAttribute("code");
		if(scode == null || "".equals("code")){
			throw new RuntimeException(AppUtil.getExMsg("验证码已过期，请刷新后重试!"));
		}
		
		if(!scode.equals(code)){
			throw new RuntimeException(AppUtil.getExMsg("验证码输入错误!"));
		}
		
		User user = this.userService.userLogin(account, pass);

		List<Resource> resourceList = this.userService.getUserResourceList(user.getId());
		
		request.getSession().setAttribute("USER", user);
		request.getSession().setAttribute("USERRES", resourceList);
				
		return jsonView(true, "登陆成功！");
	}
	
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/system/user/login";
	}
	
	
	/**
	 * 修改密码
	 * @return
	 */
	@RequestMapping(value = "pass", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> pass(HttpServletRequest request, String pass, String passa, String passb) {
		
		User user = UserUtil.getUser(request);
		
		
		if(pass == null || "".equals(pass)){
			throw new RuntimeException(AppUtil.getExMsg("请输入原密码！"));
		}
		
		if(!user.getPass().equals(AppUtil.md5(pass))){
			throw new RuntimeException(AppUtil.getExMsg("原密码不正确！"));
		}
		
		if(passa == null || "".equals(passa) || passb == null || "".equals(passb)){
			throw new RuntimeException(AppUtil.getExMsg("新密码输入不正确！"));
		}
		
		if(!passa.equals(passb)){
			throw new RuntimeException(AppUtil.getExMsg("输入的两次新密码不相同！"));
		}
		
		this.userService.updateUserPass(user.getId(), passb);
		
		return jsonView(true, SAVE_SUCCESS);
	}
	@RequestMapping("/getLoignTypeUserList")
	@ResponseBody
	public List<User> getDeptList(String loingType) {
 		return this.userService.getLoignTypeUserList("1");
	}
}
