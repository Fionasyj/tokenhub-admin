package com.yinzhi.platform.entity.system;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("s_dept")
public class Dept  extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	@Id
	private String id;
	/**
	 * 原始系统id
	 */
	@Column
	private String oldId;
	/**
	 * 分级码
	 */
	@Column
	private String gradeNum;
	/**
	 * 机构名称
	 */
	@Column
	private String name;
	/**
	 * 上级机构ID
	 */
	@Column
	private String pid;
	/**
	 * 是否叶节点:0根/1叶
	 */
	@Column
	private Integer leaf;
	/**
	 * 展开状态
	 */
	@Column
	private String state;
	/**
	 * 触摸查询系统:0否/1是
	 */
	@Column
	private String touchQuerySystem;
	/**
	 * 短信群发系统:0否/1是
	 */
	@Column
	private String massSystem;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOldId() {
		return oldId;
	}
	public void setOldId(String oldId) {
		this.oldId = oldId;
	}
	public String getGradeNum() {
		return gradeNum;
	}
	public void setGradeNum(String gradeNum) {
		this.gradeNum = gradeNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public Integer getLeaf() {
		return leaf;
	}
	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTouchQuerySystem() {
		return touchQuerySystem;
	}
	public void setTouchQuerySystem(String touchQuerySystem) {
		this.touchQuerySystem = touchQuerySystem;
	}
	public String getMassSystem() {
		return massSystem;
	}
	public void setMassSystem(String massSystem) {
		this.massSystem = massSystem;
	}
	 
	
}
