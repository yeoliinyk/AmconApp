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

package com.oliinykov.yevgen.android.amconapp.presentation.model.mapper;

import com.oliinykov.yevgen.android.amconapp.domain.Request;
import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper class used to transform {@link Request} in the domain layer to {@link RequestModel} in the
 * presentation layer.
 */

public class RequestModelDataMapper {

    public RequestModel transform(Request request) {
        RequestModel requestModel = null;
        if (request != null) {
            requestModel = new RequestModel(request.getId());
            requestModel.setTitle(request.getTitle());
            requestModel.setHash(request.getHash());
            requestModel.setCreated(request.getCreated());
            requestModel.setEstimation(request.getEstimation());
            requestModel.setLikes(request.getLikes());
            requestModel.setStatus(request.getStatus());
        }
        return requestModel;
    }

    public List<RequestModel> transform(List<Request> requestList) {
        List<RequestModel> requestModelList = new ArrayList<>();
        RequestModel requestModel;
        for (Request request : requestList) {
            requestModel = transform(request);
            if (requestModel != null) {
                requestModelList.add(requestModel);
            }
        }

        return requestModelList;
    }

}
