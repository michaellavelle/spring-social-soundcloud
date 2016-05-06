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

import org.springframework.social.soundcloud.api.SoundCloudProfile;

import java.io.Serializable;
import java.util.ArrayList;

public class SoundCloudProfileList implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    public ArrayList<SoundCloudProfile> getCollection() {
        return collection;
    }

    public void setCollection(ArrayList<SoundCloudProfile> collection) {
        this.collection = collection;
    }

    private ArrayList<SoundCloudProfile> collection;

    private String next_href;

    public String getNext_href() {
        return next_href;
    }

    public void setNext_href(String next_href) {
        this.next_href = next_href;
    }
}
