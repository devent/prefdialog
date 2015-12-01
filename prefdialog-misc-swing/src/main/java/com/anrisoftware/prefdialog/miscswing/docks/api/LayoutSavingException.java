package com.anrisoftware.prefdialog.miscswing.docks.api;

/**
 * Thrown if there was an error saving the layout.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@SuppressWarnings("serial")
public class LayoutSavingException extends LayoutException {

    public LayoutSavingException(String name, Throwable cause) {
        super("Layout saving error", cause);
        add("name", name);
    }

}
