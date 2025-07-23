package com.example.forwhat.presentation.viewModels.mainViewModel;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.forwhat.domain.usecase.openai.OpenaiUseCase;

import javax.inject.Inject;

public class MainViewModelConstruct extends ViewModelProvider.NewInstanceFactory {

    private final OpenaiUseCase openaiUseCase;
    private final SharedPreferences sharedPreferences;

    @Inject
    public MainViewModelConstruct(OpenaiUseCase openaiUseCase, SharedPreferences sharedPreferences) {
        this.openaiUseCase = openaiUseCase;
        this.sharedPreferences = sharedPreferences;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(
                openaiUseCase,
                sharedPreferences
        );
    }
}
