package com.example.forwhat.domain.usecase.openai;

import android.util.Log;

import com.example.forwhat.domain.common.interfaces.IRetrofit;
import com.example.forwhat.domain.common.models.openaiModels.ChatRequest;
import com.example.forwhat.domain.common.models.openaiModels.DeepSeekMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class OpenaiUseCase {

    private final IRetrofit iRetrofit;

    public OpenaiUseCase(IRetrofit iRetrofit){
        this.iRetrofit = iRetrofit;
    }

    public Single<String> execute(String apiKey, String message){
        DeepSeekMessage deepSeekMessage = new DeepSeekMessage("user", message);
        List<DeepSeekMessage> messageList = new ArrayList<>();
        messageList.add(deepSeekMessage);
        ChatRequest chatRequest = new ChatRequest("deepseek-chat", messageList, false);
        Log.d("aa88", "OpenaiUseCase execute\n " + chatRequest.toString());
        return iRetrofit.getIOpenaiApi().chatCompletion(
                apiKey,
                chatRequest
        )
                .map(response -> {
                    if (response.isSuccessful()) return response.body();

                    Log.d("aa88", "OpenaiUseCase response: " + response.message());
                    try {
                        throw new IOException(response.message());
                    } catch (IOException e) {
                        throw Exceptions.propagate(e);
                    }
                })
                .map(body -> body.choices.get(0).deepSeekMessage.content)
                .subscribeOn(Schedulers.io());
    }
}
