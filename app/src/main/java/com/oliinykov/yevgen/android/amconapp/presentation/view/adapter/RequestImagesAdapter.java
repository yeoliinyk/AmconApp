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
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.oliinykov.yevgen.android.amconapp.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter that manages a collection of images by their URLs.
 */

public class RequestImagesAdapter extends RecyclerView.Adapter<RequestImagesAdapter
        .ImageViewHolder> {

    private final Context mContext;
    private List<String> mImagesUrlList = Collections.emptyList();

    public RequestImagesAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View row = LayoutInflater.from(mContext).inflate(R.layout.row_image, parent, false);
        return new ImageViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        String url = mImagesUrlList.get(position);
        Uri uri = Uri.parse(url);
        Picasso.with(mContext).load(uri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mImagesUrlList.size();
    }

    public void updateData(List<String> imagesUrlList) {
        if (imagesUrlList != null) {
            mImagesUrlList = imagesUrlList;
            notifyDataSetChanged();
        }
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview) ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
