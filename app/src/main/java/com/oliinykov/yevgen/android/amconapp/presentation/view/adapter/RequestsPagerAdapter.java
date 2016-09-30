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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;
import com.oliinykov.yevgen.android.amconapp.presentation.view.AllRequestsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that manages switching between tabs in
 * {@link AllRequestsView}
 */

public class RequestsPagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<RequestsRecyclerAdapter> mAdapterList;

    public RequestsPagerAdapter(Context context) {
        this.mContext = context;
        this.mAdapterList = new ArrayList<>();
    }

    public void setRequestsList(List<RequestModel> requestsList) {
        for (RequestsRecyclerAdapter adapter : mAdapterList) {
            adapter.setRequestsList(requestsList);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RequestsPagerEnum requestsPagerEnum = RequestsPagerEnum.values()[position];
        RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(mContext).inflate(
                requestsPagerEnum.getLayoutResId(),
                container,
                false
        );
        RequestsRecyclerAdapter requestsRecyclerAdapter = new RequestsRecyclerAdapter(mContext);
        recyclerView.setAdapter(requestsRecyclerAdapter);
        mAdapterList.add(requestsRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
//        view.setTag("requestsTab" + position);
        container.addView(recyclerView);
        Log.v(RequestsPagerAdapter.class.getSimpleName(), "instantiate item" + position);
        return recyclerView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.v(RequestsPagerAdapter.class.getSimpleName(), "destroy item" + position);
        container.removeView((View) object);
        mAdapterList.remove(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        RequestsPagerEnum requestsPagerEnum = RequestsPagerEnum.values()[position];
        return mContext.getString(requestsPagerEnum.getTitleResId());
    }

    @Override
    public int getCount() {
        return RequestsPagerEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
