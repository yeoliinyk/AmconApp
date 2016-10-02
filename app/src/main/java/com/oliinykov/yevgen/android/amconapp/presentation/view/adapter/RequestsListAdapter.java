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
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.oliinykov.yevgen.android.amconapp.R;
import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter that manages a collection of {@link RequestModel}.
 */

public class RequestsListAdapter extends ArrayAdapter<RequestModel> {

    private final Context mContext;
    private RequestsRecyclerAdapter.OnItemClickListener mOnItemClickListener;

    public RequestsListAdapter(Context context) {
        super(context, 0, new ArrayList<RequestModel>());
        mContext = context;
    }

    public RequestsListAdapter(Context context, RequestsRecyclerAdapter.OnItemClickListener
            listener) {
        super(context, 0, new ArrayList<RequestModel>());
        mContext = context;
        mOnItemClickListener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        RequestModel requestModel = getItem(position);
        RequestsRecyclerAdapter.RequestViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext)
                    .inflate(R.layout.row_request, parent, false);
            holder = new RequestsRecyclerAdapter.RequestViewHolder(convertView, mContext);
            convertView.setTag(holder);
        } else {
            holder = (RequestsRecyclerAdapter.RequestViewHolder) convertView.getTag();
        }
        holder.bind(requestModel, mOnItemClickListener);
        return convertView;
    }

    public void updateData(List<RequestModel> data) {
        clear();
        if (data != null && !data.isEmpty()) {
            addAll(data);
            notifyDataSetChanged();
        }
    }
}
