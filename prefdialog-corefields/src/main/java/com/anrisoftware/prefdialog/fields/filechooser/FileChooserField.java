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
package com.anrisoftware.prefdialog.fields.filechooser;

import static com.anrisoftware.prefdialog.miscswing.components.validating.AbstractValidatingComponent.VALUE_PROPERTY;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;
import com.anrisoftware.prefdialog.classtask.ClassTask;
import com.anrisoftware.prefdialog.classtask.ClassTaskFactory;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.components.validating.ValidatingFormattedTextComponent;
import com.anrisoftware.prefdialog.miscswing.text.filetext.FileTextField;
import com.google.inject.assistedinject.Assisted;

/**
 * Field to show and select a file. Opens a dialog to select the file.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 3.0
 */
@SuppressWarnings("serial")
public class FileChooserField extends AbstractTitleField<JPanel> {

	/**
	 * Suffix to the name of the file field panel.
	 */
	public static final String FILE_FIELD_PANEL_NAME = "fileFieldPanel";

	/**
	 * Suffix to the name of the file text field.
	 */
	public static final String FILE_FIELD_NAME = "fileField";

	/**
	 * Suffix to the name of the open file chooser button.
	 */
	public static final String OPEN_FILE_CHOOSER_NAME = "openFileChooser";

	private static final Class<? extends Annotation> ANNOTATION_CLASS = FileChooser.class;

	private static final String MODEL_ELEMENT = "model";

	private final FileChooserFieldLogger log;

	private final FileTextField fileTextField;

	private final ValidatingFormattedTextComponent<FileTextField> validating;

	private final VetoableChangeListener valueVetoListener;

	private final OpenFileDialogAction openFileDialogAction;

	private final ClassTaskFactory classTaskFactory;

	private FileChooserModel model;

	private final VetoableChangeListener filePropertyListener;

	private final UiPanel panel;

	/**
	 * @see FileChooserFieldFactory#create(Object, String)
	 */
	@Inject
	FileChooserField(FileChooserFieldLogger logger, UiPanel panel,
			FileTextField fileTextField,
			OpenFileDialogAction openFileDialogAction,
			ClassTaskFactory classTaskFactory, @Assisted Object parentObject,
			@Assisted String fieldName) {
		super(panel, parentObject, fieldName);
		this.panel = panel;
		this.log = logger;
		this.fileTextField = fileTextField;
		this.classTaskFactory = classTaskFactory;
		this.openFileDialogAction = openFileDialogAction;
		this.validating = new ValidatingFormattedTextComponent<FileTextField>(
				fileTextField);
		this.valueVetoListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				FileChooserField.super.trySetValue(evt.getNewValue());
				if (model != null) {
					model.setFile((File) evt.getNewValue());
				}
				changeValue(evt.getNewValue());
			}
		};
		this.filePropertyListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				setValue(evt.getNewValue());
			}
		};
		setupValidating();
		setupPanel(panel);
	}

	private void setupValidating() {
		validating.addVetoableChangeListener(VALUE_PROPERTY, valueVetoListener);
	}

	private void setupPanel(UiPanel panel) {
		panel.setFileField(fileTextField);
		panel.getOpenFileChooser().addActionListener(openFileDialogAction);
	}

	@Inject
	void setBeanAccessFactory(AnnotationAccessFactory annotationAccessFactory) {
		setupModel();
	}

	private void setupModel() {
		@SuppressWarnings("unchecked")
		ClassTask<FileChooserModel> classTask = (ClassTask<FileChooserModel>) classTaskFactory
				.create(MODEL_ELEMENT, ANNOTATION_CLASS, getAccessibleObject());
		setModel(classTask.withParent(getParentObject()).build());
	}

	/**
	 * Sets the file chooser model.
	 * 
	 * @param model
	 *            the {@link FileChooserModel}.
	 * 
	 * @throws NullPointerException
	 *             if the specified model is {@code null}.
	 */
	public void setModel(FileChooserModel model) {
		log.checkModel(this, model);
		FileChooserModel oldModel = this.model;
		if (oldModel != null) {
			oldModel.removeVetoableChangeListener(filePropertyListener);
		}
		this.model = model;
		try {
			model.setFile((File) getValue());
		} catch (PropertyVetoException e) {
		}
		model.addVetoableChangeListener(filePropertyListener);
		openFileDialogAction.setFileChooserModel(model);
		log.modelSet(this, model);
	}

	@Override
	protected void trySetValue(Object value) throws PropertyVetoException {
		validating.setValue(value);
	}

	/**
	 * Sets the open file chooser action.
	 * 
	 * @param action
	 *            the {@link Action}.
	 */
	public void setOpenFileChooserAction(Action action) {
		panel.getOpenFileChooser().setAction(action);
	}

}
