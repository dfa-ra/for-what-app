package com.example.forwhat.presentation.viewModels.mainViewModel;

import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.forwhat.domain.common.enums.GenTypes;
import com.example.forwhat.domain.usecase.openai.OpenaiUseCase;

import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

public class MainViewModel extends ViewModel {
    private final OpenaiUseCase openaiUseCase;

    private final SharedPreferences sharedPreferences;
    private final MutableLiveData<String> motivationMessage = new MutableLiveData<>();

    private final MutableLiveData<String> userName = new MutableLiveData<>();
    private final MutableLiveData<GenTypes> promt = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isPgr = new MutableLiveData<>(false);
    private final MutableLiveData<String> errorText = new MutableLiveData<>();

    public MainViewModel(OpenaiUseCase openaiUseCase, SharedPreferences sharedPreferences) {
        this.openaiUseCase = openaiUseCase;
        this.sharedPreferences = sharedPreferences;
        loadUserData();
    }

    private void loadUserData() {
        this.userName.setValue(sharedPreferences.getString("userName", ""));
        this.isPgr.setValue(sharedPreferences.getBoolean("isPgr", false));
        promt.setValue(GenTypes.APPROACH);
    }

    public String getPromtText(){
        String result_promt = Objects.requireNonNull(promt.getValue()).getPromt();
        if (Boolean.TRUE.equals(isPgr.getValue()))
            result_promt = "ПИШИ используя МАТ как будто тебе лет 20." + result_promt;
        result_promt += " Меня зовут " + userName.getValue();
        return result_promt;
    }

    public void getNewGeneration(){
        this.openaiUseCase.execute("Bearer <токен я не дам>", getPromtText())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("aa88", "promt: " + promt);
                    }

                    @Override
                    public void onSuccess(@NonNull String message) {
                        Log.d("aa88", "result: " + message);
                        motivationMessage.setValue(message);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        errorText.setValue("Случилась ошибка при генерации :(");
                    }
                });
    }

    public LiveData<String> getMotivationMessage() {
        return motivationMessage;
    }

    public LiveData<String> getErrorText() {
        return errorText;
    }

    public LiveData<Boolean> getIsPgr() {
        return isPgr;
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<GenTypes> getPromt() {
        return promt;
    }

    public void setPromt(GenTypes genTypes){
        promt.setValue(genTypes);
    }

    public void setUserName(String userName){
        this.userName.setValue(userName);
    }

    public void setIsPgr(Boolean isPgr){
        this.isPgr.setValue(isPgr);
    }

}
