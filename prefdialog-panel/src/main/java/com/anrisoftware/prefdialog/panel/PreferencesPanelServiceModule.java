package com.anrisoftware.prefdialog.panel;

import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.google.inject.AbstractModule;

/**
 * Installs preferences panel service.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 3.6
 */
class PreferencesPanelServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(FieldFactory.class).to(PreferencesPanelFieldFactory.class);
    }

}
