package org.yan.base.constants;

import org.yan.base.responsedata.ResponseEnumInterface;

/**
 * 系统级别的错误码及信息
 * Created by yanshuai
 */
public enum ApplicationResponseEnum implements ResponseEnumInterface {

	INTERNAL_SERVER_ERROR("500", "服务器异常, 请稍后重试"),

	NOT_FOUND("404", "服务器资源不存在"),

	BAD_REQUEST("400", "请求参数不合法"),

	BAD_REQUEST_METHOD("405", "请求方法不支持"),

	TOKEN_ERROR("401", "不合法的token"),

	FORBIDDEN("403", "无权限"),

	UNSUPPORTED_MEDIA_TYPE("415", "Media Type不支持");

	private String value;

	private String text;

	ApplicationResponseEnum(String value, String text) {
		this.value = value;
		this.text = text;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public String text() {
		return text;
	}
}
