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
import com.oliinykov.yevgen.android.amconapp.presentation.view.AllRequestsView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.oliinykov.yevgen.android.amconapp.presentation.view.adapter.RequestsPagerEnum
        .INWORK;

/**
 * Adapter that manages views while switching between tabs in {@link AllRequestsView}.
 */

public class RequestsPagerAdapter extends PagerAdapter {

    private final Context mContext;
    private final RequestsRecyclerAdapter.OnItemClickListener mOnItemClickListener;
    private Map<RequestsPagerEnum, List<RequestModel>> mRequestsMap = new HashMap<>();


    public RequestsPagerAdapter(Context context,
                                RequestsRecyclerAdapter.OnItemClickListener listener) {
        mContext = context;
        mOnItemClickListener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        RequestsPagerEnum requestsPagerEnum = RequestsPagerEnum.values()[position];
        View view = LayoutInflater.from(mContext).inflate(
                requestsPagerEnum.getLayoutResId(),
                container,
                false
        );

        switch (requestsPagerEnum) {
            case WAITING: {
                RequestsListAdapter adapter = new RequestsListAdapter(mContext);
                ((ListView) view).setAdapter(adapter);
                adapter.updateData(mRequestsMap.get(RequestsPagerEnum.WAITING));
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
                adapter.updateData(mRequestsMap.get(requestsPagerEnum));
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

    public void setRequestsList(List<RequestModel> requestsList) {
        buildRequestsMap(requestsList);
        notifyDataSetChanged();
    }

    private void buildRequestsMap(List<RequestModel> requestsList) {
        ArrayList<RequestModel> waitingRequests = new ArrayList<>();
        ArrayList<RequestModel> inworkRequests = new ArrayList<>();
        ArrayList<RequestModel> doneRequests = new ArrayList<>();
        for (RequestModel requestModel : requestsList) {
            String status = requestModel.getStatus();
            switch (status) {
                case "waiting": {
                    waitingRequests.add(requestModel);
                    break;
                }
                case "inwork": {
                    inworkRequests.add(requestModel);
                    break;
                }
                case "done": {
                    doneRequests.add(requestModel);
                    break;
                }
            }
        }
        mRequestsMap.put(RequestsPagerEnum.WAITING, waitingRequests);
        mRequestsMap.put(INWORK, inworkRequests);
        mRequestsMap.put(RequestsPagerEnum.DONE, doneRequests);
    }

}
