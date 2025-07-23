package com.example.forwhat.presentation.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.forwhat.databinding.StartActivityBinding;
import com.example.forwhat.injection.app.App;
import com.example.forwhat.presentation.viewModels.startViewModel.StartViewModel;
import com.example.forwhat.presentation.viewModels.startViewModel.StartViewModelConstruct;

import javax.inject.Inject;

public class StartActivity extends AppCompatActivity {

    private StartViewModel startViewModel;

    @Inject
    public StartViewModelConstruct startViewModelConstruct;

    protected StartActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) this.getApplicationContext()).appComponent.inject(this);

        binding = StartActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startViewModel = new ViewModelProvider(this, startViewModelConstruct).get(StartViewModel.class);

        setupListeners();
        setupObservers();

    }

    private void setupListeners(){
        binding.myContinue.setOnClickListener(v -> {
            startViewModel.completeRegistration(binding.nameField.getText().toString(), !binding.mySwitch.isChecked());
        });
    }

    private void setupObservers(){
        // наблюдатель был ли пользователь уже зерегестрирован
        startViewModel.isRegistered().observe(this, isRegistered -> {
            if (isRegistered != null && isRegistered) {
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    protected void onDestroy() {
        binding.myContinue.setOnClickListener(null);
        binding = null;
        super.onDestroy();
    }
}
