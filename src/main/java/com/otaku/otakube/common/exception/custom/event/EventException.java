package com.otaku.otakube.common.exception.custom.event;

import com.otaku.otakube.common.exception.constants.ErrorDetails;
import com.otaku.otakube.common.exception.custom.CustomException;
import lombok.Getter;

@Getter
public class EventException extends CustomException {

    public EventException(ErrorDetails errorDetails) {
        super(errorDetails);
    }
}
