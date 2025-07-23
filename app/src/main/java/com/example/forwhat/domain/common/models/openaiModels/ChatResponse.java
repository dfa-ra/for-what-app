package com.example.forwhat.domain.common.models.openaiModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatResponse {

    @SerializedName("model")
    private String model;

    @SerializedName("choices")
    public List<Choice> choices;

}
