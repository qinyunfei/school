package sgg.qin.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

public class Dept implements Serializable {

	private static final long serialVersionUID = 5060188573418650105L;

	public Dept() {
		super();
	}
	
	public Dept(String dname, Date creatData) {
		super();
		this.dname = dname;
		this.creatData = creatData;
	}

	private Integer deptno;
	
	//有很多数据验证注解请百度
    @NotNull(message = "用户名不能为空")  
    @Length(min=5, max=20, message="用户名长度必须在5-20之间")  
    @Pattern(regexp = "^[a-zA-Z_]\\w{4,19}$", message = "用户名必须以字母下划线开头，可由字母数字下划线组成")  
	private String dname;
	
    @Past(message="没出生都会上网录信息") //数据验证  日期必须是一个过去的时间
	@DateTimeFormat(pattern="yyyy-MM-dd")//日期格式化(设置页面提交数据的格式) 有很多格式化注解请百度
	private Date creatData;
	
	
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}

	public Date getCreatData() {
		return creatData;
	}


	public void setCreatData(Date creatData) {
		this.creatData = creatData;
	}
	
	@Override
	public String toString() {
		return "Dept [deptno=" + deptno + ", dname=" + dname + ", creatData=" + creatData + "]";
	}



	

}
