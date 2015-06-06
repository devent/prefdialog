package com.anrisoftware.prefdialog.appdialogs.registerdialog;

import com.anrisoftware.prefdialog.miscswing.awtcheck.OnAwt;

/**
 * Factory to create the registration dialog.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public interface RegisterDialogFactory {

    /**
     * Creates the registration dialog.
     *
     * <h2>AWT Thread</h2>
     * <p>
     * Should be called in the AWT thread.
     * </p>
     *
     * @return the {@link RegisterDialog}.
     */
    @OnAwt
    RegisterDialog create();
}
