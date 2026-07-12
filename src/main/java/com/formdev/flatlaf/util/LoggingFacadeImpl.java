package com.formdev.flatlaf.util;

import com.formdev.flatlaf.FlatLaf;
import java.util.logging.Level;
import java.util.logging.Logger;

/* loaded from: target.jar:com/formdev/flatlaf/util/LoggingFacadeImpl.class */
class LoggingFacadeImpl implements LoggingFacade {
    private static final Logger LOG = Logger.getLogger(FlatLaf.class.getName());

    @Override // com.formdev.flatlaf.util.LoggingFacade
    public void logSevere(String message, Throwable t) {
        LOG.log(Level.SEVERE, message, t);
    }

    @Override // com.formdev.flatlaf.util.LoggingFacade
    public void logConfig(String message, Throwable t) {
        LOG.log(Level.CONFIG, message, t);
    }
}
