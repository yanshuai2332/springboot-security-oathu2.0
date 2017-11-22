package org.yan.base.oauth;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by yanshuai on 17/5/16.
 */
@ConfigurationProperties(prefix = "prevention.oauth2")
@Data
@NoArgsConstructor
public class OAuth2ClientPropertyConfiguration {

	private String clientId;

	private String clientSecret;

	private String accessTokenUri;

	private String resourceId;

	private String checkTokenUri;

	/**
	 * OAuth2客户端访问token的参数key
	 */
	private String usernameKey;

	/**
	 * OAuth2客户端访问token的参数key
	 */
	private String passwordKey;
}
