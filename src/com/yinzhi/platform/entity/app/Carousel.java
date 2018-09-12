package com.yinzhi.platform.entity.app;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("news")
public class Carousel extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private String pic_url;
	@Column
	private String url;
	@Column
	private Long created_at;
	@Column
	private Long updated_at;
	@Column
	private Integer status;
	@Column
	private Integer news_type;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPic_url() {
		return pic_url;
	}
	public void setPic_url(String pic_url) {
		this.pic_url = pic_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Integer getNews_type() {
		return news_type;
	}
	public void setNews_type(Integer news_type) {
		this.news_type = news_type;
	}

	
	
}
