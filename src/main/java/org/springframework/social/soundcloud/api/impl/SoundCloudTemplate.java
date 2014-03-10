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
package org.springframework.social.soundcloud.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.social.NotAuthorizedException;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.OAuth2Version;
import org.springframework.social.soundcloud.api.MeOperations;
import org.springframework.social.soundcloud.api.PlaylistsOperations;
import org.springframework.social.soundcloud.api.ResolveOperations;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.TrackReference;
import org.springframework.social.soundcloud.api.TracksOperations;
import org.springframework.social.soundcloud.api.UsersOperations;
import org.springframework.social.soundcloud.api.impl.json.SoundCloudModule;
import org.springframework.social.soundcloud.api.impl.xml.TrackArray;
import org.springframework.social.soundcloud.api.impl.xml.XmlPlaylistUpdate;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SoundCloudTemplate extends AbstractOAuth2ApiBinding implements
		SoundCloud {

	private MeOperations meOperations;
	private UsersOperations usersOperations;
	private ResolveOperations resolveOperations;
	private TracksOperations tracksOperations;
	private PlaylistsOperations playlistsOperations;

	private ObjectMapper objectMapper;

	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.DRAFT_10;
	}

	/**
	 * Create a new instance of SoundCloudTemplate. This constructor creates a
	 * new SoundCloudTemplate able to perform unauthenticated operations against
	 * SoundCloud's API. Some operations do not require OAuth authentication.
	 * For example, retrieving a specified user's profile or feed does not
	 * require authentication . A SoundCloudTemplate created with this
	 * constructor will support those operations. Those operations requiring
	 * authentication will throw {@link NotAuthorizedException}.
	 */
	public SoundCloudTemplate(String clientId) {
		initialize(clientId, null);
	}

	/**
	 * Create a new instance of SoundCloudTemplate. This constructor creates the
	 * SoundCloudTemplate using a given access token.
	 * 
	 * @param accessToken
	 *            An access token given by SoundCloud after a successful OAuth 2
	 *            authentication
	 */
	public SoundCloudTemplate(String clientId, String accessToken) {
		super(accessToken);
		initialize(clientId, accessToken);
	}

	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> messageConverters = super
				.getMessageConverters();
		messageConverters.add(new ByteArrayHttpMessageConverter());
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<Class<?>, String> implicitCollections = new HashMap<Class<?>, String>();
		implicitCollections.put(TrackArray.class, "tracks");
		marshaller.setImplicitCollections(implicitCollections);

		// marshaller.setConverters(converterMatchers);
		Map<String, Object> aliases = new HashMap<String, Object>();
		aliases.put("playlist", XmlPlaylistUpdate.class.getName());
		// aliases.put("playlist", PlaylistUpdate.class.getName());

		aliases.put("track", TrackReference.class.getName());

		Map<String, Class<?>> useAttributeFor = new HashMap<String, Class<?>>();
		useAttributeFor.put("type", String.class);

		try {
			marshaller.setAliases(aliases);
			marshaller.setUseAttributeFor(useAttributeFor);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		messageConverters.add(new MarshallingHttpMessageConverter(marshaller,
				marshaller));
		return messageConverters;
	}

	@Override
	public MeOperations meOperations() {
		return meOperations;
	}

	private void initSubApis(String clientId, String accessToken) {
		usersOperations = new UsersTemplate(clientId, getRestTemplate(),
				isAuthorized());
		meOperations = new MeTemplate(getRestTemplate(), isAuthorized());
		resolveOperations = new ResolveTemplate(clientId, getRestTemplate(),
				isAuthorized());
		tracksOperations = new TracksTemplate(clientId, getRestTemplate(),
				isAuthorized());
		playlistsOperations = new PlaylistsTemplate(clientId,
				getRestTemplate(), isAuthorized(), resolveOperations);

	}

	// private helpers
	private void initialize(String clientId, String accessToken) {
		// Wrap the request factory with a BufferingClientHttpRequestFactory so
		// that the error handler can do repeat reads on the response.getBody()
		super.setRequestFactory(ClientHttpRequestFactorySelector
				.bufferRequests(getRestTemplate().getRequestFactory()));
		initSubApis(clientId, accessToken);

	}

	@Override
	protected void configureRestTemplate(RestTemplate restTemplate) {
		restTemplate.setErrorHandler(new SoundCloudErrorHandler());
	}

	@Override
	protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
		MappingJackson2HttpMessageConverter converter = super
				.getJsonMessageConverter();
		objectMapper = new ObjectMapper();
		objectMapper.registerModule(new SoundCloudModule());
		converter.setObjectMapper(objectMapper);
		return converter;
	}

	@Override
	public UsersOperations usersOperations() {
		return usersOperations;
	}

	@Override
	public ResolveOperations resolveOperations() {
		return resolveOperations;
	}

	@Override
	public TracksOperations tracksOperations() {
		return tracksOperations;
	}

	@Override
	public PlaylistsOperations playlistsOperations() {
		return playlistsOperations;
	}

}
