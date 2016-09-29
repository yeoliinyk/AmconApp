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

package com.oliinykov.yevgen.android.amconapp.presentation.view.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Adapter that manages switching between tabs in
 * {@link com.oliinykov.yevgen.android.amconapp.presentation.view.RequestTabView}
 */

public class RequestPagerAdapter extends PagerAdapter {

    private Context mContext;

    public RequestPagerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RequestPagerEnum requestPagerEnum = RequestPagerEnum.values()[position];
        View view = LayoutInflater.from(mContext).inflate(
                requestPagerEnum.getLayoutResId(),
                container,
                false
        );
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        RequestPagerEnum requestPagerEnum = RequestPagerEnum.values()[position];
        return mContext.getString(requestPagerEnum.getTitleResId());
    }

    @Override
    public int getCount() {
        return RequestPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
