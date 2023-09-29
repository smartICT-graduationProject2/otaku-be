package com.otaku.otakube.common.exception.custom.jwt;


import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import lombok.Getter;

@Getter
public class JwtCustomException extends CustomException {

    public JwtCustomException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
