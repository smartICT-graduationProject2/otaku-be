package com.otaku.otakube.common.exception.custom.user;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import lombok.Getter;

@Getter
public class UserException extends CustomException {

    public UserException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
