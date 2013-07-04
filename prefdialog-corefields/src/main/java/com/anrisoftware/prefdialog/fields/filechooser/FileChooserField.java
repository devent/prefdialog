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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.File;
import java.lang.annotation.Annotation;

import javax.inject.Inject;
import javax.swing.Action;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.filetextfield.FileTextField;
import com.anrisoftware.prefdialog.miscswing.validatingfields.ValidatingTextFieldUi;
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

	private final ValidatingTextFieldUi validating;

	private final OpenDialogAction openDialogAction;

	private final VetoableChangeListener fileListener;

	private final UiPanel panel;

	private final ActionListener fileAction;

	private transient AnnotationClassFactory annotationClassFactory;

	private FileChooserModel model;

	/**
	 * @see FileChooserFieldFactory#create(Object, String)
	 */
	@Inject
	FileChooserField(FileChooserFieldLogger logger, UiPanel panel,
			FileTextField fileTextField, OpenDialogAction openDialogAction,
			@Assisted Object parentObject, @Assisted String fieldName) {
		super(panel, parentObject, fieldName);
		this.panel = panel;
		this.log = logger;
		this.fileTextField = fileTextField;
		this.openDialogAction = openDialogAction;
		this.validating = ValidatingTextFieldUi.decorate(fileTextField);
		this.fileListener = new VetoableChangeListener() {

			@Override
			public void vetoableChange(PropertyChangeEvent evt)
					throws PropertyVetoException {
				setValue(evt.getNewValue());
			}
		};
		this.fileAction = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setValue(FileChooserField.this.fileTextField.getValue());
				} catch (PropertyVetoException e1) {
				}
			}
		};
		setupPanel();
	}

	private void setupPanel() {
		panel.setFileField(fileTextField);
		panel.getOpenFileChooser().addActionListener(openDialogAction);
		fileTextField.addActionListener(fileAction);
		fileTextField.addPropertyChangeListener("value",
				new PropertyChangeListener() {

					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						try {
							setValue(evt.getNewValue());
						} catch (PropertyVetoException e) {
						}
					}
				});
	}

	@Inject
	void setupFileChooserField(AnnotationClassFactory annotationClassFactory) {
		this.annotationClassFactory = annotationClassFactory;
		setupModel();
	}

	private void setupModel() {
		FileChooserModel model = (FileChooserModel) annotationClassFactory
				.create(getParentObject(), ANNOTATION_CLASS,
						getAccessibleObject()).forAttribute(MODEL_ELEMENT)
				.build();
		setModel(model);
	}

	@Override
	public void setValue(Object value) throws PropertyVetoException {
		if (model == null) {
			super.setValue(value);
			fileTextField.setValue(value);
			return;
		}
		File oldFile = model.getFile();
		try {
			model.setFile((File) value);
		} catch (PropertyVetoException e) {
			validating.setValid(false);
			throw e;
		}
		try {
			super.setValue(value);
			fileTextField.setValue(value);
		} catch (PropertyVetoException e) {
			model.setFile(oldFile);
			validating.setValid(false);
			throw e;
		}
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
		removeOldModel();
		this.model = model;
		try {
			model.setFile((File) getValue());
		} catch (PropertyVetoException e) {
		}
		model.addVetoableChangeListener(fileListener);
		openDialogAction.setFileChooserModel(model);
		log.modelSet(this, model);
	}

	private void removeOldModel() {
		if (model != null) {
			model.removeVetoableChangeListener(fileListener);
		}
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
