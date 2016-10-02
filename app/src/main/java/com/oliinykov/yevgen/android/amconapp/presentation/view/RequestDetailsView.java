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

package com.oliinykov.yevgen.android.amconapp.presentation.view;

import com.oliinykov.yevgen.android.amconapp.presentation.model.RequestModel;

/**
 * A view that represents details of particular {@link RequestModel}.
 */

public interface RequestDetailsView {

    /**
     * Render a request in the UI
     *
     * @param requestModel The {@link RequestModel} that will be shown.
     */
    void renderRequest(RequestModel requestModel);

}
