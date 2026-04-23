package com.example.habit.service;

import org.springframework.stereotype.Service;

@Service
public class InsightMessageServiceImpl implements InsightMessageService 
{

    @Override
    public String generateMessage(String summary) 
    {
        // Example prompt sent to AI API
        String prompt =
            "Generate a short motivational feedback based on this summary:\n"+summary;

        // ---- REAL AI CALL IDEA ----
        // String aiResponse = openAiClient.call(prompt);
        // return aiResponse;

        // For now (mocked)
        return "You are making steady progress. Try to maintain consistency.";
    }
}