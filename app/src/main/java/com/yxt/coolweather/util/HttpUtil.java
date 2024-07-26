package com.yxt.coolweather.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtil {

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
