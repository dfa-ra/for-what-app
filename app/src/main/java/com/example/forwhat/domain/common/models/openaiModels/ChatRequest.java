package com.example.forwhat.domain.common.models.openaiModels;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChatRequest {

    @SerializedName("model")
    public String model;

    @SerializedName("messages")
    public List<DeepSeekMessage> deepSeekMessage;

    @SerializedName("stream")
    public boolean stream;

    @Override
    public String toString() {
        return "ChatRequest{" +
                "model='" + model + '\'' +
                ", deepSeekMessage=" + deepSeekMessage.toString() +
                ", stream=" + stream +
                '}';
    }
}
