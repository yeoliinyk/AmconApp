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

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.oliinykov.yevgen.android.amconapp.R;
import com.oliinykov.yevgen.android.amconapp.data.entity.RequestEntity;
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
    private final RequestEntityDataMapper mRequestEntityDataMapper;
    private final Gson mGson = new Gson();
    private final Context mContext;

    public RequestDataRepository(ResourceManager resourceManager,
                                 RequestEntityDataMapper requestEntityDataMapper, Context context) {
        mResourceManager = resourceManager;
        mRequestEntityDataMapper = requestEntityDataMapper;
        mContext = context;
    }

    @Override
    public Observable<Request> request(final long id) {
        return Observable.create(new Observable.OnSubscribe<RequestEntity>() {
            @Override
            public void call(Subscriber<? super RequestEntity> subscriber) {
                try {
                    String resName = "request_" + id;
                    int resId = mContext.getResources().getIdentifier(
                            resName,
                            "raw",
                            mContext.getPackageName()
                    );
                    String jsonString = mResourceManager.readRawResource(resId);
                    if (jsonString != null) {
                        RequestEntity entity = mGson.fromJson(jsonString, RequestEntity.class);
                        subscriber.onNext(entity);
                        subscriber.onCompleted();
                    }
                } catch (Resources.NotFoundException e) {
                    subscriber.onError(e);
                }
            }
        }).map(new Func1<RequestEntity, Request>() {
            @Override
            public Request call(RequestEntity requestEntity) {
                return mRequestEntityDataMapper.transform(requestEntity);
            }
        });
    }

    @Override
    public Observable<List<Request>> requests() {
        return Observable.create(new Observable.OnSubscribe<List<RequestEntity>>() {
            @Override
            public void call(Subscriber<? super List<RequestEntity>> subscriber) {
                try {
                    String jsonString = mResourceManager.readRawResource(R.raw.dummy);
                    if (jsonString != null) {
                        Type type = new TypeToken<List<RequestEntity>>() {
                        }.getType();
                        List<RequestEntity> entities = mGson.fromJson(jsonString, type);
                        subscriber.onNext(entities);
                        subscriber.onCompleted();
                    }
                } catch (Resources.NotFoundException e) {
                    subscriber.onError(e);
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
