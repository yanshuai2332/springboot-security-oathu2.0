package org.yan.user;

/**
 * APP用户类型
 * Created by yanshuai on 17/5/12.
 */
public enum UserTypeEnum {

	POLICE(1, "民警"),
	MASSES(2, "群众");


	private Integer value;
	private String text;

	UserTypeEnum(Integer value, String text) {
		this.value = value;
		this.text = text;
	}

	public Integer value() {
		return value;
	}

	public String text() {
		return text;
	}

}
