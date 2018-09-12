package com.yinzhi.platform.entity.system;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;
/**
 * 小区信息
 * @author 杨金
 * @createTime 2014-2-26
 */
@Table("t_community")
public class Community extends BaseEntity{
 private static final long serialVersionUID = 1L;
 /**
  * 小区编号,主键，自动序列
  */
 @Id
 private long cmid;
 /**
  * 小区名称
  */
 @Column
 private String cmname;
 /**
  * 物业公司名称
  */
 @Column
 private String pname;
 /**
  * 物业公司电话
  */
 @Column
 private String phone;
 /**
  * 小区地址
  */
 @Column
 private String address;
 /**
  * 省市区县编号
  */
 @Column
 private long cid;
public long getCmid() {
	return cmid;
}
public void setCmid(long cmid) {
	this.cmid = cmid;
}
public String getCmname() {
	return cmname;
}
public void setCmname(String cmname) {
	this.cmname = cmname;
}
public String getPname() {
	return pname;
}
public void setPname(String pname) {
	this.pname = pname;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public long getCid() {
	return cid;
}
public void setCid(long cid) {
	this.cid = cid;
}
 
 
}
