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


import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.social.soundcloud.api.Activity;
import org.springframework.social.soundcloud.api.Origin;
import org.springframework.social.soundcloud.api.Playlist;
import org.springframework.social.soundcloud.api.SoundCloudProfile;
import org.springframework.social.soundcloud.api.Track;


/**
 * Jackson module for setting up mixin annotations on SoundCloud model types. This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 * @author Michael Lavelle
 */
public class SoundCloudModule extends SimpleModule {

	public SoundCloudModule() {
		super("SoundCloudModule", new Version(1, 0, 0, null, null, null));
	}
	
	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(SoundCloudProfile.class, SoundCloudProfileMixin.class);
		context.setMixInAnnotations(Track.class, TrackMixin.class);
		context.setMixInAnnotations(Activity.class, ActivityMixin.class);
		context.setMixInAnnotations(Origin.class, OriginMixin.class);
		context.setMixInAnnotations(Playlist.class, PlaylistMixin.class);

	}
}
