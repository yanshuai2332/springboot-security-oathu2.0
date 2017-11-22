package org.yan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.yan.base.oauth.OAuth2ClientPropertyConfiguration;
import org.yan.user.repository.UserMapper;


/**
 * Created by yanshuai on 17/5/9.
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl  {


	@Autowired
	private UserMapper userMapper;
	@Autowired
	private OAuth2RestTemplate oAuth2RestTemplate;
	@Autowired
	private OAuth2ClientPropertyConfiguration oAuth2ClientPropertyConfiguration;

	private static final String USER_AVATAR_PATH = "avatars";

	public static final String REDIS_USER_SAVE_PREFIX = "USER:SAVE:";

	public static final String REDIS_USER_UPDATE_PREFIX = "USER:UPDATE:";

	private OAuth2AccessToken getAccessToken(String username, String password) {
		oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().add(oAuth2ClientPropertyConfiguration.getUsernameKey(), username);
		oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().add(oAuth2ClientPropertyConfiguration.getPasswordKey(), password);

		return oAuth2RestTemplate.getAccessToken();
	}

	public String signin(String mobile,String password){
		User user=new User();
		user.setMobile(mobile);
		user=userMapper.selectOne(user);
		if(user.getPassword().equals(password)){
			return getAccessToken(mobile, user.getPassword()).getValue();
		}
		else
			return "error";
	}
}
