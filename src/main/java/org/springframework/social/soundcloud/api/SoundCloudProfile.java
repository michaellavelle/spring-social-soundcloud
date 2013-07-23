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

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
* Model class containing a SoundCloud user's profile information.
* @author Michael Lavelle
*/
public class SoundCloudProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String kind;
	private String permalink;
	private String username;
	private String uri;
	private String permalink_url;
	private String avatar_url;
	private String country;
	private String full_name;
	private String description;
	private String city;
	private String discogs_name;
	private String myspace_name;
	@JsonIgnore
	private String website;
	@JsonIgnore
	private String website_title;
	@JsonIgnore
	private boolean online;
	@JsonIgnore
	private int track_count;
	@JsonIgnore
	private int playlist_count;
	@JsonIgnore
	private String plan;
	@JsonIgnore
	private int public_favorites_count;
	@JsonIgnore
	private int followers_count;
	@JsonIgnore
	private int followings_count;
	@JsonIgnore
	private String[] subscriptions;
	@JsonIgnore
	private int upload_seconds_left;
	@JsonIgnore
	private String[] quota;
	@JsonIgnore
	private int private_tracks_count;
	@JsonIgnore
	private int private_playlists_count;
	@JsonIgnore
	private boolean primary_email_confirmed;

	public SoundCloudProfile() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getPermalink_url() {
		return permalink_url;
	}

	public void setPermalink_url(String permalinkUrl) {
		this.permalink_url = permalinkUrl;
	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatarUrl) {
		this.avatar_url = avatarUrl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String fullName) {
		this.full_name = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDiscogs_name() {
		return discogs_name;
	}

	public void setDiscogs_name(String discogsName) {
		this.discogs_name = discogsName;
	}

	public String getMyspace_name() {
		return myspace_name;
	}

	public void setMyspace_name(String myspaceName) {
		this.myspace_name = myspaceName;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getWebsite_title() {
		return website_title;
	}

	public void setWebsite_title(String website_title) {
		this.website_title = website_title;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public int getTrack_count() {
		return track_count;
	}

	public void setTrack_count(int track_count) {
		this.track_count = track_count;
	}

	public int getPlaylist_count() {
		return playlist_count;
	}

	public void setPlaylist_count(int playlist_count) {
		this.playlist_count = playlist_count;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public int getPublic_favorites_count() {
		return public_favorites_count;
	}

	public void setPublic_favorites_count(int public_favorites_count) {
		this.public_favorites_count = public_favorites_count;
	}

	public int getFollowers_count() {
		return followers_count;
	}

	public void setFollowers_count(int followers_count) {
		this.followers_count = followers_count;
	}

	public int getFollowings_count() {
		return followings_count;
	}

	public void setFollowings_count(int followings_count) {
		this.followings_count = followings_count;
	}

	public String[] getSubscriptions() {
		return subscriptions;
	}
	
	public void setSubscriptions(String[] subscriptions) {
		this.subscriptions = subscriptions;
	}
	
	public int getUpload_seconds_left() {
		return upload_seconds_left;
	}

	public void setUpload_seconds_left(int upload_seconds_left) {
		this.upload_seconds_left = upload_seconds_left;
	}

	public String[] getQuota() {
		return quota;
	}
	
	public void setQuota(String[] quota) {
		this.quota = quota;
	}
	
	public int getPrivate_tracks_count() {
		return private_tracks_count;
	}

	public void setPrivate_tracks_count(int private_tracks_count) {
		this.private_tracks_count = private_tracks_count;
	}

	public int getPrivate_playlists_count() {
		return private_playlists_count;
	}

	public void setPrivate_playlists_count(int private_playlists_count) {
		this.private_playlists_count = private_playlists_count;
	}

	public boolean isPrimary_email_confirmed() {
		return primary_email_confirmed;
	}

	public void setPrimary_email_confirmed(boolean primary_email_confirmed) {
		this.primary_email_confirmed = primary_email_confirmed;
	}


	
}
