package org.yan.base.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yan.user.User;
import org.yan.user.repository.UserMapper;

/**
 * Created by yanshuai on 17/5/16.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {


	@Autowired
	private UserMapper userMapper;

	@Override
	public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {

		User user = new User(phone,Boolean.FALSE);
		user = userMapper.selectOne(user);

		if (user == null) {
			throw new UsernameNotFoundException("手机号为[" + phone + "] 不存在或状态不合法");
		}

		return new CustomUserDetails(user);
	}
}
