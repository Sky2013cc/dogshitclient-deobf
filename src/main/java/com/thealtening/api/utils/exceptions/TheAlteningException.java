package com.thealtening.api.utils.exceptions;

/* loaded from: target.jar:com/thealtening/api/utils/exceptions/TheAlteningException.class */
public class TheAlteningException extends RuntimeException {
    public TheAlteningException(String error, String errorMessage) {
        super(String.format("[%s]: %s", error, errorMessage));
    }
}
