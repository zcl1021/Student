package com.hbsi.bean;

public class User {
    private int id;
    private String username;
    private String password;
    private String usertypes;
    private String del_status;
    private String ctime;
    private String deletetime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsertypes() {
		return usertypes;
	}
	public void setUsertypes(String usertypes) {
		this.usertypes = usertypes;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getDeletetime() {
		return deletetime;
	}
	public void setDeletetime(String deletetime) {
		this.deletetime = deletetime;
	}
    
}
