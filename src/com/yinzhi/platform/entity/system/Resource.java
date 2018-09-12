package com.yinzhi.platform.entity.system;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("s_resource")
public class Resource extends BaseEntity{

	private static final long serialVersionUID = 1L;

	
	@Id
	private String id;
	@Column
	private String name;
	@Column
	private String url;
	@Column
	private String remark;
	@Column
	private Integer isLog;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsLog() {
		return isLog;
	}

	public void setIsLog(Integer isLog) {
		this.isLog = isLog;
	}

}
