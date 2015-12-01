package com.anrisoftware.prefdialog.miscswing.docks.api;

import org.apache.commons.lang3.exception.ContextedException;

/**
 * Loading/saving layout exception.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.5
 */
@SuppressWarnings("serial")
public class LayoutException extends ContextedException {

    protected LayoutException(String message, Throwable cause) {
        super(message, cause);
    }

    protected LayoutException(String message) {
        super(message);
    }

    @Override
    public LayoutException addContextValue(String label, Object value) {
        super.addContextValue(label, value);
        return this;
    }

    public LayoutException add(String label, Object value) {
        return addContextValue(label, value);
    }

}
