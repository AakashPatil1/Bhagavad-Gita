package com.example.bhagavadgita.Local;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class GsonHelper {

    private static final Gson gson = new Gson();

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static <T> T fromJson(String jsonString, Class<T> classOfT) {
        return gson.fromJson(jsonString, classOfT);
    }

    public static <T> List<T> fromJsonList(String jsonString, Type listType) {
        return gson.fromJson(jsonString, listType);
    }
}
