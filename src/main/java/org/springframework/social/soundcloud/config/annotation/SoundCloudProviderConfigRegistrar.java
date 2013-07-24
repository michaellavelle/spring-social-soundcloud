package org.springframework.social.soundcloud.config.annotation;
/*
 * Copyright 2013 the original author or authors.
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

import java.util.Map;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.social.config.annotation.AbstractProviderConfigRegistrarSupport;
import org.springframework.social.security.provider.SocialAuthenticationService;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.config.support.SoundCloudApiHelper;
import org.springframework.social.soundcloud.connect.SoundCloudConnectionFactory;
import org.springframework.social.soundcloud.security.SoundCloudAuthenticationService;

/**
 * {@link ImportBeanDefinitionRegistrar} for configuring a {@link SoundCloudConnectionFactory} bean and a request-scoped {@link SoundCloud} bean.
 * @author Michael Lavelle
 */
public class SoundCloudProviderConfigRegistrar extends AbstractProviderConfigRegistrarSupport {

	public SoundCloudProviderConfigRegistrar() {
		super(EnableSoundCloud.class, SoundCloudConnectionFactory.class, SoundCloudApiHelper.class);
	}
	
	
	@Override
	protected BeanDefinition getConnectionFactoryBeanDefinition(String appId,
			String appSecret, Map<String, Object> allAttributes) {
		return BeanDefinitionBuilder.genericBeanDefinition(connectionFactoryClass).addConstructorArgValue(appId).addConstructorArgValue(appSecret).addConstructorArgValue(getRedirectUri(allAttributes)).getBeanDefinition();
	}


	@Override
	protected BeanDefinition getAuthenticationServiceBeanDefinition(
			String appId, String appSecret, Map<String, Object> allAttributes) {
		return BeanDefinitionBuilder.genericBeanDefinition(authenticationServiceClass).addConstructorArgValue(appId).addConstructorArgValue(appSecret).addConstructorArgValue(getRedirectUri(allAttributes)).getBeanDefinition();

	}


	@Override
	protected Class<? extends SocialAuthenticationService<?>> getAuthenticationServiceClass() {
		return SoundCloudAuthenticationService.class;
	}
	
	protected String getRedirectUri(Map<String, Object> allAttributes) {
		String redirectUri = (String)allAttributes.get("redirectUri");
		return redirectUri.isEmpty() ? null : redirectUri;	
	}

}
