package com.pk.userservice.userservice.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Builder
public class LoginException extends RuntimeException{
    private String errorMessage;
    private String errorCode;
    public LoginException(String errorMessage,String errorCode){
        super(errorMessage);
        this.errorCode=errorCode;
    }

}
