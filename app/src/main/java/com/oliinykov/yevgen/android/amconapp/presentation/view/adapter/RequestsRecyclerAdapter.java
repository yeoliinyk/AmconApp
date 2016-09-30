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
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oliinykov.yevgen.android.amconapp.R;
import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of RequestModel.
 */

public class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsRecyclerAdapter
        .RequestViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private List<RequestModel> mRequestsList;

    public RequestsRecyclerAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mRequestsList = Collections.emptyList();
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View row = mLayoutInflater.inflate(R.layout.row_request, parent, false);
        return new RequestViewHolder(row);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        final RequestModel requestModel = mRequestsList.get(position);
        holder.title.setText(requestModel.getTitle());
        holder.hash.setText(requestModel.getHash());
        holder.created.setText(requestModel.getCreated());
        holder.estimation.setText(requestModel.getEstimation());
        holder.likes.setText(requestModel.getLikes());

    }

    @Override
    public int getItemCount() {
        return mRequestsList.size();
    }

    public void setRequestsList(List<RequestModel> requestsList) {
        if (requestsList != null) {
            mRequestsList = requestsList;
            notifyDataSetChanged();
        }
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.request_title) TextView title;
        @BindView(R.id.request_created) TextView created;
        @BindView(R.id.request_hash) TextView hash;
        @BindView(R.id.request_estimation) TextView estimation;
        @BindView(R.id.request_likes) TextView likes;

        RequestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
