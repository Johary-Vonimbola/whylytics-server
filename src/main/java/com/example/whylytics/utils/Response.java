package com.example.whylytics.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response<T> {
    private boolean success;
    private String message;
    private T data;
    private String[] errors;
}
