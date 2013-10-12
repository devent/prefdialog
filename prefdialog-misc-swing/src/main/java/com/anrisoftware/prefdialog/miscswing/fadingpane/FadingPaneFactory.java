package com.anrisoftware.prefdialog.miscswing.fadingpane;

/**
 * Factory to create layer on top of a window that can be faded away.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
public interface FadingPaneFactory {

	/**
	 * Creates layer on top of a window that can be faded away.
	 * 
	 * @return the {@link FadingPane}.
	 */
	FadingPane create();
}
