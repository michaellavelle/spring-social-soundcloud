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
package org.springframework.social.soundcloud.api.impl.json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;




/**
 * Annotated mixin to add Jackson annotations to SoundCloudProfile. 
 * @author Michael Lavelle
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class SoundCloudProfileMixin {

	
	@JsonCreator
	SoundCloudProfileMixin(
			@JsonProperty("id") String id, 
			@JsonProperty("username") String username, 
			@JsonProperty("avatar_url") String avatarUrl, 
			@JsonProperty("permalink_url") String permalinkUrl, 
			@JsonProperty("full_name") String fullName, 
			@JsonProperty("uri") String uri, 
			@JsonProperty("city") String city) {} 
	
	
	@JsonProperty("public_favorites_count")
	void setFavoritesCount(int favoritesCount) {}
	
	@JsonProperty("track_count")
	void setTrackCount(int trackCount) {}
	
	@JsonProperty("playlist_count")
	void setPlaylistCount(int playlistCount) {}
	
	@JsonProperty("followers_count")
	void setFollowersCount(int followersCount) {}
	
	@JsonProperty("followings_count")
	void setFollowingsCount(int followingsCount) {}
	
	@JsonProperty("permalink_url")
	void setPermalinkUrl(String permalinkUrl) {}
	
	@JsonProperty("avatar_url")
	void setAvatarUrl(String avatarUrl) {}
	
	@JsonProperty("full_name")
	void setFullName(String fullName) {}
	
	@JsonProperty("discogs_name")
	void setDiscogsName(String discogsName) {}
	
	@JsonProperty("website_title")
	void setWebsiteTitle(String websiteTitle) {}
	
	@JsonProperty("myspace_name")
	void setMyspaceName(String myspaceName) {}
	
	@JsonProperty("upload_seconds_left")
	void setUploadSecondsLeft(int uploadSecondsLeft) {}
	
	@JsonProperty("private_tracks_count")
	void setPrivateTracksCount(int privateTracksCount) {}
	
	
	@JsonProperty("private_playlists_count")
	void setPrivatePlaylistsCount(int privatePlaylistsCount) {}
	
	@JsonProperty("primary_email_confirmed")
	void setPrimaryEmailConfirmed(boolean primaryEmailConfirmed) {}
	
	
}
