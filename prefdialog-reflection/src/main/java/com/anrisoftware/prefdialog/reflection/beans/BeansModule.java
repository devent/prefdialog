package com.anrisoftware.prefdialog.reflection.beans;

import com.google.inject.AbstractModule;

/**
 * Binds the bean access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class BeansModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(BeanAccess.class).to(BeanAccessImpl.class);
	}

}
