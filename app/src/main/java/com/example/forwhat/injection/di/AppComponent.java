package com.example.forwhat.injection.di;

import android.content.Context;

import com.example.forwhat.injection.di.data.DataModule;
import com.example.forwhat.injection.di.domain.OpenaiModule;
import com.example.forwhat.presentation.activity.MainActivity;
import com.example.forwhat.presentation.activity.StartActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;




@Singleton
@Component(modules = {
        OpenaiModule.class,
        DataModule.class,
        SharedPreferencesModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        AppComponent.Builder context (Context context);

        AppComponent build();

    }

    void inject(MainActivity mainActivity);
    void inject(StartActivity startActivity);
}
