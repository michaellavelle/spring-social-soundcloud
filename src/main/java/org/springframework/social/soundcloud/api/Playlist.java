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

import java.util.List;

public class Playlist  {

	private String id;
	private String title;
	private String permalinkUrl;
	private String description;
	private List<Track> tracks;

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Track> getTracks() {
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}

	public Playlist(String permalinkUrl,String title,String id)
	{
		this.permalinkUrl = permalinkUrl;
		this.title = title;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getPermalinkUrl() {
		return permalinkUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
