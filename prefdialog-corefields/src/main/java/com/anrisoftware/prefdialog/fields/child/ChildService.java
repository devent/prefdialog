/*
 * Copyright 2013-2015 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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

import java.awt.Component;

import org.mangosdk.spi.ProviderFor;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.fields.FieldFactory;
import com.anrisoftware.prefdialog.fields.FieldInfo;
import com.anrisoftware.prefdialog.fields.FieldService;
import com.google.inject.Injector;

/**
 * Makes the child field available as service.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@ProviderFor(FieldService.class)
public class ChildService implements FieldService {

	/**
	 * The name post-fix of the title separator. The separator will have the
	 * name <code>&lt;name&gt;-{@value #TITLE_SEPARATOR_NAME}</code>, with
	 * <code>&lt;name&gt;</code> the name of the field.
	 */
	public static final String TITLE_SEPARATOR_NAME = "separator";

	/**
	 * The field information.
	 */
	public static final FieldInfo INFO = new FieldInfo(Child.class);

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
		return parent.createChildInjector(new ChildModule());
	}
}
