package com.example.forwhat.data.retrofitClient;

import com.example.forwhat.data.retrofitClient.remoteApi.IOpenaiApi;
import com.example.forwhat.domain.common.interfaces.IRetrofit;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImpl implements IRetrofit {

    private IOpenaiApi iOpenaiApi;

    @Inject
    public RetrofitImpl() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.deepseek.com/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        this.iOpenaiApi = retrofit.create(IOpenaiApi.class);
    }

    @Override
    public IOpenaiApi getIOpenaiApi() {
        return this.iOpenaiApi;
    }
}
