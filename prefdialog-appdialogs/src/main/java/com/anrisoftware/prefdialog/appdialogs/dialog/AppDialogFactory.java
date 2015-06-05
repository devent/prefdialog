package com.anrisoftware.prefdialog.appdialogs.dialog;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Factory to create the application dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public interface AppDialogFactory {

    /**
     * Creates the application dialog.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @return the {@link AppDialog}.
     */
    @OnAwt
    AppDialog create();
}
