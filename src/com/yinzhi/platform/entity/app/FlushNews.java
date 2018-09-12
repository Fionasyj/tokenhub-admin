package com.yinzhi.platform.entity.app;

import com.yinzhi.platform.core.BaseEntity;
import com.yinzhi.platform.core.annotation.Column;
import com.yinzhi.platform.core.annotation.Id;
import com.yinzhi.platform.core.annotation.Table;

@Table("flushnews")
public class FlushNews extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id;
	@Column
	private String title;
	@Column
	private String content;
	@Column
	private String content_color;
	@Column
	private String url;
	@Column
	private Long news_timestamp;
	@Column
	private Long created_at;
	@Column
	private Long updated_at;
	@Column
	private Integer status;
	@Column
	private Integer source_media;
	@Column
	private String source_id;
	@Column
	private Integer push_times;
	public Integer getPush_times() {
		return push_times;
	}
	public void setPush_times(Integer push_times) {
		this.push_times = push_times;
	}
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getNews_timestamp() {
		return news_timestamp;
	}
	public void setNews_timestamp(Long news_timestamp) {
		this.news_timestamp = news_timestamp;
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
	public Integer getSource_media() {
		return source_media;
	}
	public void setSource_media(Integer source_media) {
		this.source_media = source_media;
	}
	public String getSource_id() {
		return source_id;
	}
	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}
	
	public String getContent_color() {
		return content_color;
	}
	public void setContent_color(String content_color) {
		this.content_color = content_color;
	}
	
}
