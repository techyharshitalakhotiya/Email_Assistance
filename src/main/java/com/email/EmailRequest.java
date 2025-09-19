package com.email;

import lombok.Data;

@Data
public class EmailRequest {
    private String emailContent;
    private String tone;
    private String language;
    private String prompt;
    private String action; // New field to specify the action
}
