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
package com.anrisoftware.prefdialog.fields.child;

import static com.google.inject.multibindings.Multibinder.newSetBinder;

import com.anrisoftware.prefdialog.fields.FieldPlugin;
import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

/**
 * Binds the child field field plug-in.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class ChildPluginModule extends AbstractModule {

	/**
	 * The name post-fix of the children scroll pane. The scroll pane will have
	 * the name {@code "name-childrenScroll"}, with {code name} the name of the
	 * field.
	 */
	public static final String CHILDREN_SCROLL_NAME = "childrenScroll";

	/**
	 * The name post-fix of the title separator. The separator will have the
	 * name {@code "name-separator"}, with {code name} the name of the field.
	 */
	public static final String TITLE_SEPARATOR_NAME = "separator";

	@Override
	protected void configure() {
		Multibinder<FieldPlugin> binder;
		binder = newSetBinder(binder(), FieldPlugin.class);
		binder.addBinding().to(ChildFieldPlugin.class);
	}

}