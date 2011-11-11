package com.globalscalingsoftware.prefdialog.swingutils;

/**
 * Logger for the preferences dialog.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
public abstract class AbstractSwingLogger {

	protected transient org.slf4j.Logger log;

	/**
	 * Creates a new {@link org.slf4j.Logger} for the given {@link Class}.
	 */
	protected AbstractSwingLogger(Class<?> contextClass) {
		this.log = org.slf4j.LoggerFactory.getLogger(contextClass);
	}

}
