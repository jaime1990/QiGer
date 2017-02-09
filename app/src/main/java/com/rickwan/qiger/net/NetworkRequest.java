package com.rickwan.qiger.net;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rickwan.qiger.BuildConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkRequest {

    public static Interfaces service;

    public static Interfaces getInstance() {
        if (service == null) {

            OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpBuilder.addNetworkInterceptor(httpLoggingInterceptor);
            }
            okHttpBuilder.readTimeout(60, TimeUnit.SECONDS);
            okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
            okHttpBuilder.addNetworkInterceptor(intercepeter);

            GsonBuilder builder = new GsonBuilder();
            builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            Gson gson = builder.create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Interfaces.BASE_URL)
                    .client(okHttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
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
