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
package org.springframework.social.soundcloud.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.social.test.client.RequestMatchers.header;
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Page;


public class MeTemplateTest extends AbstractSoundCloudApiTest {

	@Test
	public void getFavorites() {
		
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me"))
		.andExpect(header("Authorization","OAuth " + ACCESS_TOKEN))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/userprofile"),
						responseHeaders));
		
		
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me/favorites"))
		.andExpect(method(GET))
		.andExpect(header("Authorization","OAuth " + ACCESS_TOKEN))
		.andRespond(
				withResponse(jsonResource("testdata/favorites"),
						responseHeaders));

		Page<Track> tracksPage = soundCloud.meOperations().getFavorites();
		assertEquals(0,tracksPage.getNumber());
		assertEquals(56,tracksPage.getTotalElements());
		List<Track> tracks = tracksPage.getContent();
		assertNotNull(tracks);
		assertEquals(50,tracks.size());
		Track firstTrack = tracks.get(0);
		assertEquals("Sneaky Sound System - Big (Oliver Remix)",firstTrack.getTitle());
		assertEquals("http://soundcloud.com/weareoliver/sneaky-sound-system-big-oliver",firstTrack.getPermalinkUrl());
		assertEquals("22905377",firstTrack.getId());
	
	}
	
	@Test
	public void getTracks() {

		
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me"))
		.andExpect(header("Authorization","OAuth " + ACCESS_TOKEN))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/userprofile"),
						responseHeaders));
		
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me/tracks"))
		.andExpect(header("Authorization","OAuth " + ACCESS_TOKEN))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/tracks"),
						responseHeaders));

		Page<Track> tracksPage = soundCloud.meOperations().getTracks();
		List<Track> tracks= tracksPage.getContent();

		assertNotNull(tracks);
		assertEquals(50,tracks.size());
		Track firstTrack = tracks.get(0);
		assertEquals("Kid Sister vs Daft Punk (Monsieur Adi Mashup)",firstTrack.getTitle());
		assertEquals("http://soundcloud.com/monsieuradi/kid-sister-vs-daft-punk",firstTrack.getPermalinkUrl());
		assertEquals("39679670",firstTrack.getId());
	
		
	}
	
	@Test
	public void getUserProfile() {


		mockServer
		.expect(requestTo("https://api.soundcloud.com/me"))
		.andExpect(header("Authorization","OAuth " + ACCESS_TOKEN))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/userprofile"),
						responseHeaders));

		SoundCloudProfile userProfile = soundCloud.meOperations().getUserProfile();
		assertNotNull(userProfile);

		assertEquals("mattslip",userProfile.getUsername());
		assertEquals("Matt Slip",userProfile.getFullName());
		assertEquals("https://i1.sndcdn.com/avatars-000002866761-cj637a-large.jpg?cc23540",userProfile.getAvatarUrl());
		assertEquals("London",userProfile.getCity());
		assertEquals("https://api.soundcloud.com/users/3510549",userProfile.getUri());
		assertEquals("http://soundcloud.com/mattslip",userProfile.getPermalinkUrl());

	}
	
	@Test
	public void favoriteTrack() {


		mockServer
		.expect(requestTo("https://api.soundcloud.com/me/favorites/12345"))
		.andExpect(header("Authorization","OAuth " + ACCESS_TOKEN))
		.andExpect(method(PUT))
		.andRespond(
				withResponse(jsonResource("testdata/userprofile"),
						responseHeaders));

		soundCloud.meOperations().favoriteTrack(12345);
	
	}
	
	
	
	@Test
	public void getPlaylists()
	{
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/userprofile"),
						responseHeaders));
		
		
		
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me/playlists"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/playlists"),
						responseHeaders));

		Page<Playlist> playlists = soundCloud.meOperations().getPlaylists();
		assertEquals(1,playlists.getNumberOfElements());
		Playlist playlist = playlists.getContent().get(0);
		assertPlaylistData(playlist);

	}
	
	@Test
	public void getActivities()
	{
		
		mockServer
		.expect(requestTo("https://api.soundcloud.com/me/activities"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/activities"),
						responseHeaders));

		Page<Activity> activities = soundCloud.meOperations().getActivities();
		assertEquals(50,activities.getNumberOfElements());
		Activity activity = activities.getContent().get(0);
		assertEquals("favoriting",activity.getType());
		Track track = activity.getOrigin().getTrack();
		assertEquals("49581078",track.getId());
		assertEquals("Salon Acapulco - Atardecer",track.getTitle());
		assertEquals("http://soundcloud.com/salonacapulco/salon-acapulco-atardecer",track.getPermalinkUrl());



	}
	
	

	
}
