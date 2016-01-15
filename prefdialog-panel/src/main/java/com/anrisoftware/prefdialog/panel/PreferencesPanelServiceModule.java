/*
 * Copyright 2012-2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-panel.
 *
 * prefdialog-panel is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * prefdialog-panel is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-panel. If not, see <http://www.gnu.org/licenses/>.
 */
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
