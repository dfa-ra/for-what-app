package com.example.forwhat.injection.di;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    @Provides
    @Singleton  // Если нужно единный экземпляр на всё приложение
    public SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
    }
}
