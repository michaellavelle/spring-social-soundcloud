/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.soundcloud.connect;

import java.util.Map;

import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;

/**
* SoundCloud-specific extension of OAuth2Template
* @author Michael Lavelle
*/
public class SoundCloudOAuth2Template extends OAuth2Template {

	private String redirectUri;
	
	public SoundCloudOAuth2Template(String clientId, String clientSecret) {
		this(clientId, clientSecret, null);
	}

	public SoundCloudOAuth2Template(String clientId, String clientSecret,String redirectUri) {
		super(clientId, clientSecret, "https://soundcloud.com/connect", "https://api.soundcloud.com/oauth2/token");
		setUseParametersForClientAuthentication(true);
		this.redirectUri = redirectUri;
	}
	
	@Override
	protected AccessGrant createAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn, Map<String, Object> response) {
		return super.createAccessGrant(accessToken, scope, refreshToken, expiresIn, response);
	}
	
	
	@Override
	public String buildAuthenticateUrl(GrantType grantType, OAuth2Parameters parameters) {
		if (redirectUri != null) parameters.setRedirectUri(redirectUri);
		return super.buildAuthenticateUrl(grantType, parameters);
	}

	@Override
	public String buildAuthorizeUrl(GrantType grantType, OAuth2Parameters parameters) {
		if (redirectUri != null) parameters.setRedirectUri(redirectUri);
		return super.buildAuthorizeUrl(grantType, parameters);
	}

	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> additionalParameters) {
		return super.exchangeForAccess(authorizationCode, this.redirectUri != null ? this.redirectUri : redirectUri, additionalParameters);		
	}
	
}
