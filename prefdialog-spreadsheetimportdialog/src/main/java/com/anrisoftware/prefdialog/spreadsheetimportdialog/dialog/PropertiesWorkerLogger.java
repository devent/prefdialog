package com.anrisoftware.prefdialog.spreadsheetimportdialog.dialog;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging for {@link PropertiesWorker}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@Singleton
final class PropertiesWorkerLogger extends AbstractLogger {

    /**
     * Sets the context of the logger to {@link PropertiesWorker}.
     */
    public PropertiesWorkerLogger() {
        super(PropertiesWorker.class);
    }
}
