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
		setupMajorTicks();
		setupMinorTicks();
		setupPaintLabels();
		setupPaintTicks();
		setupPaintTrack();
		setupSnapToTicks();
	}

	private void setupMin() {
		int min = getValueFromAnnotation("min", Integer.class);
		getComponent().setMin(min);
	}

	private void setupMax() {
		int max = getValueFromAnnotation("max", Integer.class);
		getComponent().setMax(max);
	}

	private void setupMajorTicks() {
		int value = getValueFromAnnotation("majorTicks", Integer.class);
		getComponent().setMajorTicks(value);
	}

	private void setupMinorTicks() {
		int value = getValueFromAnnotation("minorTicks", Integer.class);
		getComponent().setMinorTicks(value);
	}

	private void setupPaintTicks() {
		boolean value = getValueFromAnnotation("paintTicks", Boolean.class);
		getComponent().setPaintTicks(value);
	}

	private void setupPaintLabels() {
		boolean value = getValueFromAnnotation("paintLabels", Boolean.class);
		getComponent().setPaintLabels(value);
	}

	private void setupPaintTrack() {
		boolean value = getValueFromAnnotation("paintTrack", Boolean.class);
		getComponent().setPaintTrack(value);
	}

	private void setupSnapToTicks() {
		boolean value = getValueFromAnnotation("snapToTicks", Boolean.class);
		getComponent().setSnapToTicks(value);
	}

	private <T> T getValueFromAnnotation(String name, Class<T> returnType) {
		Field field = getField();
		Class<? extends Annotation> annotationClass = getAnnotationClass();
		T value = getValueFromAnnotationIn(name, returnType, field,
				annotationClass);
		return value;
	}

}
