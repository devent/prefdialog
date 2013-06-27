package com.anrisoftware.prefdialog.core;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.google.inject.AbstractModule;

/**
 * Installs the dependent modules for the core fields.
 * 
 * @see AnnotationsModule
 * @see BeansModule
 * @see AnnotationClassModule
 * @see MnemonicModule
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 1.0
 */
public class CoreFieldComponentModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new AnnotationsModule());
		install(new BeansModule());
		install(new AnnotationClassModule());
		install(new MnemonicModule());
	}

}
