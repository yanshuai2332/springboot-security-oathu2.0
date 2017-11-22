package org.yan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.yan.base.oauth.OAuth2ClientPropertyConfiguration;

import java.util.Arrays;

/**
 * Created by yanshuai on 17/5/16.
 * oauth认证客户端
 */
@Configuration
@EnableOAuth2Client
@EnableConfigurationProperties(OAuth2ClientPropertyConfiguration.class)
public class OAuth2Client {

	@Autowired
	private OAuth2ClientPropertyConfiguration oAuth2ClientPropertyConfiguration;

	@Bean
	public ResourceOwnerPasswordResourceDetails resource() {
		ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
		details.setClientId(oAuth2ClientPropertyConfiguration.getClientId());
		details.setClientSecret(oAuth2ClientPropertyConfiguration.getClientSecret());
		details.setAccessTokenUri(oAuth2ClientPropertyConfiguration.getAccessTokenUri());
		details.setId(oAuth2ClientPropertyConfiguration.getResourceId());
		details.setAuthenticationScheme(AuthenticationScheme.form);
		return details;
	}

	@Bean
	@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
	public OAuth2RestTemplate oauth2RestTemplate() {
		OAuth2RestTemplate template = new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext());
		AccessTokenProvider accessTokenProvider = new AccessTokenProviderChain(
			Arrays.<AccessTokenProvider>asList(
				new ImplicitAccessTokenProvider(),
				new ResourceOwnerPasswordAccessTokenProvider(),
				new ClientCredentialsAccessTokenProvider())
		);
		template.setAccessTokenProvider(accessTokenProvider);
		template.setRetryBadAccessTokens(false);
		return template;
	}


}
