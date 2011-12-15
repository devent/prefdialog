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
package com.anrisoftware.prefdialog.panel.inputfields.slider;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.swing.BoundedRangeModel;
import javax.swing.DefaultBoundedRangeModel;

import com.anrisoftware.prefdialog.FieldHandler;
import com.anrisoftware.prefdialog.annotations.Slider;
import com.anrisoftware.prefdialog.swingutils.AbstractLabelFieldHandler;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the {@link SliderPanel} as the managed component.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.1
 */
class SliderFieldHandler extends AbstractLabelFieldHandler<SliderPanel> {

	private LoggerFactory.Logger log;

	/**
	 * Sets the parameter of the {@link SliderPanel}.
	 * 
	 * @param panel
	 *            the {@link SliderPanel}.
	 * 
	 * @param parentObject
	 *            the {@link Object} where the field is defined.
	 * 
	 * @param value
	 *            the value of the field.
	 * 
	 * @param field
	 *            the {@link Field}.
	 */
	@Inject
	SliderFieldHandler(SliderPanel panel,
			@Assisted("parentObject") Object parentObject,
			@Assisted("value") Object value, @Assisted Field field) {
		super(parentObject, value, field, Slider.class, panel);
	}

	/**
	 * Set the attributes min, max, majorTicks, minorTicks, paintTicks,
	 * paintLabels, snapToTicks, extent, model.
	 */
	@Override
	public FieldHandler<SliderPanel> setup() {
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
		return super.setup();
	}

	private boolean isCustomModelSet() {
		Class<? extends BoundedRangeModel> modelClass = getModelClass();
		return modelClass != DefaultBoundedRangeModel.class;
	}

	private void setupExtent() {
		int extent = getValueFromAnnotation("extent", Integer.class);
		log.setExtent(extent, this);
		getComponent().setExtent(extent);
	}

	private void setupCustomModel() {
		Class<? extends BoundedRangeModel> modelClass = getModelClass();
		BoundedRangeModel model = createNewModel(modelClass);
		log.setModel(model, this);
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
		log.setMin(min, this);
		getComponent().setMin(min);
	}

	private void setupMax() {
		int max = getValueFromAnnotation("max", Integer.class);
		log.setMax(max, this);
		getComponent().setMax(max);
	}

	private void setupMajorTicks() {
		int value = getValueFromAnnotation("majorTicks", Integer.class);
		log.setMajorTicks(value, this);
		getComponent().setMajorTicks(value);
	}

	private void setupMinorTicks() {
		int value = getValueFromAnnotation("minorTicks", Integer.class);
		log.setMinorTicks(value, this);
		getComponent().setMinorTicks(value);
	}

	private void setupPaintTicks() {
		boolean value = getValueFromAnnotation("paintTicks", Boolean.class);
		log.setPaintTicks(value, this);
		getComponent().setPaintTicks(value);
	}

	private void setupPaintLabels() {
		boolean value = getValueFromAnnotation("paintLabels", Boolean.class);
		log.setPaintLabels(value, this);
		getComponent().setPaintLabels(value);
	}

	private void setupPaintTrack() {
		boolean value = getValueFromAnnotation("paintTrack", Boolean.class);
		log.setPaintTrack(value, this);
		getComponent().setPaintTrack(value);
	}

	private void setupSnapToTicks() {
		boolean value = getValueFromAnnotation("snapToTicks", Boolean.class);
		log.setSnapToTicks(value, this);
		getComponent().setSnapToTicks(value);
	}

	private <T> T getValueFromAnnotation(String name, Class<T> classType) {
		Annotation a = getField().getAnnotation(Slider.class);
		return getReflectionToolbox().invokeMethodWithReturnType(name,
				classType, a);
	}

	/**
	 * Injects the slider field {@link LoggerFactory}.
	 */
	@Inject
	public void setSliderFieldLoggerFactory(LoggerFactory loggerFactory) {
		log = loggerFactory.create(SliderFieldHandler.class);
	}
}
