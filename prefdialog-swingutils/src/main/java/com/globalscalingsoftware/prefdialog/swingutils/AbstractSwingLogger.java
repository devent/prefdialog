package com.globalscalingsoftware.prefdialog.swingutils;

public abstract class AbstractSwingLogger {

	protected transient org.slf4j.Logger log;

	protected AbstractSwingLogger(Class<?> contextClass) {
		this.log = org.slf4j.LoggerFactory.getLogger(contextClass);
	}

}
