package org.yan.base.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.yan.base.oauth.resources.CustomDefaultWebResponseExceptionTranslator;
import org.yan.base.oauth.resources.CustomTokenEnhancer;

import javax.sql.DataSource;

/**
 * oauth认证服务端配置
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {


	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}


	@Override
	public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			.jdbc(dataSource);
//			.withClient("prevention_governance_client_id")
//			.secret("prevention_governance_client_secret")
//			.authorizedGrantTypes("password", "refresh_token")
//			.scopes("read", "write")
//			.authorities("ROLE_POLICE", "ROLE_MASSES")
//			.autoApprove(true)
//			.resourceIds("prevention_governance_resource")
//			.accessTokenValiditySeconds(0)
//			.refreshTokenValiditySeconds(0);

	}

	public DefaultTokenServices tokenServices() {
		final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		defaultTokenServices.setAccessTokenValiditySeconds(0);
		defaultTokenServices.setRefreshTokenValiditySeconds(0);
		defaultTokenServices.setTokenEnhancer(tokenEnhancer());
		return defaultTokenServices;
	}


	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.tokenServices(tokenServices())
			.exceptionTranslator(exceptionTranslator())
			.authenticationManager(authenticationManager);
	}

	@Bean
	public WebResponseExceptionTranslator exceptionTranslator() {
		return new CustomDefaultWebResponseExceptionTranslator();
	}

	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource);
	}


	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new CustomTokenEnhancer();
	}

}
