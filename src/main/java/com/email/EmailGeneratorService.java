package com.email;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailGeneratorService {

    private final WebClient webClient;

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String getGeminiApiKey;

    public EmailGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    // --- NEW FEATURES ---

    /**
     * Summarizes the provided email content.
     */
    public String summarizeEmail(EmailRequest emailRequest) {
        String prompt = "Summarize the following email in a few key points:\n\n" + emailRequest.getEmailContent();
        return callGeminiApi(prompt);
    }

    /**
     * Suggests a subject line for the provided email content.
     */
    public String suggestSubject(EmailRequest emailRequest) {
        String prompt = "Based on the following email content, suggest 5 creative and professional subject lines. Return them as a comma-separated list:\n\n" + emailRequest.getEmailContent();
        return callGeminiApi(prompt);
    }

    /**
     * Rephrases the provided email content based on tone and language.
     */
    public String rephraseEmail(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Rephrase and improve the following email content. ");

        if (emailRequest.getTone() != null && !emailRequest.getTone().equalsIgnoreCase("none")) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone. ");
        }

        if (emailRequest.getLanguage() != null && !emailRequest.getLanguage().equalsIgnoreCase("none")) {
            prompt.append("Write it in ").append(emailRequest.getLanguage()).append(". ");
        }

        prompt.append("\n\nOriginal content:\n").append(emailRequest.getEmailContent());
        return callGeminiApi(prompt.toString());
    }

    // --- EXISTING FEATURES (Refactored to use callGeminiApi) ---

    public String generateEmailReply(EmailRequest emailRequest) {
        String prompt = buildReplyPrompt(emailRequest);
        return callGeminiApi(prompt);
    }

    public String generateEmailFromPrompt(String prompt, String tone, String language) {
        StringBuilder fullPrompt = new StringBuilder();
        if (language != null && !language.equalsIgnoreCase("none")) {
            fullPrompt.append("Write the email in ").append(language).append(". ");
        }
        if (tone != null && !tone.equalsIgnoreCase("none")) {
            fullPrompt.append("Use a ").append(tone).append(" tone. ");
        }
        fullPrompt.append("Compose an email based on the following prompt: ").append(prompt);
        return callGeminiApi(fullPrompt.toString());
    }

    // --- PRIVATE HELPER METHODS ---

    /**
     * Central method to call the Gemini API.
     */
    private String callGeminiApi(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", prompt)
                        })
                }
        );

        String response = webClient.post()
                .uri(geminiApiUrl + getGeminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return extractResponseContent(response);
    }

    private String extractResponseContent(String response) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(response);
            return rootNode.path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();
        } catch (Exception e) {
            return "Error processing request: " + e.getMessage();
        }
    }

    private String buildReplyPrompt(EmailRequest emailRequest) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Generate an email reply with the following style:\n");

        if (emailRequest.getTone() != null && !emailRequest.getTone().equalsIgnoreCase("none")) {
            prompt.append("Use a ").append(emailRequest.getTone()).append(" tone.");
        }

        if (emailRequest.getLanguage() != null && !emailRequest.getLanguage().equalsIgnoreCase("none")) {
            prompt.append(" Write it in ").append(emailRequest.getLanguage());
        }

        prompt.append("\nOriginal email content:\n").append(emailRequest.getEmailContent());
        return prompt.toString();
    }
}
