package com.anrisoftware.prefdialog.miscswing.dialogsworker;

import javax.inject.Singleton;

import com.anrisoftware.globalpom.log.AbstractLogger;

/**
 * Logging for {@link OpenDialogAction}.
 *
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.5
 */
@Singleton
final class OpenDialogActionLogger extends AbstractLogger {

    /**
     * Sets the context of the logger to {@link OpenDialogAction}.
     */
    public OpenDialogActionLogger() {
        super(OpenDialogAction.class);
    }

    void errorOpenDialog(Throwable e) {
        log.error(null, e);
    }
}
