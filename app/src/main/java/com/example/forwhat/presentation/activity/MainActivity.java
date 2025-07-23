package com.example.forwhat.presentation.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.forwhat.databinding.MainActivityBinding;
import com.example.forwhat.injection.app.App;
import com.example.forwhat.presentation.dialog.SettingsDialogFragment;
import com.example.forwhat.presentation.viewModels.mainViewModel.MainViewModel;
import com.example.forwhat.presentation.viewModels.mainViewModel.MainViewModelConstruct;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mainViewModel;

    @Inject
    public MainViewModelConstruct mainViewModelConstruct;

    private MainActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((App) this.getApplicationContext()).appComponent.inject(this);

        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this, mainViewModelConstruct).get(MainViewModel.class);

        binding.forWhat.setText("Я НЕЙРОМОТИВАТОР\nТКНИ В МЕНЯ");

        setupListeners();
        setupObservers();

    }



    private void setupListeners(){
        binding.forWhat.setOnClickListener(clc -> {
            Log.d("aa88", "start generation");
            mainViewModel.getNewGeneration();
        });

        binding.settingsButton.setOnClickListener(clc -> {
            SettingsDialogFragment dialog = new SettingsDialogFragment();
            dialog.show(getSupportFragmentManager(), "my_dialog");
        });

    }

    private void setupObservers(){
        // наблюдатель за изменение текста
        mainViewModel.getMotivationMessage().observe(this, message -> {
            Log.d("aa88", "change: " + message);
            binding.forWhat.setText(message);
        });
    }

    @Override
    protected void onDestroy() {
        binding.forWhat.setOnClickListener(null);
        binding.settingsButton.setOnClickListener(null);
        binding = null;
        super.onDestroy();
    }
}
