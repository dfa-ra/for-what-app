package com.example.forwhat.injection.app;

import android.app.Application;

import com.example.forwhat.injection.di.AppComponent;
import com.example.forwhat.injection.di.DaggerAppComponent;


public class App extends Application {

    public volatile AppComponent appComponent;

    @Override
    public void onCreate() {

        this.appComponent = DaggerAppComponent
                .builder().context(this.getApplicationContext()).build();

        super.onCreate();


    }

}
