package com.example.whylytics.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.genai.types.GenerateContentResponse;

import com.google.genai.Client;

@Service
public class AIService {
    @Value("${gemini.api.key}")
    private String apiKey;

    public String ask(String prompt) {

        Client client = Client.builder()
                .apiKey(apiKey)
                .build();

        GenerateContentResponse response =
                client.models.generateContent(
                        "gemini-3-flash-preview",
                        prompt,
                        null
                );

        return response.text();
    }
}
