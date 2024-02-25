package com.xiongan.common;

import okhttp3.*;

import java.io.IOException;
import java.util.Map;

public class OkHttpClient {
    private static okhttp3.OkHttpClient OK_HTTP_CLIENT;

    static {
            OK_HTTP_CLIENT = new okhttp3.OkHttpClient.Builder().build();
    }
    public static String get(String url) {
        return execute(new Request.Builder().url(url).get().build());
    }

    public static String post(String url, String jsonString) {
        RequestBody body = RequestBody.create(jsonString, MediaType.parse("application/json; charset=utf-8"));
        return execute(new Request.Builder().url(url).post(body).build());
    }

    public static String postForm(String url, Map<String, Object> params) {
        url = url.split("\\?")[0];
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue().toString());
        }
        MultipartBody body = builder.build();
        return execute(new Request.Builder().url(url).post(body).build());
    }

    private static String execute(Request request) {
        String result = null;
        Response response = null;
        try {
            response = OK_HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                response.body().close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != response && null != response.body()) {
                response.body().close();
            }
        }
        return result;
    }
}
