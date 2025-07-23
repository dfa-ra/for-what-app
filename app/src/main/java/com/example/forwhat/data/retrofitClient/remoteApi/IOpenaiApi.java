package com.example.forwhat.data.retrofitClient.remoteApi;

import com.example.forwhat.domain.common.models.openaiModels.ChatRequest;
import com.example.forwhat.domain.common.models.openaiModels.ChatResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IOpenaiApi {
    @POST("chat/completions")
    Single<Response<ChatResponse>> chatCompletion(@Header("Authorization") String apiKey, @Body ChatRequest request);
}
