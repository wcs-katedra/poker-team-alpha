package com.wcs.poker.jsonconverter;

import com.google.gson.Gson;

public class JsonConverter<T> {

    private final Gson gson;
    private final Class<T> objectClass;

    public JsonConverter(Class<T> objectClass) {
        this.objectClass = objectClass;
        this.gson = new Gson();
    }

    public T fromJson(String json) {
        return gson.fromJson(json, objectClass);
    }

    public String toJson(T source) {
        return gson.toJson(source, objectClass);
    }

}
