package com.persistence.manager.entity;

public class Menu {
	private Integer id;
	private String name;
	private String url;
	private Integer status;
	private Integer seq;
	private String icon;
	private Integer menuDirId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getMenuDirId() {
		return menuDirId;
	}
	public void setMenuDirId(Integer menuDirId) {
		this.menuDirId = menuDirId;
	}
	
}
