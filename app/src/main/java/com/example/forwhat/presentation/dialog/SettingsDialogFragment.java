package com.example.forwhat.presentation.dialog;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.forwhat.R;
import com.example.forwhat.databinding.SettingsFragmentBinding;
import com.example.forwhat.domain.common.enums.GenTypes;
import com.example.forwhat.presentation.viewModels.mainViewModel.MainViewModel;
import com.example.forwhat.presentation.viewModels.mainViewModel.MainViewModelConstruct;

import javax.inject.Inject;

public class SettingsDialogFragment extends DialogFragment {

    private MainViewModel mainViewModel;
    @Inject
    public MainViewModelConstruct mainViewModelConstruct;

    private SettingsFragmentBinding binding;

    private TextWatcher userNameWatcher;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogTheme);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = SettingsFragmentBinding.inflate(inflater, container, false);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        initTextWatcher();
        setupObservers();
        setupListeners();

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        setDialogSize();
    }

    private void initTextWatcher() {
        userNameWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s != null && mainViewModel != null) {
                    mainViewModel.setUserName(s.toString());
                }
            }
        };
    }

    private void setupObservers() {
        mainViewModel.getUserName().observe(getViewLifecycleOwner(), userName -> {
            if (userName != null && !userName.equals(binding.userName.getText().toString())) {
                binding.userName.removeTextChangedListener(userNameWatcher);
                binding.userName.setText(userName);
                binding.userName.addTextChangedListener(userNameWatcher);
            }
        });

        mainViewModel.getIsPgr().observe(getViewLifecycleOwner(), isPgr -> {
            binding.isPgr.setOnCheckedChangeListener(null);
            binding.isPgr.setChecked(isPgr);
            binding.isPgr.setOnCheckedChangeListener(isPgrListener);
        });

        mainViewModel.getPromt().observe(getViewLifecycleOwner(), this::updateSelectedGenType);
    }

    private void updateSelectedGenType(GenTypes genType) {
        if (genType == null || binding == null) return;

        binding.radioGroup.setOnCheckedChangeListener(null);

        switch (genType) {
            case MOTIVATION:
                binding.radioGroup.check(R.id.radio_motivation);
                break;
            case JOKE:
                binding.radioGroup.check(R.id.radio_jokes);
                break;
            case APPROACH:
                binding.radioGroup.check(R.id.radio_appoach);
                break;
        }

        updateRadioButtonsColor();
        binding.radioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    private void updateRadioButtonsColor() {
        int selectedColor = ContextCompat.getColor(requireContext(), R.color.brown);
        int unselectedColor = ContextCompat.getColor(requireContext(), R.color.light_brown);

        binding.radioMotivation.setTextColor(
                binding.radioMotivation.isChecked() ? selectedColor : unselectedColor);
        binding.radioJokes.setTextColor(
                binding.radioJokes.isChecked() ? selectedColor : unselectedColor);
        binding.radioAppoach.setTextColor(
                binding.radioAppoach.isChecked() ? selectedColor : unselectedColor);
    }

    private final CompoundButton.OnCheckedChangeListener isPgrListener =
            (buttonView, isChecked) -> mainViewModel.setIsPgr(isChecked);

    private final RadioGroup.OnCheckedChangeListener radioGroupListener = (group, checkedId) -> {
        updateRadioButtonsColor();

        if (checkedId == R.id.radio_motivation) {
            mainViewModel.setPromt(GenTypes.MOTIVATION);
        } else if (checkedId == R.id.radio_jokes) {
            mainViewModel.setPromt(GenTypes.JOKE);
        } else if (checkedId == R.id.radio_appoach) {
            mainViewModel.setPromt(GenTypes.APPROACH);
        }
    };

    private void setupListeners() {
        binding.userName.addTextChangedListener(userNameWatcher);
        binding.isPgr.setOnCheckedChangeListener(isPgrListener);
        binding.radioGroup.setOnCheckedChangeListener(radioGroupListener);
    }

    @Override
    public void onDestroyView() {
        binding.userName.removeTextChangedListener(userNameWatcher);
        binding.isPgr.setOnCheckedChangeListener(null);
        binding.radioGroup.setOnCheckedChangeListener(null);
        binding = null;
        super.onDestroyView();
    }

    private void setDialogSize() {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            Window window = dialog.getWindow();
            DisplayMetrics metrics = new DisplayMetrics();
            requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);

            int width = (int) (metrics.widthPixels * 0.9);
            window.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
}