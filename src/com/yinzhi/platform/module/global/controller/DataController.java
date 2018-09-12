package com.yinzhi.platform.module.global.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/global/data")
public class DataController {
	
	
	@RequestMapping("/getUserStatusList")
	@ResponseBody
	public List<Map<String, Object>> getUserStatusList(String id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value", "0");
		map.put("text", "正常");
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("value", "1");
		map1.put("text", "停用");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("value", "2");
		map2.put("text", "注销");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		list.add(map1);
		list.add(map2);
		
		return list;
	}
	
	
	@RequestMapping("/getMenuTypeList")
	@ResponseBody
	public List<Map<String, Object>> getMenuTypeList(String id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("value", "1");
		map.put("text", "模块");
		
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("value", "2");
		map1.put("text", "菜单");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("value", "3");
		map2.put("text", "操作");
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		list.add(map1);
		list.add(map2);
		
		return list;
	}
	
	
}
