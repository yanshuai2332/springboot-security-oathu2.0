package org.yan.base.oauth.resources;

import org.yan.base.responsedata.OAuth2ResponseData;
import lombok.Data;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * Created by yanshuai on 17/5/17.
 */
@com.fasterxml.jackson.databind.annotation.JsonSerialize(using = CustomOAuth2ExceptionJackson2Serializer.class)
@com.fasterxml.jackson.databind.annotation.JsonDeserialize(using = CustomOAuth2ExceptionJackson2Deserializer.class)
@Data
public class CustomOAuth2Exception extends OAuth2Exception {

	private final OAuth2ResponseData responseData;

	public CustomOAuth2Exception(String message, OAuth2ResponseData responseData) {
		super(message);
		this.responseData = responseData;
	}

}
