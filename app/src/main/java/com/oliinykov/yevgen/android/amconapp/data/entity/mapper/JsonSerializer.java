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

package com.oliinykov.yevgen.android.amconapp.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Class used to as Serializer/Deserializer for entities.
 */

public class JsonSerializer<T> {

    private final Gson mGson = new Gson();

    public T deserializeEntity(String jsonString, Type type) throws JsonSyntaxException {
        return mGson.fromJson(jsonString, type);
    }

    public List<T> deserializeEntityList(String jsonString, Type type) throws JsonSyntaxException {
        return mGson.fromJson(jsonString, type);
    }

}
