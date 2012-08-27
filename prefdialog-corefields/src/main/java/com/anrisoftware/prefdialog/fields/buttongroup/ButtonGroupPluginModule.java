package com.anrisoftware.prefdialog.fields.buttongroup;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

import com.anrisoftware.prefdialog.fields.FieldPlugin;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * Binds the button group field plugin.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
class ButtonGroupPluginModule extends AbstractModule {

	@Override
	protected void configure() {
		Multibinder<FieldPlugin> binder;
		binder = newSetBinder(binder(), FieldPlugin.class);
		binder.addBinding().to(ButtonGroupFieldPlugin.class);
	}

}
