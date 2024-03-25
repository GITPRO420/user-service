package com.pk.userservice.userservice.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomTokenException extends  RuntimeException{
    private String errorCode;
    private String errorMessage;

    public CustomTokenException(String message,String errorCode){
        super(message);
        this.errorCode=errorCode;
    }

}
