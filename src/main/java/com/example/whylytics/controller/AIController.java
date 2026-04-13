package com.example.whylytics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.whylytics.dto.Prompt;
import com.example.whylytics.service.AIService;
import com.example.whylytics.utils.Response;

@RestController
@RequestMapping("/api/ai-assistant")
public class AIController {
    @Autowired
    private AIService aiService;


    @PostMapping
    public Response<String> ask(@RequestBody Prompt prompt){
        return new Response<>(true, "AI Response", this.aiService.ask(prompt.getPrompt()), null);
    }

}
