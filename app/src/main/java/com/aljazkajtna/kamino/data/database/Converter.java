package com.aljazkajtna.kamino.data.database;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converter {

    @TypeConverter
    public static List<String> fromJsonString(String json) {
        Type listType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(json, listType);
    }

    @TypeConverter
    public static String fromList(List<String> list) {
        return new Gson().toJson(list);
    }
}
