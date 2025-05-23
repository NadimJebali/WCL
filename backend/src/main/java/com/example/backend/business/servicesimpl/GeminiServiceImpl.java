package com.example.backend.business.servicesimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.backend.business.services.GeminiService;

@Service
public class GeminiServiceImpl implements GeminiService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;

    private final WebClient webClient;

    public GeminiServiceImpl(WebClient.Builder webClient) {
        this.webClient = webClient.build();
    }


    @Override
    public String getAnswer(String question) {
        Map<String, Object> requestBody = Map.of(
            "contents", new Object[] {
                    Map.of("parts", new Object[] {
                            Map.of("text", question)
                    } )
            }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }
    
}
