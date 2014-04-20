/*
 * Copyright 2012-2014 Erwin Müller <erwin.mueller@deventm.org>
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

import java.awt.Component;

import org.mangosdk.spi.ProviderFor;

import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldInfo;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Injector;

/**
 * Makes the check box field available.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@ProviderFor(FieldService.class)
public class PreferencesPanelService implements FieldService {

	/**
	 * The field information.
	 */
	public static final FieldInfo INFO = new FieldInfo(PreferencesPanel.class);

	@Override
	public FieldInfo getInfo() {
		return INFO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FieldFactory<? extends Component> getFactory(Object... parent) {
		return createInjector((Injector) parent[0]).getInstance(
				FieldFactory.class);
	}

	private Injector createInjector(Injector parent) {
		return parent.createChildInjector(new PreferencesPanelModule());
	}
}
