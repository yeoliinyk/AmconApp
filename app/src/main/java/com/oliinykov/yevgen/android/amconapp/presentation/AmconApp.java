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

package com.oliinykov.yevgen.android.amconapp.presentation;

import android.app.Application;
import android.content.Context;

import com.oliinykov.yevgen.android.amconapp.BuildConfig;
import com.oliinykov.yevgen.android.amconapp.data.executor.TaskExecutor;
import com.oliinykov.yevgen.android.amconapp.domain.executor.InteractorExecutor;
import com.oliinykov.yevgen.android.amconapp.domain.executor.MainThread;
import com.oliinykov.yevgen.android.amconapp.presentation.executor.UiThread;
import com.squareup.leakcanary.LeakCanary;

/**
 * Main application class.
 */

public class AmconApp extends Application {

    private MainThread mMainThread;
    private InteractorExecutor mInteractorExecutor;

    public static InteractorExecutor getInteractorExecutor(Context context) {
        AmconApp application = (AmconApp) context.getApplicationContext();
        return application.mInteractorExecutor;
    }

    public static MainThread getMainThread(Context context) {
        AmconApp application = (AmconApp) context.getApplicationContext();
        return application.mMainThread;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initSingletones();
        initLeakCanary();
    }

    private void initSingletones() {
        mMainThread = new UiThread();
        mInteractorExecutor = new TaskExecutor();
    }

    private void initLeakCanary() {
        if (BuildConfig.DEBUG) {
            LeakCanary.install(this);
        }
    }
}
