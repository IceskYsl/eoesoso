package com.eoesou.model;

public class AppCategory {
	private int id;
	private String name;
	private String icon;
	public AppCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppCategory(int id, String name, String icon) {
		super();
		this.id = id;
		this.name = name;
		this.icon = icon;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
}
