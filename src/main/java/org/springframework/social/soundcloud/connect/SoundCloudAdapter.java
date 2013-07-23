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
package org.springframework.social.soundcloud.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.SoundCloudProfile;

/**
* SoundCloud ApiAdapter implementation.
* @author Michael Lavelle
*/
public class SoundCloudAdapter implements ApiAdapter<SoundCloud> {

	@Override
	public UserProfile fetchUserProfile(SoundCloud soundCloud) {
		SoundCloudProfile profile = soundCloud.meOperations().getUserProfile();
		return new UserProfileBuilder().setName(profile.getFull_name()).setUsername(profile.getUsername()).build();

	}

	@Override
	public void setConnectionValues(SoundCloud soundCloud, ConnectionValues values) {
		SoundCloudProfile profile = soundCloud.meOperations().getUserProfile();
		values.setProviderUserId(profile.getId());
		values.setDisplayName(profile.getUsername());
		values.setProfileUrl(profile.getPermalink_url());
		values.setImageUrl(profile.getAvatar_url());
	}


	
	@Override
	public boolean test(SoundCloud soundCloud) {
		try {
			soundCloud.meOperations().getUserProfile();
			return true;
		} catch (ApiException e) {
			return false;
		}
	}

	@Override
	public void updateStatus(SoundCloud soundCloud, String arg1) {}
}
