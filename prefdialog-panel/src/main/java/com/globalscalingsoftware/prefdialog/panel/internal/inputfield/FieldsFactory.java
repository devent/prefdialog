/*
 * Copyright 2010 Erwin Müller <erwin.mueller@deventm.org>
 * 
 * This file is part of prefdialog-swing.
 * 
 * prefdialog-swing is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * prefdialog-swing is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with prefdialog-swing. If not, see <http://www.gnu.org/licenses/>.
 */
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscovery;
import com.globalscalingsoftware.prefdialog.internal.reflection.AnnotationDiscoveryCallback;

public class FieldsFactory {

	private class FactoryWorker implements AnnotationDiscoveryCallback {

		private final AnnotationDiscovery annotationDiscovery;
		private final Object parentObject;
		private final FieldFactories fieldFactories;
		private final List<FieldHandler<?>> fieldHandlers;

		public FactoryWorker(AnnotationDiscovery annotationDiscovery,
				FieldFactories fieldFactories, Object parentObject) {
			this.annotationDiscovery = annotationDiscovery;
			this.fieldFactories = fieldFactories;
			this.parentObject = parentObject;
			this.fieldHandlers = new ArrayList<FieldHandler<?>>();
		}

		public List<FieldHandler<?>> createFieldsHandlers() {
			annotationDiscovery.discoverAnnotations(parentObject, this);
			return fieldHandlers;
		}

		@Override
		public void fieldAnnotationDiscovered(Field field, Object value,
				Annotation a) {
			FieldHandlerFactory factory = fieldFactories.getFactory(a);
			FieldHandler<?> handler = factory
					.create(parentObject, value, field);
			fieldHandlers.add(handler);
		}

	}

	public List<FieldHandler<?>> createFieldsHandlers(
			AnnotationDiscovery annotationDiscovery,
			FieldFactories fieldFactories, Object parentObject) {
		return new FactoryWorker(annotationDiscovery, fieldFactories,
				parentObject).createFieldsHandlers();
	}

}
