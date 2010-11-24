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
package com.globalscalingsoftware.prefdialog.internal.inputfield.slider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.globalscalingsoftware.prefdialog.annotations.fields.Slider;
import com.globalscalingsoftware.prefdialog.internal.inputfield.AbstractDefaultFieldHandler;

public class SliderFieldHandler extends
		AbstractDefaultFieldHandler<SliderPanel> {

	public SliderFieldHandler(Object parentObject, Object value, Field field) {
		super(parentObject, value, field, Slider.class, new SliderPanel());
	}

	@Override
	public void setup() {
		super.setup();
		setupMin();
		setupMax();
	}

	private void setupMin() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		int min = getValueFromAnnotationIn("min", Integer.class, field,
				annotationClass);
		getComponent().setMin(min);
	}

	private void setupMax() {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		int max = getValueFromAnnotationIn("max", Integer.class, field,
				annotationClass);
		getComponent().setMax(max);
	}
}
