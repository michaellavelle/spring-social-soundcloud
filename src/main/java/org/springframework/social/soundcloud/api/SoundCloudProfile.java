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
	private String permalinkUrl;
	private String avatarUrl;
	private String country;
	private String fullName;
	private String description;
	private String city;
	private String discogs_name;
	private String myspaceName;
	private String website;
	private String websiteTitle;
	private boolean online;
	private int trackCount;
	private int playlistCount;
	private String plan;
	private int favoritesCount;
	private int followersCount;
	private int followingsCount;
	private Subscription[] subscriptions;
	private int uploadSecondsLeft;
	private int privateTracksCount;
	private int privatePlaylistsCount;
	private boolean primaryEmailConfirmed;

	public SoundCloudProfile() {
	}

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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	
	public Subscription[] getSubscriptions() {
		return subscriptions;
	}
	
	public String getPermalinkUrl() {
		return permalinkUrl;
	}

	public void setPermalinkUrl(String permalinkUrl) {
		this.permalinkUrl = permalinkUrl;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMyspaceName() {
		return myspaceName;
	}

	public void setMyspaceName(String myspaceName) {
		this.myspaceName = myspaceName;
	}

	public String getWebsiteTitle() {
		return websiteTitle;
	}

	public void setWebsiteTitle(String websiteTitle) {
		this.websiteTitle = websiteTitle;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public int getPlaylistCount() {
		return playlistCount;
	}

	public void setPlaylistCount(int playlistCount) {
		this.playlistCount = playlistCount;
	}

	public int getFavoritesCount() {
		return favoritesCount;
	}

	public void setFavoritesCount(int favoritesCount) {
		this.favoritesCount = favoritesCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public int getFollowingsCount() {
		return followingsCount;
	}

	public void setFollowingsCount(int followingsCount) {
		this.followingsCount = followingsCount;
	}

	public int getUploadSecondsLeft() {
		return uploadSecondsLeft;
	}

	public void setUploadSecondsLeft(int uploadSecondsLeft) {
		this.uploadSecondsLeft = uploadSecondsLeft;
	}

	public int getPrivateTracksCount() {
		return privateTracksCount;
	}

	public void setPrivateTracksCount(int privateTracksCount) {
		this.privateTracksCount = privateTracksCount;
	}

	public int getPrivatePlaylistsCount() {
		return privatePlaylistsCount;
	}

	public void setPrivatePlaylistsCount(int privatePlaylistsCount) {
		this.privatePlaylistsCount = privatePlaylistsCount;
	}

	public boolean isPrimaryEmailConfirmed() {
		return primaryEmailConfirmed;
	}

	public void setPrimaryEmailConfirmed(boolean primaryEmailConfirmed) {
		this.primaryEmailConfirmed = primaryEmailConfirmed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSubscriptions(Subscription[] subscriptions) {
		this.subscriptions = subscriptions;
	}

	
	
}
