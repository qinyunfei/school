package sgg.qin.domain.sys;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户类
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id; //编号
    private String username; //用户名
    private String password; //密码
    private String salt; //加密密码的盐
    private Boolean locked=Boolean.FALSE;//
    
    
    
	public User() {
		super();
	}

	public User(String username, String password, Boolean locked) {
		super();
		this.username = username;
		this.password = password;
		this.locked = locked;
	}






	public User(int id, String username, String password, Boolean locked) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.locked = locked;
	}

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



	public String getSalt() {
		return salt;
	}



	public void setSalt(String salt) {
		this.salt = salt;
	}



	public Boolean getLocked() {
		return locked;
	}



	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	  public String getCredentialsSalt() {
	        return username + salt;
	    }



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", salt=" + salt + ", locked="
				+ locked + "]";
	}
    

   
}
