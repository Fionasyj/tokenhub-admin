package com.yinzhi.platform;

import net.sf.json.JSONObject;

public class Test {

	public static void main(String[] args) {
		String jsonStr = "{\"password\":\"123456\",\"username\":\"张三\"}";
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		System.out.println(jsonObj.toString());
	}

}
