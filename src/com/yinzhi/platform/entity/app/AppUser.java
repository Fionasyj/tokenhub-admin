package com.yinzhi.platform.entity.app;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("user")
public class AppUser extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@Column
	private String nick;
	@Column
	private String mobile;
	@Column
	private String salt;
	@Column
	private String password;
	@Column
	private Long created_at;
	@Column
	private Long updated_at;
	@Column
	private Integer status;
	@Column
	private Integer source;
	@Column
	private String openid;
	@Column
	private String avatar;
	@Column
	private Integer verify_type;
	@Column
	private String email;
	@Column
	private String pay_password;
	@Column
	private String referral_code;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}
	public Long getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(Long updated_at) {
		this.updated_at = updated_at;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getVerify_type() {
		return verify_type;
	}
	public void setVerify_type(Integer verify_type) {
		this.verify_type = verify_type;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public String getReferral_code() {
		return referral_code;
	}
	public void setReferral_code(String referral_code) {
		this.referral_code = referral_code;
	}
	
	
	
	
}
