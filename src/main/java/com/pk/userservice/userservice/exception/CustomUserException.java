package com.pk.userservice.userservice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Data
public class CustomUserException extends RuntimeException{
    private String message;
    private String errorCode;

    public CustomUserException(String message,String errorCode){
        super(message);
        this.errorCode=errorCode;
    }
}
