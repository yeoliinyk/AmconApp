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
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oliinykov.yevgen.android.amconapp.R;
import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of {@link RequestModel}.
 */

public class RequestsRecyclerAdapter extends RecyclerView.Adapter<RequestsRecyclerAdapter
        .RequestViewHolder> {

    private final Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<RequestModel> mRequestsList;

    public RequestsRecyclerAdapter(Context context) {
        mContext = context;
    }

    public RequestsRecyclerAdapter(Context context, OnItemClickListener onItemClickListener) {
        mContext = context;
        mOnItemClickListener = onItemClickListener;
        mRequestsList = new ArrayList<>();
    }

    @Override
    public RequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View row = LayoutInflater.from(mContext).inflate(R.layout.row_request, parent, false);
        return new RequestViewHolder(row, mContext);
    }

    @Override
    public void onBindViewHolder(RequestViewHolder holder, int position) {
        holder.bind(mRequestsList.get(position), mOnItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mRequestsList.size();
    }

    public void updateData(List<RequestModel> data) {
        mRequestsList.clear();
        if (data != null && !data.isEmpty()) {
            mRequestsList.addAll(data);
            notifyDataSetChanged();
        }
    }

    public interface OnItemClickListener {
        void onRequestItemClicked(RequestModel requestModel);
    }

    static class RequestViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.request_title) TextView title;
        @BindView(R.id.request_created) TextView created;
        @BindView(R.id.request_hash) TextView hash;
        @BindView(R.id.request_estimation) TextView estimation;
        @BindView(R.id.request_likes) TextView likes;

        private Context mContext;

        RequestViewHolder(View itemView, Context context) {
            super(itemView);
            mContext = context;
            ButterKnife.bind(this, itemView);
        }

        void bind(final RequestModel requestModel, final OnItemClickListener listener) {
            title.setText(requestModel.getTitle());
            hash.setText(requestModel.getHash());
            created.setText(requestModel.getCreated());
            estimation.setText(requestModel.getEstimation());
            likes.setText(requestModel.getLikes());
            likes.setCompoundDrawablesWithIntrinsicBounds(wrapLikesDrawable(), null, null, null);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onRequestItemClicked(requestModel);
                    }
                }
            });
        }

        private Drawable wrapLikesDrawable() {
            Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.ic_thumb_up);
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawable, ContextCompat.getColor(mContext, R.color.colorGray));
            return drawable;
        }
    }
}
