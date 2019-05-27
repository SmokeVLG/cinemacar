package com.cinemacar.helpers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.cinemacar.R;

import java.util.HashMap;
import java.util.Map;

public class Const {

    public static final String INTERNET_NOT_FOUND = "internet_not_found";
    public static final String FILMS_NOT_FOUND = "films_not_found";
    public static final String URL = "URL";

    private static Map<String, Integer> previewInfoMap;
    private static Map<String, String> infoMessage;

    static {
        previewInfoMap = new HashMap<>();
        previewInfoMap.put(INTERNET_NOT_FOUND, R.drawable.ic_no_internet);
        previewInfoMap.put(FILMS_NOT_FOUND, R.drawable.ic_not_found);
    }

    static {
        infoMessage = new HashMap<>();
        infoMessage.put(INTERNET_NOT_FOUND, "Отсутствует интернет");
        infoMessage.put(FILMS_NOT_FOUND, "Фильмы не найдены");
    }

    public static int getPreview(String key) {
        return previewInfoMap.get(key);
    }

    public static String getMessage(String key) {
        return infoMessage.get(key);
    }

}
