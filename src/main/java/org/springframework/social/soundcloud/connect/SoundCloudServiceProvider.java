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

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.impl.SoundCloudTemplate;

/**
* SoundCloud ServiceProvider implementation.
* @author Michael Lavelle
*/
public class SoundCloudServiceProvider extends AbstractOAuth2ServiceProvider<SoundCloud>{

	private String clientId;
	
	public SoundCloudServiceProvider(String clientId, String clientSecret) {
		this(clientId, clientSecret, null);
	}
	

	public SoundCloudServiceProvider(String clientId, String clientSecret, String redirectUri) {
		super(new SoundCloudOAuth2Template(clientId, clientSecret, redirectUri));
	}

	@Override
	public SoundCloud getApi(String accessToken) {
		return new SoundCloudTemplate(clientId, accessToken);
	}

}
