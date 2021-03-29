package com.api.berlinclock.core;

import lombok.Data;

@Data
public class APIResponse {
    private boolean success = true;
    private String errorMessage;
    private String body;
}
