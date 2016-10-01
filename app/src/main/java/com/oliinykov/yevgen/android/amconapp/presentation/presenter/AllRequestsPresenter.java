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

package com.oliinykov.yevgen.android.amconapp.presentation.presenter;

import com.oliinykov.yevgen.android.amconapp.domain.Request;
import com.oliinykov.yevgen.android.amconapp.domain.interactor.DefaultSubscriber;
import com.oliinykov.yevgen.android.amconapp.domain.interactor.GetAllRequests;
import com.oliinykov.yevgen.android.amconapp.presentation.model.mapper.RequestModelDataMapper;
import com.oliinykov.yevgen.android.amconapp.presentation.view.AllRequestsView;

import java.util.List;

/**
 * Presenter that controls communication between view and model.
 */

public class AllRequestsPresenter extends BasePresenter {

    private final AllRequestsView mView;
    private final GetAllRequests mInteractor;
    private final RequestModelDataMapper mRequestModelDataMapper;

    public AllRequestsPresenter(AllRequestsView view, GetAllRequests interactor,
                                RequestModelDataMapper requestModelDataMapper) {
        mView = view;
        mInteractor = interactor;
        mRequestModelDataMapper = requestModelDataMapper;
    }

    public void getAllRequests() {
        setSubscription(mInteractor.getObservable().subscribe(new AllRequestsSubscriber()));
        // show progress indicator
    }

    private class AllRequestsSubscriber extends DefaultSubscriber<List<Request>> {
        @Override
        public void onCompleted() {
            // hide progress indicator
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            // hide progress indicator
            // show toast or snackbar with notification
        }

        @Override
        public void onNext(List<Request> requests) {
            mView.renderRequests(mRequestModelDataMapper.transform(requests));
        }
    }
}
