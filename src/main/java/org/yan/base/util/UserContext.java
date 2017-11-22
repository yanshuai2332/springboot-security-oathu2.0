package org.yan.base.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.yan.base.security.CustomUserDetails;
import org.yan.user.User;

/**
 * 获取当前用户相关信息
 * Created by yanshuai on 17/5/17.
 */
public class UserContext {

	private UserContext() {
	}

	public static CurrentUser getCurrentUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal == null || !(principal instanceof UserDetails)) {
			return null;
		}

		User authenticated = ((CustomUserDetails) principal).getUser();

		CurrentUser currentUser = new CurrentUser();
		currentUser.id = authenticated.getId();
		currentUser.areaId = authenticated.getAreaId();
		currentUser.mobile = authenticated.getMobile();
		currentUser.realname = authenticated.getRealname();
		currentUser.type = authenticated.getType();
		currentUser.policeCode = authenticated.getPoliceCode();
		return currentUser;
	}


	public static final class CurrentUser {
		private Long id;
		private String mobile;
		private Integer type;
		private String realname;
		private Long areaId;
		private String policeCode;


		public Long getId() {
			return id;
		}

		public String getMobile() {
			return mobile;
		}

		public Integer getType() {
			return type;
		}

		public String getRealname() {
			return realname;
		}

		public Long getAreaId() {
			return areaId;
		}

		public String getPoliceCode() {
			return policeCode;
		}

		@Override
		public String toString() {
			return "CurrentUser{" +
				"id=" + id +
				", mobile='" + mobile + '\'' +
				", type=" + type +
				", realname='" + realname + '\'' +
				", areaId=" + areaId +
				", policeCode='" + policeCode + '\'' +
				'}';
		}
	}
}
