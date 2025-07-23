package com.example.forwhat.domain.common.models.openaiModels;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeepSeekMessage {

    @SerializedName("role")
    public String role;

    @SerializedName("content")
    public String content;

    @Override
    public String toString() {
        return "DeepSeekMessage{" +
                "role='" + role + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
