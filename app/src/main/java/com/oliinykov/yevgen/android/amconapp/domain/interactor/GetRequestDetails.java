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

package com.oliinykov.yevgen.android.amconapp.domain.interactor;

import com.oliinykov.yevgen.android.amconapp.domain.Request;
import com.oliinykov.yevgen.android.amconapp.domain.executor.InteractorExecutor;
import com.oliinykov.yevgen.android.amconapp.domain.executor.MainThread;
import com.oliinykov.yevgen.android.amconapp.domain.repository.RequestRepository;

import rx.Observable;

/**
 * This class is an implementation of {@link Interactor} that represents a use case for
 * retrieving a single {@link Request} by its id.
 */

public class GetRequestDetails extends Interactor<Request> {

    private final RequestRepository mRequestRepository;
    private final long mId;

    public GetRequestDetails(InteractorExecutor interactorExecutor, MainThread mainThread,
                             RequestRepository requestRepository, long id) {
        super(interactorExecutor, mainThread);
        mRequestRepository = requestRepository;
        mId = id;
    }

    @Override
    Observable<Request> buildObservable() {
        return mRequestRepository.request(mId);
    }
}
