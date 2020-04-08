package com.scapor.core.exceptions.services;

import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.core.exceptions.enums.LogRefServices;

public class DataNotFoundServiceException extends ServiceException {

    public DataNotFoundServiceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataNotFoundServiceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }
}
