package com.example.forwhat.injection.di.data;


import com.example.forwhat.data.retrofitClient.RetrofitImpl;
import com.example.forwhat.domain.common.interfaces.IRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    public IRetrofit provideRetrofit(){
        return new RetrofitImpl();
    }

}
