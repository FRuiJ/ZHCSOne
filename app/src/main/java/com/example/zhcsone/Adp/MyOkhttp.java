package com.example.zhcsone.Adp;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MyOkhttp {

    public static void getToken(String address, String token, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        Request request = new Request.Builder()
                .url(address)
                .addHeader("Authorization", "Bearer " + token)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
