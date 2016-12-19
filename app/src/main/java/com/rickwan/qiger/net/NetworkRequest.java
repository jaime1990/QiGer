package com.rickwan.qiger.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequest {

    public static Interfaces service;

    public static Interfaces getInstance() {
        if (service == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addNetworkInterceptor(intercepeter)
                    .cookieJar(new CookieJar() {
                        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

                        @Override
                        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                            cookieStore.put(url, cookies);
                        }

                        @Override
                        public List<Cookie> loadForRequest(HttpUrl url) {
                            List<Cookie> cookies = cookieStore.get(url);
                            return cookies != null ? cookies : new ArrayList<Cookie>();
                        }
                    }).readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build();

            GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            Gson gson = builder.create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Interfaces.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            service = retrofit.create(Interfaces.class);
        }

        return service;
    }

    private static Interceptor intercepeter = new Interceptor() {

        private String session;

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (TextUtils.isEmpty(session)) {
                Response response = chain.proceed(originalRequest);
//                originalRequest.url().toString().contains("getCaptcha")
                if (session == null) {
                    String ss = response.header("Set-Cookie");
                    if (!TextUtils.isEmpty(ss)) {
                        session = ss.split(";")[0];
                    }

                }
                return response;
            }
            Request authorised = originalRequest.newBuilder()
                    .header("Cookie", session)
                    .build();


            return chain.proceed(authorised);
        }
    };


}