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

import java.net.URLEncoder;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.social.soundcloud.api.Playlist;
import org.springframework.social.soundcloud.api.Track;
import org.springframework.social.soundcloud.api.TracksOperations;
import org.springframework.social.soundcloud.api.impl.json.PlaylistList;
import org.springframework.social.soundcloud.api.impl.json.TrackList;
import org.springframework.web.client.RestTemplate;

public class TracksTemplate extends AbstractSoundCloudResourceOperations implements TracksOperations {

	public TracksTemplate(String clientId,RestTemplate restTemplate, boolean isAuthorizedForUser) {
		super(clientId,restTemplate, isAuthorizedForUser,true);
	}
	
	@Override
	public Page<Track> search(String query) {
		return search(query,null);
	}
	

	@Override
	public Page<Track> search(String query,Pageable pageable) {
		 List<Track> tracks =  restTemplate.getForObject(getApiResourceUrl("?q=" + URLEncoder.encode(query),pageable), TrackList.class);
		 return new PageImpl<Track>(tracks,pageable,tracks.size());
	}



	@Override
	protected String getApiResourceBaseUrl() {
		return getApiBaseUrl() + "/tracks";
	}
	
	@Override
	public Track getTrack(String trackId) {
		 return restTemplate.getForObject(getApiResourceUrl("/" + trackId), Track.class);
	}

	@Override
	public Page<Playlist> getTrackPlaylists(String trackId,Pageable pageable) {
		 List<Playlist> playlists = restTemplate.getForObject(getApiResourceUrl("/" + trackId + "/playlists",pageable), PlaylistList.class);
		 return new PageImpl<Playlist>(playlists,pageable,playlists.size());

	}

	@Override
	public Page<Playlist> getTrackPlaylists(String trackId) {
		return getTrackPlaylists(trackId,null);
	}



}
