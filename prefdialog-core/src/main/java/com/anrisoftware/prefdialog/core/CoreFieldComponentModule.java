package com.anrisoftware.prefdialog.core;

import com.anrisoftware.globalpom.mnemonic.MnemonicModule;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassModule;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.google.inject.AbstractModule;

public class CoreFieldComponentModule extends AbstractModule {

	@Override
	protected void configure() {
		install(new AnnotationsModule());
		install(new BeansModule());
		install(new AnnotationClassModule());
		install(new MnemonicModule());
	}

}
