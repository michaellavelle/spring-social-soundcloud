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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.soundcloud.api.MeOperations;
import org.springframework.social.soundcloud.api.SoundCloud;
import org.springframework.social.soundcloud.api.SoundCloudProfile;


public class SoundCloudAdapterTest {

	private SoundCloudAdapter apiAdapter = new SoundCloudAdapter();
	
	private SoundCloud soundCloud = Mockito.mock(SoundCloud.class);

	
	@Test
	public void fetchProfileForFirstNameLastName() {		
		MeOperations userOperations = Mockito.mock(MeOperations.class);
		Mockito.when(soundCloud.meOperations()).thenReturn(userOperations);
		Mockito.when(userOperations.getUserProfile()).thenReturn(createTestSoundCloudProfile("michaellavelle","Michael Lavelle"));
		UserProfile profile = apiAdapter.fetchUserProfile(soundCloud);
		assertEquals("Michael Lavelle", profile.getName());
		assertEquals("Michael", profile.getFirstName());
		assertEquals("Lavelle", profile.getLastName());
		assertNull(profile.getEmail());
		assertEquals("michaellavelle", profile.getUsername());
	}

	@Test
	public void fetchProfileForOnlyFirstName() {		
		MeOperations userOperations = Mockito.mock(MeOperations.class);
		Mockito.when(soundCloud.meOperations()).thenReturn(userOperations);
		Mockito.when(userOperations.getUserProfile()).thenReturn(createTestSoundCloudProfile("mattslip","mattslip"));
		UserProfile profile = apiAdapter.fetchUserProfile(soundCloud);
		assertEquals("mattslip", profile.getName());
		assertEquals("mattslip", profile.getFirstName());
		assertNull(profile.getLastName());
		assertNull(profile.getEmail());
		assertEquals("mattslip", profile.getUsername());
	}
	
	
	@Test
	public void setConnectionValues() {		
		MeOperations meOperations = Mockito.mock(MeOperations.class);
		Mockito.when(soundCloud.meOperations()).thenReturn(meOperations);
		Mockito.when(meOperations.getUserProfile()).thenReturn(createTestSoundCloudProfile("michaellavelle","Michael Lavelle"));
		TestConnectionValues connectionValues = new TestConnectionValues();
		apiAdapter.setConnectionValues(soundCloud, connectionValues);
		assertEquals("michaellavelle", connectionValues.getDisplayName());
		assertEquals("http://a1.sndcdn.com/images/default_avatar_large.png?8460df1", connectionValues.getImageUrl());
		assertEquals("http://soundcloud.com/michaellavelle", connectionValues.getProfileUrl());
		assertEquals("12345678", connectionValues.getProviderUserId());
	}
	
	
	private SoundCloudProfile createTestSoundCloudProfile(String userName,String fullName)
	{
		SoundCloudProfile soundCloudProfile = new SoundCloudProfile();
		soundCloudProfile.setId("12345678");
		soundCloudProfile.setUsername(userName);
		soundCloudProfile.setAvatarUrl("http://a1.sndcdn.com/images/default_avatar_large.png?8460df1");
		soundCloudProfile.setPermalinkUrl("http://soundcloud.com/michaellavelle");
		soundCloudProfile.setFullName(fullName);
		soundCloudProfile.setUri("https://api.soundcloud.com/users/7031365");
		soundCloudProfile.setCity("London");

		return soundCloudProfile;
	}

	private static class TestConnectionValues implements ConnectionValues {

		private String displayName;
		private String imageUrl;
		private String profileUrl;
		private String providerUserId;

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public String getImageUrl() {
			return imageUrl;
		}

		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}

		public String getProfileUrl() {
			return profileUrl;
		}

		public void setProfileUrl(String profileUrl) {
			this.profileUrl = profileUrl;
		}

		public String getProviderUserId() {
			return providerUserId;
		}

		public void setProviderUserId(String providerUserId) {
			this.providerUserId = providerUserId;
		}
	
	}

}
