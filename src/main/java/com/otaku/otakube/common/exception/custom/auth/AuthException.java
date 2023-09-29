package com.otaku.otakube.common.exception.custom.auth;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;

public class AuthException extends CustomException {
    public AuthException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
