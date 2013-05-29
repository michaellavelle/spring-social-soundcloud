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
import static org.springframework.social.test.client.RequestMatchers.method;
import static org.springframework.social.test.client.RequestMatchers.requestTo;
import static org.springframework.social.test.client.ResponseCreators.withResponse;

import java.util.List;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.social.ResourceNotFoundException;

public class TrackTemplateTest extends AbstractSoundCloudApiTest {

	@Test
	public void search()
	{
		
		mockUnauthorizedServer
		.expect(requestTo("https://api.soundcloud.com/tracks?q=monsieur%2Badi&client_id=someApiKey"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/searchresults"),
						responseHeaders));

		Page<Track> tracksPage = unauthorizedSoundCloud.tracksOperations().search("monsieur adi");
		assertEquals(0,tracksPage.getNumber());
		assertEquals(46,tracksPage.getNumberOfElements());

		List<Track> tracks = tracksPage.getContent();
		assertNotNull(tracks);
		assertEquals(46,tracks.size());
		Track firstTrack = tracks.get(0);
		assertEquals("Obsessions (Monsieur Adi Remix)",firstTrack.getTitle());
		assertEquals("http://soundcloud.com/bangnmash/obsessions-monsieur-adi-remix",firstTrack.getPermalinkUrl());
		assertEquals("3252916",firstTrack.getId());
	
	}
	
	@Test
	public void search_NoSearchResults()
	{
		
		mockUnauthorizedServer
		.expect(requestTo("https://api.soundcloud.com/tracks?q=monsieur%2Badi&client_id=someApiKey"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/emptysearchresults"),
						responseHeaders));

		Page<Track> tracksPage = unauthorizedSoundCloud.tracksOperations().search("monsieur adi");
		assertEquals(0,tracksPage.getNumber());
		assertEquals(0,tracksPage.getNumberOfElements());
		List<Track> tracks = tracksPage.getContent();
		assertNotNull(tracks);
		assertEquals(0,tracks.size());
	}
	
	@Test
	public void getTrackPlaylists()
	{
		
		mockUnauthorizedServer
		.expect(requestTo("https://api.soundcloud.com/tracks/3252916/playlists?client_id=someApiKey"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/playlists"),
						responseHeaders));

		Page<Playlist> playlistsPage = unauthorizedSoundCloud.tracksOperations().getTrackPlaylists("3252916");
		assertEquals(0,playlistsPage.getNumber());
		assertEquals(1,playlistsPage.getNumberOfElements());

		List<Playlist> playlists = playlistsPage.getContent();
		assertNotNull(playlists);
		assertEquals(1,playlists.size());
		Playlist firstPlaylist = playlists.get(0);
		assertEquals("My Test Playlist",firstPlaylist.getTitle());
		assertEquals("http://soundcloud.com/michaellavelle/sets/my-test-playlist",firstPlaylist.getPermalinkUrl());
		assertEquals("2119912",firstPlaylist.getId());
	
	}

	@Test
	public void getTrack()
	{
		
		mockUnauthorizedServer
		.expect(requestTo("https://api.soundcloud.com/tracks/3252916?client_id=someApiKey"))
		.andExpect(method(GET))
		.andRespond(
				withResponse(jsonResource("testdata/track"),
						responseHeaders));

		Track track = unauthorizedSoundCloud.tracksOperations().getTrack("3252916");
	
		assertNotNull(track);
		assertEquals("Obsessions (Monsieur Adi Remix)",track.getTitle());
		assertEquals("http://soundcloud.com/bangnmash/obsessions-monsieur-adi-remix",track.getPermalinkUrl());
		assertEquals("3252916",track.getId());
	
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void getTrack_NotFound()
	{
		
		mockUnauthorizedServer
		.expect(requestTo("https://api.soundcloud.com/tracks/3252916a?client_id=someApiKey"))
		.andExpect(method(GET))
		.andRespond(
				
				withResponse(jsonResource("testdata/notfound"),
						responseHeaders,HttpStatus.NOT_FOUND,"404 - Not Found"));

		unauthorizedSoundCloud.tracksOperations().getTrack("3252916a");
	
	}
	
}
