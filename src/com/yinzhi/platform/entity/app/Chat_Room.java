package com.yinzhi.platform.entity.app;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("chat_room")
public class Chat_Room extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	@Column
	private String room_id;
	@Column
	private String room_name;
	@Column
	private Long created_at;
	@Column
	private Long updated_at;
	@Column
	private Integer status;
	@Column
	private String description;
	@Column
	private String notice;
	@Column
	private String notice_url;
	@Column
	private String avatar;
	@Column
	private Integer weight;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoom_id() {
		return room_id;
	}
	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getNotice_url() {
		return notice_url;
	}
	public void setNotice_url(String notice_url) {
		this.notice_url = notice_url;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	
	
}
