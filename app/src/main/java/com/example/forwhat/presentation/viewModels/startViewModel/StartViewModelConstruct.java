package com.example.forwhat.presentation.viewModels.startViewModel;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class StartViewModelConstruct extends ViewModelProvider.NewInstanceFactory {
    private final SharedPreferences sharedPreferences;

    @Inject
    public StartViewModelConstruct(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StartViewModel(
                sharedPreferences
        );
    }
}
