package com.anrisoftware.prefdialog.reflection.annotations;

import com.google.inject.AbstractModule;

/**
 * Binds the annotation access.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class AnnotationsModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(AnnotationAccess.class).to(AnnotationAccessImpl.class);
	}

}
