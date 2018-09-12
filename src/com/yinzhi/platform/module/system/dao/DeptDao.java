package com.yinzhi.platform.module.system.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yinzhi.platform.core.BaseDao;
import com.yinzhi.platform.entity.system.Dept;

@Repository
public class DeptDao extends BaseDao {
	
	
	/**
	 * 部门实体数据
	 * @param deptId 部门ID
	 * @return
	 */
	public Dept getDept(String id){
		return this.get(id, Dept.class);
	}
	
	/**
	 * 返回所有部门
	 * @return
	 */
	public List<Dept> getDeptList(){
		String sql = "select id,name,pid,leaf,state from s_dept ";
		return this.getList(sql, Dept.class);
	}
	
	/**
	 * 返回子部门数据
	 * @param deptId
	 * @return
	 */
	public List<Dept> getDeptList(String deptId){
		String sql = "select id,name,pid,leaf,state from s_dept where pid=?";
		Object[] args = new Object[]{
			deptId
		};
		return this.getList(sql, args, Dept.class);
	}
	
	/**
	 * 返回下级部门数量
	 * @param deptId
	 * @return
	 */
	public int getDeptChildCount(String deptId){
		String sql = "select count(0) from s_dept where pid=?";
		Object[] args = new Object[]{
			deptId
		};
		return this.queryForInt(sql, args);
	}
	/**
	 * 返回子部门树结构数据
	 * @param dept
	 * @return
	 */
	public List<Map<String, Object>> getDeptTreeList(String deptId){
		String sql = "select id,name text,pid,leaf,'1' expanded,'icon-012' iconCls, state from s_dept where pid=? order by name";
		Object[] args = new Object[]{
			deptId
		};
		return this.queryForList(sql, args);
	}
	/**
	 * 返回子部门树结构数据,去掉图标
	 * @param dept
	 * @return
	 */
	public List<Map<String, Object>> getDeptTreeLists(String deptId){
		String sql = "select id,name text,pid,leaf,'1' expanded, state from s_dept where pid=? order by name";
		Object[] args = new Object[]{
			deptId
		};
		return this.queryForList(sql, args);
	}
	/**
	 * 保存部门
	 * @param dept
	 */
	public void saveDept(Dept dept){
		this.insert(dept);
	}
	
	/**
	 * 修改部门
	 * @param dept
	 */
	public void updateDept(Dept dept){
		this.update(dept);
	}
	
	/**
	 * 修改部门是否有子级状态
	 * @param dept
	 */
	public void updateDeptLeaf(Dept dept){
		String sql = "update s_dept set leaf=?, state=? where id=?";
		Object[] args = new Object[] {dept.getLeaf(),dept.getState(), dept.getId() };

		this.update(sql, args);
	}
	
	/**
	 * 删除部门
	 * @param deptId
	 */
	public void removeDept(String deptId){
		String sql = "delete from s_dept where id=?";
		Object[] args = new Object[]{ deptId };
		this.update(sql, args);
	}
	
	/**
	 * 背归查询子部门
	 * @param deptId 部门ID
	 * @param list 空List
	 * @return
	 */
	public List<String> getDeptList(Dept dept, List<String> list){
		list.add(dept.getId());
		if("closed".equals(dept.getState())){
			List<Dept> pList = getDeptList(dept.getId());
			for (Dept d : pList) {
				getDeptList(d, list);
			}
		}
		return list;
	}
	
	/**
	 * 返回部门ID字符串， '1','2','3','4'
	 * @param deptId
	 * @return
	 */
	public String getDeptIds(String deptId){
		List<String> list = new ArrayList<String>();
		Dept dept = new Dept();
		dept.setId(deptId);
		dept.setState("closed");
		
		list = getDeptList(dept, list);
		StringBuffer sb = new StringBuffer();
		
		for (String id : list) {
			if(sb.length() == 0){
				sb.append("'").append(id).append("'");
			}else{
				sb.append(",'").append(id).append("'");
			}
		}
		
		return sb.toString();
	}
}
