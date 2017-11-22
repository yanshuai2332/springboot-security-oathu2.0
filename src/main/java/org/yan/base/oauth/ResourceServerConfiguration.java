package org.yan.base.oauth;

import org.yan.base.oauth.resources.CustomDefaultWebResponseExceptionTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private TokenStore tokenStore;

	@Autowired
	private OAuth2ClientPropertyConfiguration oAuth2ClientPropertyConfiguration;


	@Override
	public void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/api/v1/activitys/latest").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers(HttpMethod.GET,"/api/v1/activitys").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers("/api/v1/activityTypes").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers(HttpMethod.GET, "/api/v1/activitys/users").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES")
//			.antMatchers(HttpMethod.GET, "/users/test").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers("/api/v1/articles/**").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers("/api/v1/areas").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers("/api/v1/scores/products/**").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers("/api/v1/scores/rank/police/**").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
			.antMatchers("/api/v1/scores/rank/people/**").hasAnyAuthority("PERM_APP_POLICE", "PERM_APP_MASSES", "ROLE_ANONYMOUS")
//			.antMatchers("/manager/**").hasAnyAuthority("ROLE_MANAGER")
			.anyRequest().authenticated();
	}


	public DefaultTokenServices tokenServices() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setAccessTokenValiditySeconds(0);
		defaultTokenServices.setRefreshTokenValiditySeconds(0);
		defaultTokenServices.setTokenStore(tokenStore);
		return defaultTokenServices;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources
			.tokenStore(tokenStore)
			.tokenServices(tokenServices())
			.accessDeniedHandler(accessDeniedHandler())
			.authenticationEntryPoint(authenticationEntryPoint())
			.resourceId(oAuth2ClientPropertyConfiguration.getResourceId());
	}

	@Bean
	public WebResponseExceptionTranslator exceptionTranslator() {
		return new CustomDefaultWebResponseExceptionTranslator();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		final OAuth2AuthenticationEntryPoint entryPoint = new OAuth2AuthenticationEntryPoint();
		entryPoint.setExceptionTranslator(exceptionTranslator());
		return entryPoint;
	}

	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		final OAuth2AccessDeniedHandler handler = new OAuth2AccessDeniedHandler();
		handler.setExceptionTranslator(exceptionTranslator());
		return handler;
	}
}
