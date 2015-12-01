package com.anrisoftware.prefdialog.miscswing.docks.api;

/**
 * Thrown if the loading/saving of the layout was interrupted.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@SuppressWarnings("serial")
public class LayoutInterruptedException extends LayoutException {

    public LayoutInterruptedException(String name, InterruptedException e) {
        super("Layout interrupted", e);
        add("name", name);
    }

}
