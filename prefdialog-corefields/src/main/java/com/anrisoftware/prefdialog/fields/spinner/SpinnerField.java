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
package com.anrisoftware.prefdialog.fields.spinner;

import static com.anrisoftware.prefdialog.miscswing.components.validating.AbstractValidatingComponent.VALUE_PROPERTY;
import static org.apache.commons.lang3.StringUtils.isBlank;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.annotations.Spinner;
import com.anrisoftware.prefdialog.classtask.ClassTask;
import com.anrisoftware.prefdialog.classtask.ClassTaskFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.fields.spacer.Spacer;
import com.anrisoftware.prefdialog.miscswing.components.validating.ValidatingSpinner;
import com.google.inject.assistedinject.Assisted;

/**
 * Sets the properties of the spinner field.
 * 
 * @see Spacer
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class SpinnerField extends AbstractTitleField<JSpinner> {

	private static final String STEP_SIZE_ATTRIBUTE = "stepSize";

	private static final String MINIMUM_ATTRIBUTE = "minimum";

	private static final String MAXIMUM_ATTRIBUTE = "maximum";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = Spinner.class;

	private static final String MODEL_ELEMENT = "model";

	private static final String START_ATTRIBUTE = "start";

	private static final String END_ATTRIBUTE = "end";

	private static final String CALENDAR_FIELD_ATTRIBUTE = "calendarField";

	private final ValidatingSpinner<JSpinner> validating;

	private final VetoableChangeListener valueVetoListener;

	private final SpinnerFieldLogger log;

	private boolean modelSet;

	/**
	 * @see SpinnerFieldFactory#create(Object, String)
	 */
	@Inject
	SpinnerField(SpinnerFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(new JSpinner(), parentObject, fieldName);
		this.log = logger;
		this.validating = new ValidatingSpinner<JSpinner>(getComponent());
		this.modelSet = false;
		this.valueVetoListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				SpinnerField.super.trySetValue(evt.getNewValue());
				changeValue(evt.getNewValue());
			}
		};
		setupValidating();
	}

	private void setupValidating() {
		validating.addVetoableChangeListener(VALUE_PROPERTY, valueVetoListener);
	}

	@Inject
	void setupAnnotationElements(ClassTaskFactory classTaskFactory,
			AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory) {
		AnnotationAccess annotationAccess = annotationAccessFactory.create(
				ANNOTATION_CLASS, getAccessibleObject());
		SpinnerModel defaultModel = createDefaultModel(annotationAccess,
				beanAccessFactory);
		setupModel(defaultModel, classTaskFactory, beanAccessFactory);
		getComponent().setValue(getValue());
	}

	private void setupModel(SpinnerModel defaultModel,
			ClassTaskFactory classTaskFactory,
			BeanAccessFactory beanAccessFactory) {
		@SuppressWarnings("unchecked")
		ClassTask<SpinnerModel> task = (ClassTask<SpinnerModel>) classTaskFactory
				.create(MODEL_ELEMENT, ANNOTATION_CLASS, getAccessibleObject());
		setModel(task.withParent(getParentObject()).withDefault(defaultModel)
				.build());
	}

	private SpinnerModel createDefaultModel(AnnotationAccess a,
			BeanAccessFactory accessFactory) {
		SpinnerModel model = createNumberModel(a);
		model = model == null ? createDateModel(a) : model;
		model = model == null ? createListModel(a, accessFactory) : model;
		model = model == null ? new SpinnerNumberModel() : model;
		return model;
	}

	private SpinnerModel createListModel(AnnotationAccess a,
			BeanAccessFactory accessFactory) {
		String field = a.getValue("elements");
		if (isBlank(field)) {
			return null;
		}
		return new SpinnerListModel(accessFactory.create(field,
				getParentObject()).<List<?>> getValue());
	}

	private SpinnerModel createDateModel(AnnotationAccess a) {
		Object value = getValue();
		if (!(value instanceof Date)) {
			return null;
		}
		long[] start = a.getValue(START_ATTRIBUTE);
		long[] end = a.getValue(END_ATTRIBUTE);
		int field = a.getValue(CALENDAR_FIELD_ATTRIBUTE);
		Comparable<?> cstart = start.length == 0 ? null : new Date(start[0]);
		Comparable<?> cend = end.length == 0 ? null : new Date(end[0]);
		return new SpinnerDateModel((Date) value, cstart, cend, field);
	}

	private SpinnerModel createNumberModel(AnnotationAccess a) {
		Object value = getValue();
		if (!(value instanceof Number)) {
			return null;
		}
		double[] max = a.getValue(MAXIMUM_ATTRIBUTE);
		double[] min = a.getValue(MINIMUM_ATTRIBUTE);
		double size = a.getValue(STEP_SIZE_ATTRIBUTE);
		if (value instanceof Integer) {
			Comparable<?> maximum = max.length == 0 ? null : (int) max[0];
			Comparable<?> minimum = min.length == 0 ? null : (int) min[0];
			return new SpinnerNumberModel((Number) getValue(), minimum,
					maximum, (int) size);
		}
		if (value instanceof Long) {
			Comparable<?> maximum = max.length == 0 ? null : (long) max[0];
			Comparable<?> minimum = min.length == 0 ? null : (long) min[0];
			return new SpinnerNumberModel((Number) getValue(), minimum,
					maximum, (long) size);
		}
		if (value instanceof Double) {
			Comparable<?> maximum = max.length == 0 ? null : max[0];
			Comparable<?> minimum = min.length == 0 ? null : min[0];
			return new SpinnerNumberModel((Number) getValue(), minimum,
					maximum, size);
		}
		return null;
	}

	@Override
	protected void trySetValue(Object value) throws PropertyVetoException {
		if (modelSet) {
			validating.setValue(value);
		}
	}

	/**
	 * Sets the spinner model for the field.
	 * 
	 * @param model
	 *            the {@link SpinnerModel}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	public void setModel(SpinnerModel model) {
		log.checkModel(this, model);
		getComponent().setModel(model);
		modelSet = true;
		log.modelSet(this, model);
	}

	/**
	 * Sets the spinner model for the field.
	 * 
	 * @return the {@link SpinnerModel}.
	 */
	public SpinnerModel getModel() {
		return getComponent().getModel();
	}
}
