package com.example.forwhat.domain.common.enums;

import lombok.Getter;

@Getter
public enum GenTypes {
    MOTIVATION("Напиши одно мативационное предложение для меня (без лишних символов)"),
    APPROACH("Напиши подкат с которой можно начать лёгкую роамнтическую интрижку с девушкой."),
    JOKE("Напиши анекдот!");

    final String promt;

    GenTypes(String promt){
        this.promt = "Предоставь ответ в виде одного предложения без лишних эмодзи и т.д! " + promt;
    }
}
