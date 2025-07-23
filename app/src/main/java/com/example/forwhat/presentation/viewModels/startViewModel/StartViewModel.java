package com.example.forwhat.presentation.viewModels.startViewModel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StartViewModel extends ViewModel {
    private final SharedPreferences sharedPreferences;


    private final MutableLiveData<Boolean> _isRegistered = new MutableLiveData<>();

    public LiveData<Boolean> isRegistered() { return _isRegistered; }

    public StartViewModel(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        checkRegistration();
    }

    public void checkRegistration() {
        boolean registered = sharedPreferences.getBoolean("isRegistered", false);
        _isRegistered.setValue(registered);
        _isRegistered.setValue(false);
    }


    public void completeRegistration(String name, Boolean isPgr) {
        sharedPreferences.edit().putBoolean("isRegistered", true).apply();
        sharedPreferences.edit().putString("userName", name).apply();
        sharedPreferences.edit().putBoolean("isPgr", isPgr).apply();
        _isRegistered.postValue(true);
    }
}
