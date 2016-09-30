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

package com.oliinykov.yevgen.android.amconapp.data;

import android.content.res.Resources;

import com.google.gson.reflect.TypeToken;
import com.oliinykov.yevgen.android.amconapp.R;
import com.oliinykov.yevgen.android.amconapp.data.entity.RequestEntity;
import com.oliinykov.yevgen.android.amconapp.data.entity.mapper.JsonSerializer;
import com.oliinykov.yevgen.android.amconapp.data.entity.mapper.RequestEntityDataMapper;
import com.oliinykov.yevgen.android.amconapp.domain.Request;
import com.oliinykov.yevgen.android.amconapp.domain.repository.RequestRepository;

import java.lang.reflect.Type;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * this class is an implementation of {@link RequestRepository} for retrieving {@link Request} data.
 */

public class RequestDataRepository implements RequestRepository {

    private final ResourceManager mResourceManager;
    private final JsonSerializer<RequestEntity> mRequestListSerializer;
    private final RequestEntityDataMapper mRequestEntityDataMapper;

    public RequestDataRepository(ResourceManager resourceManager,
                                 JsonSerializer<RequestEntity> requestListSerializer,
                                 RequestEntityDataMapper requestEntityDataMapper) {
        mResourceManager = resourceManager;
        mRequestListSerializer = requestListSerializer;
        mRequestEntityDataMapper = requestEntityDataMapper;
    }

    @Override
    public Observable<Request> request(long id) {
        return null;
    }

    @Override
    public Observable<List<Request>> requests() {
        return Observable.create(new Observable.OnSubscribe<List<RequestEntity>>() {
            @Override
            public void call(Subscriber<? super List<RequestEntity>> subscriber) {
                try {
                    String jsonResponseString = mResourceManager.readRawResource(R.raw.dummy);
                    if (jsonResponseString != null) {
                        Type type = new TypeToken<List<RequestEntity>>() {
                        }.getType();
                        subscriber.onNext(mRequestListSerializer
                                .deserializeEntityList(jsonResponseString, type));
                        subscriber.onCompleted();
                    }
                } catch (Resources.NotFoundException e) {
                    subscriber.onError(new Resources.NotFoundException());
                }
            }
        }).map(new Func1<List<RequestEntity>, List<Request>>() {
            @Override
            public List<Request> call(List<RequestEntity> requestEntities) {
                return mRequestEntityDataMapper.transform(requestEntities);
            }
        });
    }
}
