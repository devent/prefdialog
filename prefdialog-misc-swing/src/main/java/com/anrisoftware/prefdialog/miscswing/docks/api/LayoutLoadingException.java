package com.anrisoftware.prefdialog.miscswing.docks.api;

/**
 * Thrown if there was an error loading the layout.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@SuppressWarnings("serial")
public class LayoutLoadingException extends LayoutException {

    public LayoutLoadingException(String name, Throwable cause) {
        super("Layout loading error", cause);
        add("name", name);
    }

}
