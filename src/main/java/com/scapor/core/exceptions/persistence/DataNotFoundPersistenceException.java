package com.scapor.core.exceptions.persistence;
import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.core.exceptions.enums.LogRefServices;

public class DataNotFoundPersistenceException extends ServiceException {

    public DataNotFoundPersistenceException(LogRefServices logRefServices, String message) {
        super(logRefServices, message);
    }

    public DataNotFoundPersistenceException(LogRefServices logRefServices, String message, Throwable cause) {
        super(logRefServices, message, cause);
    }

}
