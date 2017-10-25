package sgg.qin.framework.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sgg.qin.domain.sys.User;


/**
 * 
 * 类名称：shiro密码工具类
 * 
 * @author xiaoqin 修改时间：2017年9月17日
 * @version 1.0
 */

@Component
public class PasswordHelper {

	@Autowired
	private HashedCredentialsMatcher credentialsMatcher; // 获取shiro的凭证匹配器

	// 随机数字生成器
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	// 加密策略
	// @Value("${password.algorithmName}") 可以考虑使用配置文件
	private String algorithmName = "md5";
	// 加密次数
	// @Value("${password.hashIterations}") 可以考虑使用配置文件
	private int hashIterations = 2;

	// 设置用户的密码和盐
	public void encryptPassword(User user) {
		if (credentialsMatcher != null) {
			algorithmName = credentialsMatcher.getHashAlgorithmName();
			hashIterations = credentialsMatcher.getHashIterations();
		}
		// 生成16进制的随机数做为盐
		String salt = randomNumberGenerator.nextBytes().toHex();
		user.setSalt(salt);
		String newPassword = new SimpleHash(algorithmName, user.getPassword(),
				ByteSource.Util.bytes(user.getCredentialsSalt()), hashIterations).toHex();

		user.setPassword(newPassword);

	}
	
	
	
	// 加密密码
	public String[] encryptPassword(String username,String password) {
		if (credentialsMatcher != null) {
			algorithmName = credentialsMatcher.getHashAlgorithmName();
			hashIterations = credentialsMatcher.getHashIterations();
		}
		// 生成16进制的随机数做为盐
		String salt = randomNumberGenerator.nextBytes().toHex();
		String newPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(username+salt), hashIterations).toHex();
		return new String[] {newPassword,salt};

	}
	
	//根据用户名 盐 密码获取对应密码
	public String encryptPassword(String username,String password,String salt) {
		if (credentialsMatcher != null) {
			algorithmName = credentialsMatcher.getHashAlgorithmName();
			hashIterations = credentialsMatcher.getHashIterations();
		}

		String newPassword = new SimpleHash(algorithmName, password,
				ByteSource.Util.bytes(username+salt), hashIterations).toHex();
		return newPassword;

	}

}
