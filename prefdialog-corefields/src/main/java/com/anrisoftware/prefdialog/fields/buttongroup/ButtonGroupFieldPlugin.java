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

import java.awt.Component;
import java.awt.Container;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.apache.commons.lang3.concurrent.ConcurrentException;
import org.apache.commons.lang3.concurrent.LazyInitializer;
import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.anrisoftware.prefdialog.annotations.ButtonGroup;
import com.anrisoftware.prefdialog.fields.FieldPlugin;
import com.anrisoftware.prefdialog.fields.FieldPluginError;
import com.google.inject.Injector;

/**
 * Returns the button group field for the specified field of the preferences
 * bean object.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.2
 */
public class ButtonGroupFieldPlugin implements FieldPlugin {

	private Injector parentInjector;

	private LazyInitializer<ButtonGroupFieldFactory> lazyCreateFactory;

	public ButtonGroupFieldPlugin() {
		this.lazyCreateFactory = new LazyInitializer<ButtonGroupFieldFactory>() {

			@Override
			protected ButtonGroupFieldFactory initialize()
					throws ConcurrentException {
				Injector injector = parentInjector
						.createChildInjector(new ButtonGroupModule());
				return injector.getInstance(ButtonGroupFieldFactory.class);
			}
		};
	}

	@Override
	public Class<? extends Annotation> getFieldAnnotation() {
		return ButtonGroup.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ButtonGroupField getField(Object injector, Component component,
			Object bean, Field field) {
		parentInjector = (Injector) injector;
		Container container = (Container) component;
		return lazyCreateField(bean, field, container);
	}

	private ButtonGroupField lazyCreateField(Object bean, Field field,
			Container container) {
		try {
			return lazyCreateFactory.get().create(container, bean, field);
		} catch (ConcurrentException e) {
			throw createError(e);
		}
	}

	private ContextedRuntimeException createError(ConcurrentException e) {
		return new FieldPluginError("Error create buttons group field.",
				e.getCause()).addContextValue("annotation",
				getFieldAnnotation());
	}

}
