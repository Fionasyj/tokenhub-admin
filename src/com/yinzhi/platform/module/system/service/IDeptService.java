package com.yinzhi.platform.module.system.service;

import java.util.List;
import java.util.Map;

import com.yinzhi.platform.entity.system.Dept;


public interface IDeptService {
	
	/**
	 * 部门实体数据
	 * @param deptId 部门ID
	 * @return
	 */
	Dept getDept(String deptId);
	
	/**
	 * 保存部门
	 * @param dept
	 */
	void saveDept(Dept dept);
	
	/**
	 * 修改部门
	 * @param dept
	 */
	void updateDept(Dept dept);
	
	/**
	 * 删除部门
	 * @param deptId
	 */
	void removeDept(String deptId);
	
	/**
	 * 返回子部门树结构数据
	 * @param dept
	 * @return
	 */
	List<Map<String, Object>> getDeptTreeList(Dept dept);
	/**
	 * 返回子部门树结构数据,去掉图标
	 * @param dept
	 * @return
	 */
	List<Map<String, Object>> getDeptTreeLists(Dept dept);
	
	/**
	 * 返回部门ID字符串， '1','2','3','4'
	 * @param deptId
	 * @return
	 */
	String getDeptIds(String deptId);
	/**
	 * 返回所有部门
	 * @return
	 */
	List<Dept> getDeptList();
}
