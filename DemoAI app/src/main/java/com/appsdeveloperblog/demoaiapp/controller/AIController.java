package com.appsdeveloperblog.demoaiapp.controller;

import com.appsdeveloperblog.demoaiapp.service.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/analyze")
    public String analyze(@RequestParam String dataPath) {
        try {
            return aiService.performAnalysis(dataPath);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
