/*
 * Copyright 2012 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-corefields.
 * 
 * prefdialog-corefields is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-corefields is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-corefields. If not, see <http://www.gnu.org/licenses/>.
 */
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
public class ButtonGroupPluginModule extends AbstractModule {

	/**
	 * The name postfix of the action button. Each button in the group will have
	 * the name {@code "name-N-button"}, with {code name} the name of the field
	 * and {code N} the index of the button.
	 */
	public static final String BUTTON_NAME = "button";

	/**
	 * The name postfix of the buttons row container. Each action button will be
	 * inside this container. The name will be {@code "name-buttonsRow"}, with
	 * {code name} the name of the field.
	 */
	public static final String BUTTONS_ROW_NAME = "buttonsRow";

	/**
	 * The name postfix of the buttons row alignment container. The container
	 * will align the group left, right or middle. The name will be
	 * {@code "name-rowPanel"}, with {code name} the name of the field.
	 */
	public static final String ROW_PANEL_NAME = "rowPanel";

	@Override
	protected void configure() {
		Multibinder<FieldPlugin> binder;
		binder = newSetBinder(binder(), FieldPlugin.class);
		binder.addBinding().to(ButtonGroupFieldPlugin.class);
	}

}
