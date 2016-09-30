/*
 * Copyright (c) 2016 Yevgen Oliinykov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oliinykov.yevgen.android.amconapp.data.entity;

/**
 * Class that represents {@link com.oliinykov.yevgen.android.amconapp.domain.Request} in data layer.
 */

public class RequestEntity {

    private long id;
    private String title;
    private String hash;
    private String created;
    private String estimation;
    private String likes;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEstimation() {
        return estimation;
    }

    public void setEstimation(String estimation) {
        this.estimation = estimation;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "--------------RequestEntity------------------" + "\n" +
                "id: " + getId() + "\n" +
                "title: " + getTitle() + "\n" +
                "hash" + getHash() + "\n" +
                "created: " + getCreated() + "\n" +
                "estimation: " + getEstimation() + "\n" +
                "likes: " + getLikes() + "\n" +
                "status: " + getStatus() + "\n" +
                "--------------------------------------------" + "\n";
    }
}
