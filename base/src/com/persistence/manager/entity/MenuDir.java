package com.persistence.manager.entity;

import java.util.List;

public class MenuDir {

	private Integer id;
	private String name;
	private Integer seq;
	private String icon;
	private List<Menu> menuLs;
	
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
	public List<Menu> getMenuLs() {
		return menuLs;
	}
	public void setMenuLs(List<Menu> menuLs) {
		this.menuLs = menuLs;
	}
	
	
}
