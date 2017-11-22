package org.yan.base.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.yan.user.User;
import org.yan.user.UserTypeEnum;

import java.util.Collection;

/**
 * Created by yanshuai on 17/5/17.
 * security用户类 通过此类对用户绑定权限
 */
public class CustomUserDetails implements UserDetails {

	private User user;

	public CustomUserDetails(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = "";
		if (user.getType() == UserTypeEnum.POLICE.value()) {
			role = "PERM_APP_POLICE";
		} else if (user.getType() == UserTypeEnum.MASSES.value()) {
			role = "PERM_APP_MASSES";
		}
		return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getMobile();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
