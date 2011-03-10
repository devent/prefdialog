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
package com.globalscalingsoftware.prefdialog.panel.internal.inputfield.slider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

import com.globalscalingsoftware.prefdialog.annotations.Slider;
import com.globalscalingsoftware.prefdialog.internal.reflection.ReflectionToolbox;
import com.globalscalingsoftware.prefdialog.panel.internal.inputfield.AbstractDefaultFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class SliderFieldHandler extends
		AbstractDefaultFieldHandler<SliderPanel> {

	@Inject
	SliderFieldHandler(ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, Slider.class,
				new SliderPanel());
	}

	@Override
	public void setup() {
		super.setup();
		setupCustomModel();
		setupMin();
		setupMax();
		setupExtent();
		setupMajorTicks();
		setupMinorTicks();
		setupPaintLabels();
		setupPaintTicks();
		setupPaintTrack();
		setupSnapToTicks();
	}

	private void setupExtent() {
		int extent = getValueFromAnnotation("extent", Integer.class);
		getComponent().setExtent(extent);
	}

	private void setupCustomModel() {
		@SuppressWarnings("unchecked")
		Class<? extends BoundedRangeModel> modelClass = getValueFromAnnotation(
				"model", Class.class);
		BoundedRangeModel model = createNewInstance(modelClass);
		getComponent().setModel(model);
	}

	private BoundedRangeModel createNewInstance(
			Class<? extends BoundedRangeModel> modelClass) {
		try {
			return modelClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new DefaultBoundedRangeModel();
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
