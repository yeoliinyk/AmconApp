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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;
import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestStatus;
import com.oliinykov.yevgen.android.amconapp.presentation.view.AllRequestsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Adapter that manages views while switching between tabs in {@link AllRequestsView}.
 */

public class RequestsPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final RequestsRecyclerAdapter.OnItemClickListener mOnItemClickListener;
    private Map<RequestStatus, List<RequestModel>> mRequestsMap;


    public RequestsPagerAdapter(Context context,
                                RequestsRecyclerAdapter.OnItemClickListener listener) {
        mContext = context;
        mOnItemClickListener = listener;
        mRequestsMap = new HashMap<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RequestStatus requestStatus = RequestStatus.values()[position];
        View view = LayoutInflater.from(mContext).inflate(
                requestStatus.getLayoutResId(),
                container,
                false
        );

        switch (requestStatus) {
            case WAITING: {
                RequestsListAdapter adapter = new RequestsListAdapter(mContext,
                        mOnItemClickListener);
                ((ListView) view).setAdapter(adapter);
                adapter.updateData(mRequestsMap.get(RequestStatus.WAITING));
                break;
            }
            case INWORK: {
                // go through
            }
            case DONE: {
                RequestsRecyclerAdapter adapter = new RequestsRecyclerAdapter(
                        mContext,
                        mOnItemClickListener
                );
                ((RecyclerView) view).setAdapter(adapter);
                ((RecyclerView) view).setLayoutManager(new LinearLayoutManager(mContext));
                adapter.updateData(mRequestsMap.get(requestStatus));
                break;
            }
            default:
                throw new IllegalArgumentException();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        RequestStatus requestStatus = RequestStatus.values()[position];
        return mContext.getString(requestStatus.getTitleResId()).toUpperCase();
    }

    @Override
    public int getCount() {
        return RequestStatus.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void updateData(List<RequestModel> requestsList) {
        mRequestsMap.clear();
        if (requestsList != null && !requestsList.isEmpty()) {
            buildRequestsMap(requestsList);
            notifyDataSetChanged();
        }
    }

    private void buildRequestsMap(List<RequestModel> requestsList) {
        initMap();
        for (RequestModel requestModel : requestsList) {
            mRequestsMap.get(requestModel.getStatus()).add(requestModel);
        }
    }

    private void initMap() {
        for (RequestStatus status : RequestStatus.values()) {
            mRequestsMap.put(status, new ArrayList<RequestModel>());
        }
    }

}
