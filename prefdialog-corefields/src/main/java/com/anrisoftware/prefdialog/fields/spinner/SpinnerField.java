/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
 *
 * This file is part of prefdialog-corefields.
 *
 * prefdialog-corefields is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
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

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.beans.PropertyVetoException;
import java.lang.annotation.Annotation;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClass;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.globalpom.reflection.beans.BeanAccessFactory;
import com.anrisoftware.prefdialog.annotations.Spinner;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.fields.spacer.Spacer;
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

	private final SpinnerFieldLogger log;

	private final ChangeListener changeListener;

	private transient AnnotationClassFactory annotationClassFactory;

	private transient AnnotationAccess annotationAccess;

	private transient BeanAccessFactory beanAccessFactory;

	private boolean customModelSet;

	/**
	 * @see SpinnerFieldFactory#create(Object, String)
	 */
	@Inject
	SpinnerField(SpinnerFieldLogger logger, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(new JSpinner(), parentObject, fieldName);
		this.log = logger;
		this.customModelSet = false;
		this.changeListener = new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					setValue(getComponent().getValue());
				} catch (PropertyVetoException e1) {
				}
			}
		};

	}

	@Inject
	void setupSpinnerField(AnnotationClassFactory annotationClassFactory,
			AnnotationAccessFactory annotationAccessFactory,
			BeanAccessFactory beanAccessFactory) {
		this.annotationClassFactory = annotationClassFactory;
		this.annotationAccess = annotationAccessFactory.create(
				ANNOTATION_CLASS, getAccessibleObject());
		this.beanAccessFactory = beanAccessFactory;
		SpinnerModel defaultModel = createDefaultModel();
		setupModel(defaultModel);
		getComponent().setValue(getValue());
	}

	private void setupModel(SpinnerModel defaultModel) {
		@SuppressWarnings("unchecked")
		AnnotationClass<SpinnerModel> annotationClass = (AnnotationClass<SpinnerModel>) annotationClassFactory
				.create(getParentObject(), ANNOTATION_CLASS,
						getAccessibleObject());
		SpinnerModel model = annotationClass.withDefault(defaultModel)
				.forAttribute(MODEL_ELEMENT).build();
		setModel(model);
	}

	private SpinnerModel createDefaultModel() {
		SpinnerModel model = createNumberModel();
		model = model == null ? createDateModel() : model;
		model = model == null ? createListModel() : model;
		model = model == null ? new SpinnerNumberModel() : model;
		return model;
	}

	private SpinnerModel createListModel() {
		String field = annotationAccess.getValue("elements");
		if (isBlank(field)) {
			return null;
		}
		return new SpinnerListModel(beanAccessFactory.create(field,
				getParentObject()).<List<?>> getValue());
	}

	private SpinnerModel createDateModel() {
		Object value = getValue();
		if (!(value instanceof Date)) {
			return null;
		}
		long[] start = annotationAccess.getValue(START_ATTRIBUTE);
		long[] end = annotationAccess.getValue(END_ATTRIBUTE);
		int field = annotationAccess.getValue(CALENDAR_FIELD_ATTRIBUTE);
		Comparable<?> cstart = start.length == 0 ? null : new Date(start[0]);
		Comparable<?> cend = end.length == 0 ? null : new Date(end[0]);
		return new SpinnerDateModel((Date) value, cstart, cend, field);
	}

	private SpinnerModel createNumberModel() {
		Object value = getValue();
		if (!(value instanceof Number)) {
			return null;
		}
		double[] max = annotationAccess.getValue(MAXIMUM_ATTRIBUTE);
		double[] min = annotationAccess.getValue(MINIMUM_ATTRIBUTE);
		double size = annotationAccess.getValue(STEP_SIZE_ATTRIBUTE);
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
	public void setValue(Object value) throws PropertyVetoException {
		super.setValue(value);
		if (customModelSet) {
			getComponent().setValue(value);
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
		removeOldModel();
		getComponent().setModel(model);
		model.addChangeListener(changeListener);
		customModelSet = true;
		log.modelSet(this, model);
	}

	private void removeOldModel() {
		getComponent().getModel().removeChangeListener(changeListener);
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
