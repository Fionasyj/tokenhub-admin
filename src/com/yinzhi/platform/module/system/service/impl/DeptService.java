package com.yinzhi.platform.module.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yinzhi.platform.core.AppUtil;
import com.yinzhi.platform.entity.system.Dept;
import com.yinzhi.platform.module.system.dao.DeptDao;
import com.yinzhi.platform.module.system.service.IDeptService;

@Service
public class DeptService implements IDeptService {

	@Autowired
	private DeptDao deptDao;

	public Dept getDept(String deptId) {
				
		return this.deptDao.getDept(deptId);
	}

	public List<Map<String, Object>> getDeptTreeList(Dept dept) {
		return this.deptDao.getDeptTreeList(dept.getId());
	}
	@Override
	public List<Map<String, Object>> getDeptTreeLists(Dept dept) {
		return this.deptDao.getDeptTreeLists(dept.getId());
	}
	public void saveDept(Dept dept) {
		dept.setLeaf(1);
		this.deptDao.saveDept(dept);
		

		Dept pDept = new Dept();
		pDept.setId(dept.getPid());
		pDept.setLeaf(0);
		pDept.setState("closed");
		this.deptDao.updateDeptLeaf(pDept);
	}

	public void updateDept(Dept dept) {
		if(dept.getId() == null){
			throw new RuntimeException(AppUtil.getExMsg("参数不正确！"));
		}
		if(dept.getId().equals(dept.getPid())){
			throw new RuntimeException(AppUtil.getExMsg("上级部门不能和当前部门相同！"));
		}
		
		//获取更新前的部门数据
		Dept ydept = this.deptDao.getDept(dept.getId());
		
		this.deptDao.updateDept(dept);
		
		//修改父级部门为有子级状态
		Dept pDept = new Dept();
		pDept.setId(dept.getPid());
		pDept.setLeaf(0);
		pDept.setState("closed");
		this.deptDao.updateDeptLeaf(pDept);
		
		//如果原父级部门下面已经没有子级，将更新父级状态
		int count = this.deptDao.getDeptChildCount(ydept.getPid());
		if(count == 0){
			Dept ypDept = new Dept();
			ypDept.setId(ydept.getPid());
			ypDept.setLeaf(1);
			ypDept.setState("open");
			this.deptDao.updateDeptLeaf(ypDept);
		}
	}

	public void removeDept(String deptId) {
		Dept dept = this.deptDao.getDept(deptId);
		
		List<Map<String, Object>> list = this.deptDao.getDeptTreeList(dept.getId());
		if (list.size() > 0) {
			throw new RuntimeException(AppUtil.getExMsg("当前部门存在子级，不能直接删除！"));
		}

		try {
			this.deptDao.removeDept(deptId);
		} catch (Exception e) {
			throw new RuntimeException(AppUtil.getExMsg("系统异常，或者该数据已关联其他数据！"));
		}
		
		//如果原父级部门下面已经没有子级，将更新子级状态
		int count = this.deptDao.getDeptChildCount(dept.getPid());
		if(count == 0){
			Dept pDept = new Dept();
			pDept.setId(dept.getPid());
			pDept.setLeaf(1);
			pDept.setState("open");
			this.deptDao.updateDeptLeaf(pDept);
		}
	}

	public String getDeptIds(String deptId) {
		return this.deptDao.getDeptIds(deptId);
	}

	@Override
	public List<Dept> getDeptList() {
		return this.deptDao.getDeptList();
	}


}
