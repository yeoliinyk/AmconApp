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

import com.oliinykov.yevgen.android.amconapp.domain.executor.InteractorExecutor;
import com.oliinykov.yevgen.android.amconapp.domain.executor.MainThread;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * This class represents an execution unit for different interactors (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each {@link Interactor} implementation will return the result using a
 * {@link rx.Observable} that will execute its job in a background thread and will post the
 * result in the UI thread.
 */

public abstract class Interactor<T> {

    private final InteractorExecutor mInteractorExecutor;
    private final MainThread mMainThread;

    public Interactor(InteractorExecutor interactorExecutor, MainThread mainThread) {
        mInteractorExecutor = interactorExecutor;
        mMainThread = mainThread;
    }


    /**
     * Returns modified observable so that its subscriptions happen in background and emission -
     * on main thread.
     */
    public Observable<T> getObservable() {
        return buildObservable()
                .subscribeOn(Schedulers.from(mInteractorExecutor))
                .observeOn(mMainThread.getScheduler());
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current
     * {@link Interactor}.
     */
    abstract Observable<T> buildObservable();
}
