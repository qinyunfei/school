package sgg.qin.framework.shiro.realm;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import sgg.qin.domain.sys.User;
import sgg.qin.service.UserService;
import sgg.qin.util.PageData;

/**
 * user:qin yunfei
 * date 2017-9-20
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userservice;

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		// 获取用户提交的用户名
		String username = (String) token.getPrincipal();
		PageData<String, Object> pd = new PageData<String, Object>();
		pd.put("username", username);
		// 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authenticationInfo=null;
		try {
			// 根据用户名获取用户
			User user = userservice.findByUserName(pd).getbean(User.class);
			if (user == null) {
				throw new UnknownAccountException();// 没找到帐号
			}
			if (Boolean.TRUE.equals(user.getLocked())) {
				throw new LockedAccountException(); // 帐号锁定
			}

			authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), // 用户名
					user.getPassword(), // 密码
					ByteSource.Util.bytes(user.getCredentialsSalt()), // salt=username+salt
					getName() // realm name
			);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return authenticationInfo;
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户名
        String username = (String)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        PageData<String, Object> pd=new PageData<String, Object>();
        pd.put("username", username);
        try {
    		//获取用户角色的set集合
		 Set<String> roles = userservice.findRoles(pd);
		 //将角色信息写入 authorizationInfo
		 authorizationInfo.setRoles(roles);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authorizationInfo;
	}

}
