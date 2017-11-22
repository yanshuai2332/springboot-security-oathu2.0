/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.yan.base.oauth.resources;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.yan.base.constants.ApplicationResponseEnum;
import org.yan.base.responsedata.OAuth2ResponseData;
import org.yan.base.responsedata.ResponseData;
import org.yan.base.responsedata.ResponseDataBuilder;

/**
 * @author yanshuai
 */
public class CustomDefaultWebResponseExceptionTranslator implements WebResponseExceptionTranslator {

	private DefaultWebResponseExceptionTranslator defaultWebResponseExceptionTranslator = new DefaultWebResponseExceptionTranslator();

	@Override
	public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
		ResponseEntity<OAuth2Exception> responseEntity = defaultWebResponseExceptionTranslator.translate(e);
		OAuth2Exception body = responseEntity.getBody();
		HttpHeaders headers = new HttpHeaders();
		headers.setAll(responseEntity.getHeaders().toSingleValueMap());

		ResponseData responseData = ResponseDataBuilder.newBuilder()
			.base(ApplicationResponseEnum.TOKEN_ERROR)
			.build();

		String message = ApplicationResponseEnum.TOKEN_ERROR.text();
		if (responseEntity.getStatusCode() == HttpStatus.FORBIDDEN) {
			responseData = ResponseDataBuilder.newBuilder()
				.base(ApplicationResponseEnum.FORBIDDEN)
				.build();
			message = ApplicationResponseEnum.FORBIDDEN.text();
		}


		/**
		 * 在OAuth2ResponseData中增加error属性, 是为了统一返回信息
		 * @see org.springframework.security.oauth2.provider.token.RemoteTokenServices
		 * 查看以上源码Line109, 如果token不合法, 返回数据结构中需包含error属性
		 */

		OAuth2ResponseData oAuth2ResponseData = new OAuth2ResponseData(responseData, body.getOAuth2ErrorCode());
		CustomOAuth2Exception customOAuth2Exception = new CustomOAuth2Exception(message, oAuth2ResponseData);
		return new ResponseEntity<>(customOAuth2Exception, headers, responseEntity.getStatusCode());
	}
}
