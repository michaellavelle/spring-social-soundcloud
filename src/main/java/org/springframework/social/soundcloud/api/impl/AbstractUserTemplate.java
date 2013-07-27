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

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.social.soundcloud.api.Playlist;
import org.springframework.social.soundcloud.api.SoundCloudProfile;
import org.springframework.social.soundcloud.api.Track;
import org.springframework.social.soundcloud.api.UserOperations;
import org.springframework.social.soundcloud.api.impl.json.PlaylistList;
import org.springframework.social.soundcloud.api.impl.json.SoundCloudProfileList;
import org.springframework.social.soundcloud.api.impl.json.TrackList;
import org.springframework.web.client.RestTemplate;

public abstract class AbstractUserTemplate extends AbstractSoundCloudResourceOperations implements UserOperations {
	
	public AbstractUserTemplate(String clientId,RestTemplate restTemplate,
			boolean isAuthorizedForUser,boolean appendClientIdIfNotAuthorized) {
		super(clientId,restTemplate, isAuthorizedForUser,appendClientIdIfNotAuthorized);
	}
	protected abstract String getUsersResourcePrefix();
		
	@Override
	protected String getApiResourceBaseUrl() {
		return getApiBaseUrl() + getUsersResourcePrefix();
	}
	
	@Override
	public void favoriteTrack(long trackId) {
		restTemplate.put(getApiResourceUrl("/favorites/" + trackId),null);

	}
	

	@Override
	public void followUser(long userId) {
		restTemplate.put(getApiResourceUrl("/followings/" + userId),null);

	}
	
	@Override 
	public SoundCloudProfile getUserProfile() {
		return  restTemplate.getForObject(getApiResourceUrl(""), SoundCloudProfile.class);
	}
	
	@Override
	public Page<Track> getFavorites() {
		 return  getFavorites(null);
	}
	
	@Override
	public Page<SoundCloudProfile> getFollowing() {
		 return  getFollowing(null);
	}

	@Override
	public Page<Track> getFavorites(Pageable pageable) {

		 SoundCloudProfile soundCloudProfile = getUserProfile();
		 List<Track> tracks = restTemplate.getForObject(getApiResourceUrl("/favorites",pageable), TrackList.class);	 
		 if (pageable == null)
		 {
			 return new PageImpl<Track>(tracks,new PageRequest(0,50),soundCloudProfile.getFavoritesCount());
		 }
		 else
		 {
			 return new PageImpl<Track>(tracks,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()),soundCloudProfile.getFavoritesCount());
		 }
		 
	}
	
	@Override
	public Page<SoundCloudProfile> getFollowing(Pageable pageable) {

		 SoundCloudProfile soundCloudProfile = getUserProfile();
		 List<SoundCloudProfile> profiles = restTemplate.getForObject(getApiResourceUrl("/followings",pageable), SoundCloudProfileList.class);	 
		 if (pageable == null)
		 {
			 // TODO
			 //return new PageImpl<SoundCloudProfile>(profiles,new PageRequest(0,50),soundCloudProfile.getFavoritesCount());
			 return new PageImpl<SoundCloudProfile>(profiles);
		 }
		 else
		 {
			 return new PageImpl<SoundCloudProfile>(profiles,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()),soundCloudProfile.getFavoritesCount());
		 }
		 
	}
	
	@Override
	public Page<Track> getTracks() {
		return getTracks(null);
	}
	
	@Override
	public Page<Track> getTracks(Pageable pageable) {
		 SoundCloudProfile soundCloudProfile = getUserProfile();

		 List<Track> tracks =  restTemplate.getForObject(getApiResourceUrl("/tracks",pageable), TrackList.class);	
		 if (pageable == null)
		 {
			 return new PageImpl<Track>(tracks,new PageRequest(0,50),soundCloudProfile.getTrackCount());
		 }
		 else
		 {
			 return new PageImpl<Track>(tracks,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()),soundCloudProfile.getTrackCount());
		 }
		 
		 
		 
	}
	

	@Override
	public Page<Playlist> getPlaylists() {
		return getPlaylists(null);
	}
	
	@Override
	public Page<Playlist> getPlaylists(Pageable pageable) {
		 SoundCloudProfile soundCloudProfile = getUserProfile();

		 List<Playlist> playlists =  restTemplate.getForObject(getApiResourceUrl("/playlists",pageable), PlaylistList.class);	
		 if (pageable == null)
		 {
			 return new PageImpl<Playlist>(playlists,new PageRequest(0,50),soundCloudProfile.getPlaylistCount());
		 }
		 else
		 {
			 return new PageImpl<Playlist>(playlists,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()),soundCloudProfile.getPlaylistCount());
		 }
		 
		 
		 
	}
	


}
