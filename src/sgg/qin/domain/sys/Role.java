package sgg.qin.domain.sys;

import java.util.List;

/*
 * 角色类
 * 
 * */
public class Role {
	private Long id; // 编号
	private String role; //角色标识 程序中判断使用,如"admin"
	private String description; //角色描述,UI界面显示使用
	private Boolean available = Boolean.FALSE;	//是否可用
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	
	
	

}
