package com.yinzhi.platform.entity.app;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("chat_user")
public class Chat_User extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@Column
	private Long user_id;
	@Column
	private String room;
	@Column
	private Long created_at;
	@Column
	private Long updated_at;
	@Column
	private Integer status;
	@Column
	private String nick;
	@Column
	private Long online_status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
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
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public Long getOnline_status() {
		return online_status;
	}
	public void setOnline_status(Long online_status) {
		this.online_status = online_status;
	}
	
	
	
}
