package com.example.forwhat.injection.di.domain;

import com.example.forwhat.domain.common.interfaces.IRetrofit;
import com.example.forwhat.domain.usecase.openai.OpenaiUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class OpenaiModule {

    @Provides
    public OpenaiUseCase provideOpenaiUseCase(IRetrofit iRetrofit){
        return new OpenaiUseCase(iRetrofit);
    }

}
