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
package com.globalscalingsoftware.prefdialog.internal.inputfield.child;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.fest.reflect.exception.ReflectionError;

import com.globalscalingsoftware.prefdialog.FieldHandler;
import com.globalscalingsoftware.prefdialog.internal.inputfield.FieldsFactory;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;

class AddAllInputFields {

	private final ReflectionToolbox reflectionToolbox;
	private final FieldsFactory fieldsFactory;

	public AddAllInputFields(ReflectionToolbox reflectionToolbox,
			FieldsFactory fieldsFactory) {
		this.reflectionToolbox = reflectionToolbox;
		this.fieldsFactory = fieldsFactory;
	}

	public List<FieldHandler<?>> addAllInto(List<FieldHandler<?>> inputFields,
			Object parentObject) {
		addAllInputFields(inputFields, parentObject);
		return inputFields;
	}

	private void addAllInputFields(List<FieldHandler<?>> inputFields,
			Object parentObject) {
		List<Field> fields = getPreferenceFields(parentObject);
		for (Field field : fields) {
			FieldHandler<?> inputField = createInputField(parentObject,
					reflectionToolbox, field);
			addInputField(inputFields, field, inputField);
		}
	}

	private List<Field> getPreferenceFields(Object parentObject) {
		Field[] declaredFields = parentObject.getClass().getDeclaredFields();
		List<Field> fields = new ArrayList<Field>(declaredFields.length);
		addPreferenceFields(fields, declaredFields);
		return fields;
	}

	private void addPreferenceFields(List<Field> fields, Field[] declaredFields) {
		for (Field field : declaredFields) {
			if (fieldIsStatic(field)) {
				continue;
			}
			if (fieldIsTransient(field)) {
				continue;
			}
			fields.add(field);
		}
	}

	private boolean fieldIsTransient(Field field) {
		return Modifier.isTransient(field.getModifiers());
	}

	private boolean fieldIsStatic(Field field) {
		return Modifier.isStatic(field.getModifiers());
	}

	private FieldHandler<?> createInputField(Object parentObject,
			ReflectionToolbox reflectionToolbox, Field field) {
		try {
			Object value = getValueFromField(parentObject, reflectionToolbox,
					field);
			return createInputField(parentObject, field, value);
		} catch (ReflectionError e) {
			return null;
		}
	}

	private Object getValueFromField(Object parentObject,
			ReflectionToolbox reflectionToolbox, Field field) {
		return reflectionToolbox.getValueFrom(field, parentObject);
	}

	private FieldHandler<?> createInputField(Object parentObject, Field field,
			Object value) {
		return fieldsFactory.createField(parentObject, field, value);
	}

	private void addInputField(List<FieldHandler<?>> inputFields, Field field,
			FieldHandler<?> inputField) {
		if (inputField == null) {
			return;
		}

		inputFields.add(inputField);
	}

}
