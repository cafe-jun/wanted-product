package com.example.cafejun.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
public class Message {
    private String message;
    public Message(){}
    @Builder
    public Message(String message) {
        this.message = message;
    }
}
