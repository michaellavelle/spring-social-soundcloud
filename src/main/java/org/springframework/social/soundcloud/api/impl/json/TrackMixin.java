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
 * Annotated mixin to add Jackson annotations to Track.
 *
 * @author Michael Lavelle
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class TrackMixin {

    @JsonCreator
    TrackMixin(
            @JsonProperty("permalink_url") String permalinkUrl,
            @JsonProperty("title") String title, @JsonProperty("id") String id) {
    }

    @JsonProperty("artwork_url")
    void setArtworkUrl(String artworkUrl) {
    }

    @JsonProperty("label_name")
    void setLabelName(String labelName) {
    }

    @JsonProperty("label_id")
    void setLabelid(String labelid) {
    }

    @JsonProperty("tag_list")
    void setTagList(String tagList) {
    }

    @JsonProperty("purchase_url")
    void setPurchaseUrl(String purchaseUrl) {
    }

    @JsonProperty("embeddable_by")
    void setEmbeddableBy(String embeddableBy) {
    }

    @JsonProperty("release_day")
    void setReleaseDay(int releaseDay) {
    }

    @JsonProperty("release_month")
    void setReleaseMonth(int releaseMonth) {
    }

    @JsonProperty("release_year")
    void setReleaseYear(int releaseYear) {
    }

    @JsonProperty("track_type")
    void setTrackType(String trackType) {
    }

    @JsonProperty("download_url")
    void setDownloadUrl(String downloadUrl) {
    }

    @JsonProperty("video_url")
    void setVideoUrl(String videoUrl) {
    }

    @JsonProperty("stream_url")
    void setStreamUrl(String streamUrl) {
    }

    @JsonProperty("key_signature")
    void setKeySignature(String keySignature) {
    }

    @JsonProperty("comment_count")
    void setCommentCount(long commentCount) {
    }

    @JsonProperty("download_count")
    void setDownloadCount(long downloadCount) {
    }

    @JsonProperty("playback_count")
    void setPlaybackCount(long playbackCount) {
    }

    @JsonProperty("favoritings_count")
    void setFavoritingsCount(long favoritingsCount) {
    }

}
