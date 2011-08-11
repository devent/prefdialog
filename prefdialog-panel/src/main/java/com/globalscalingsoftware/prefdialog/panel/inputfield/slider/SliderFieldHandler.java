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
package com.globalscalingsoftware.prefdialog.panel.inputfield.slider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

import com.globalscalingsoftware.prefdialog.annotations.Slider;
import com.globalscalingsoftware.prefdialog.panel.inputfield.AbstractLabelFieldHandler;
import com.globalscalingsoftware.prefdialog.panel.inputfield.slider.LoggerFactory.Logger;
import com.globalscalingsoftware.prefdialog.reflection.ReflectionToolbox;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class SliderFieldHandler extends AbstractLabelFieldHandler<SliderPanel> {

	private final Logger log;

	@Inject
	SliderFieldHandler(LoggerFactory loggerFactory,
			ReflectionToolbox reflectionToolbox,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(reflectionToolbox, parentObject, value, field, Slider.class,
				new SliderPanel());
		this.log = loggerFactory.create(SliderFieldHandler.class);
		setup();
	}

	private void setup() {
		if (isCustomModelSet()) {
			setupCustomModel();
		} else {
			setupMin();
			setupMax();
			setupExtent();
		}
		setupMajorTicks();
		setupMinorTicks();
		setupPaintLabels();
		setupPaintTicks();
		setupPaintTrack();
		setupSnapToTicks();
	}

	private boolean isCustomModelSet() {
		Class<? extends BoundedRangeModel> modelClass = getModelClass();
		return modelClass != DefaultBoundedRangeModel.class;
	}

	private void setupExtent() {
		int extent = getValueFromAnnotation("extent", Integer.class);
		log.setExtent(getField(), extent);
		getComponent().setExtent(extent);
	}

	private void setupCustomModel() {
		Class<? extends BoundedRangeModel> modelClass = getModelClass();
		BoundedRangeModel model = createNewModel(modelClass);
		log.setModel(getField(), model);
		getComponent().setModel(model);
	}

	@SuppressWarnings("unchecked")
	private Class<? extends BoundedRangeModel> getModelClass() {
		return getValueFromAnnotation("model", Class.class);
	}

	private BoundedRangeModel createNewModel(
			Class<? extends BoundedRangeModel> modelClass) {
		try {
			return modelClass.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private void setupMin() {
		int min = getValueFromAnnotation("min", Integer.class);
		log.setMin(getField(), min);
		getComponent().setMin(min);
	}

	private void setupMax() {
		int max = getValueFromAnnotation("max", Integer.class);
		log.setMax(getField(), max);
		getComponent().setMax(max);
	}

	private void setupMajorTicks() {
		int value = getValueFromAnnotation("majorTicks", Integer.class);
		log.setMajorTicks(getField(), value);
		getComponent().setMajorTicks(value);
	}

	private void setupMinorTicks() {
		int value = getValueFromAnnotation("minorTicks", Integer.class);
		log.setMinorTicks(getField(), value);
		getComponent().setMinorTicks(value);
	}

	private void setupPaintTicks() {
		boolean value = getValueFromAnnotation("paintTicks", Boolean.class);
		log.setPaintTicks(getField(), value);
		getComponent().setPaintTicks(value);
	}

	private void setupPaintLabels() {
		boolean value = getValueFromAnnotation("paintLabels", Boolean.class);
		log.setPaintLabels(getField(), value);
		getComponent().setPaintLabels(value);
	}

	private void setupPaintTrack() {
		boolean value = getValueFromAnnotation("paintTrack", Boolean.class);
		log.setPaintTrack(getField(), value);
		getComponent().setPaintTrack(value);
	}

	private void setupSnapToTicks() {
		boolean value = getValueFromAnnotation("snapToTicks", Boolean.class);
		log.setSnapToTicks(getField(), value);
		getComponent().setSnapToTicks(value);
	}

	private <T> T getValueFromAnnotation(String name, Class<T> classType) {
		Annotation a = getField().getAnnotation(Slider.class);
		return reflectionToolbox.invokeMethodWithReturnType(name, classType, a);
	}

}
