package com.formdev.flatlaf.util;

/* loaded from: target.jar:com/formdev/flatlaf/util/LoggingFacade.class */
public interface LoggingFacade {
    public static final LoggingFacade INSTANCE = new LoggingFacadeImpl();

    void logSevere(String str, Throwable th);

    void logConfig(String str, Throwable th);
}
