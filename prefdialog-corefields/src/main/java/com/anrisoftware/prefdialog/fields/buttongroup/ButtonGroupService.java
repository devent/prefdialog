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

import static java.util.Arrays.asList;

import java.awt.Component;

import org.mangosdk.spi.ProviderFor;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationsModule;
import com.anrisoftware.globalpom.reflection.beans.BeansModule;
import com.anrisoftware.prefdialog.annotations.ButtonGroup;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldInfo;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Binds the button group field plugin.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@ProviderFor(FieldService.class)
public class ButtonGroupService implements FieldService {

	/**
	 * The field information.
	 */
	public static final FieldInfo INFO = new FieldInfo(ButtonGroup.class);

	/**
	 * The name post-fix of the action button. Each button in the group will
	 * have the name <code>&lt;name&gt;-N-button</code>, with
	 * <code>&lt;name&gt;</code> the name of the field and {@code N} the index
	 * of the button.
	 */
	public static final String BUTTON_NAME = "button";

	/**
	 * The name post-fix of the buttons row container. Each action button will
	 * be inside this container. The name will be
	 * <code>&lt;name&gt;-buttonsRow</code>, with <code>&lt;name&gt;</code> the
	 * name of the field.
	 */
	public static final String BUTTONS_ROW_NAME = "buttonsRow";

	/**
	 * The name post-fix of the buttons row alignment container. The container
	 * will align the group left, right or middle. The name will be
	 * <code>&lt;name&gt;-buttonsGroupPanel</code>, with
	 * <code>&lt;name&gt;</code> the name of the field.
	 */
	public static final String BUTTONS_GROUP_PANEL_NAME = "buttonsGroupPanel";

	private final Iterable<? extends Module> modules;

	private final Iterable<? extends Module> dependencies;

	public ButtonGroupService() {
		this.modules = asList(new Module[] { new ButtonGroupModule() });
		this.dependencies = asList(new Module[] { new AnnotationsModule(),
				new BeansModule() });
	}

	@Override
	public FieldInfo getInfo() {
		return INFO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public FieldFactory<? extends Component> getFactory(Object... parent) {
		return createInjector(parent.length > 0 ? (Injector) parent[0] : null)
				.getInstance(FieldFactory.class);
	}

	private Injector createInjector(Injector parent) {
		return parent != null ? parent.createChildInjector(modules) : Guice
				.createInjector(dependencies).createChildInjector(modules);
	}
}
