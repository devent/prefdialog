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

import java.awt.Component;
import java.awt.Container;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.anrisoftware.prefdialog.annotations.Child;
import com.anrisoftware.prefdialog.fields.FieldPlugin;
import com.google.inject.Injector;

/**
 * Returns the child panel field for the specified field of the preferences bean
 * object.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class ChildFieldPlugin implements FieldPlugin {

	private static final Class<? extends Annotation> FIELD_ANNOTATION = Child.class;

	private final LazyCreateFactory lazyCreateFactory;

	public ChildFieldPlugin() {
		this.lazyCreateFactory = new LazyCreateFactory();
	}

	@Override
	public Class<? extends Annotation> getFieldAnnotation() {
		return FIELD_ANNOTATION;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ChildField getField(Object injector, Component component,
			Object bean, Field field) {
		lazyCreateFactory.setParentInjector((Injector) injector);
		Container container = (Container) component;
		return lazyCreateFactory.lazyCreateField(bean, field, container);
	}

}
