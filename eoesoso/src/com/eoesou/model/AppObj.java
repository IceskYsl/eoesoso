package com.eoesou.model;

public class AppObj {
	private int id;//应用ID
	private String name;//应用名称
	private int downloadCount;//下载次数
	private int size;//大小
	private String icon;//图标
	private String cname;//类型，如：系统工具
	private String url;//下载地址
	private String updatedAt;//更新日期
	private String vName;//版本号
	private String desc;//软件描述
	private String[] pic;//描述图片
	private String[] spic;//描述图片缩略图
	public AppObj() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AppObj(int id) {
		super();
		this.id = id;
	}
	public AppObj(int id, String name, int downloadCount, int size,
			String icon, String cname, String url, String updatedAt,
			String vName, String desc, String[] pic, String[] spic) {
		super();
		this.id = id;
		this.name = name;
		this.downloadCount = downloadCount;
		this.size = size;
		this.icon = icon;
		this.cname = cname;
		this.url = url;
		this.updatedAt = updatedAt;
		this.vName = vName;
		this.desc = desc;
		this.pic = pic;
		this.spic = spic;
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
	public int getDownloadCount() {
		return downloadCount;
	}
	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getvName() {
		return vName;
	}
	public void setvName(String vName) {
		this.vName = vName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String[] getPic() {
		return pic;
	}
	public void setPic(String[] pic) {
		this.pic = pic;
	}
	public String[] getSpic() {
		return spic;
	}
	public void setSpic(String[] spic) {
		this.spic = spic;
	}
	
	
	
}
