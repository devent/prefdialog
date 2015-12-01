package com.anrisoftware.prefdialog.miscswing.dialogsworker;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Updates the current dialog size.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
class DialogSizeListener implements ComponentListener {

    private Dimension dialogSize;

    public Dimension getDialogSize() {
        return dialogSize;
    }

    @Override
    public void componentResized(ComponentEvent e) {
        this.dialogSize = e.getComponent().getSize();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
}
