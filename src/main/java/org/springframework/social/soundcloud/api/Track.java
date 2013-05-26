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

/**
 * See http://developers.soundcloud.com/docs/api/reference#tracks
 */
public class Track extends TrackReference {
	
	private String title;
	private String sharing;
	private String embeddableBy;
	private String purchaseUrl;
	private String artworkUrl;
	private String description;
	private long duration;
	private String genre;

	public Track(String permalinkUrl,String title,String id)
	{
		super(id,permalinkUrl);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * public/private sharing
	 * "public" or "private"
	 */
	public String getSharing() {
		return sharing;
	}

	public void setSharing(String sharing) {
		this.sharing = sharing;
	}

	public boolean isPublic() {
		return "public".equals(sharing);
	}

	/**
	 * who can embed this track or playlist
	 * "all", "me", or "none"
	 */
	public String getEmbeddableBy() {
		return embeddableBy;
	}

	public void setEmbeddableBy(String embeddableBy) {
		this.embeddableBy = embeddableBy;
	}

	public boolean isEmbeddableByAll() {
		return "all".equals(embeddableBy);
	}

	public boolean isEmbeddableByMe() {
		return "me".equals(embeddableBy);
	}

	public String getPurchaseUrl() {
		return purchaseUrl;
	}

	public void setPurchaseUrl(String purchaseUrl) {
		this.purchaseUrl = purchaseUrl;
	}

	public String getArtworkUrl() {
		return artworkUrl;
	}

	public void setArtworkUrl(String artworkUrl) {
		this.artworkUrl = artworkUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getStreamUrl()
	{
		return "http://api.soundcloud.com/tracks/" + getId() + "/stream"; 
	}

}
