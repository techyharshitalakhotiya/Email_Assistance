package com.email;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class EmailGeneratorController {

    private final EmailGeneratorService emailGeneratorService;

    @PostMapping("/generate")
    public ResponseEntity<String> generateEmail(@RequestBody EmailRequest emailRequest) {
        String response;
        String action = emailRequest.getAction();

        // Check if an action is specified
        if (action != null) {
            switch (action) {
                case "summarize":
                    response = emailGeneratorService.summarizeEmail(emailRequest);
                    break;
                case "suggest_subject":
                    response = emailGeneratorService.suggestSubject(emailRequest);
                    break;
                case "rephrase":
                    response = emailGeneratorService.rephraseEmail(emailRequest);
                    break;
                default:
                    // Fallback for unknown actions or original functionality
                    response = handleOriginalGeneration(emailRequest);
                    break;
            }
        } else {
            // If no action is specified, use the original logic
            response = handleOriginalGeneration(emailRequest);
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Handles the original email generation logic (compose or reply).
     */
    private String handleOriginalGeneration(EmailRequest emailRequest) {
        if (emailRequest.getPrompt() != null && !emailRequest.getPrompt().isBlank()) {
            return emailGeneratorService.generateEmailFromPrompt(
                    emailRequest.getPrompt(),
                    emailRequest.getTone(),
                    emailRequest.getLanguage()
            );
        } else {
            return emailGeneratorService.generateEmailReply(emailRequest);
        }
    }
}
