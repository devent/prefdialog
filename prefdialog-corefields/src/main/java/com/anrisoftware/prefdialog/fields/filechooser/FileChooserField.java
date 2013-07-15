/*
 * Copyright 2013-2013 Erwin Müller <erwin.mueller@deventm.org>
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
import javax.swing.JButton;
import javax.swing.JPanel;

import com.anrisoftware.globalpom.mnemonic.Mnemonic;
import com.anrisoftware.globalpom.mnemonic.MnemonicFactory;
import com.anrisoftware.globalpom.reflection.annotationclass.AnnotationClassFactory;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccess;
import com.anrisoftware.globalpom.reflection.annotations.AnnotationAccessFactory;
import com.anrisoftware.prefdialog.annotations.FileChooser;
import com.anrisoftware.prefdialog.annotations.FileChooserModel;
import com.anrisoftware.prefdialog.core.AbstractTitleField;
import com.anrisoftware.prefdialog.miscswing.filetextfield.FileTextField;
import com.anrisoftware.resources.texts.api.Texts;
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

	private static final String BUTTON_TEXT_ELEMENT = "buttonText";

	private static final String BUTTON_MNEMONIC_ELEMENT = "buttonMnemonic";

	private final FileChooserFieldLogger log;

	private final FileTextField fileTextField;

	private final OpenDialogAction openDialogAction;

	private final VetoableChangeListener fileListener;

	private final UiPanel panel;

	private final ActionListener fileAction;

	private transient AnnotationClassFactory annotationClassFactory;

	private String buttonTextResource;

	private String buttonText;

	private FileChooserModel model;

	private AnnotationAccess annotationAccess;

	private String buttonMnemonicResource;

	private Integer buttonMnemonic;

	private MnemonicFactory mnemonicFactory;

	private int buttonMnemonicIndex;

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
	void setupFileChooserField(AnnotationClassFactory annotationClassFactory,
			AnnotationAccessFactory annotationAccessFactory,
			MnemonicFactory mnemonicFactory) {
		this.annotationClassFactory = annotationClassFactory;
		this.annotationAccess = annotationAccessFactory.create(
				ANNOTATION_CLASS, getAccessibleObject());
		this.mnemonicFactory = mnemonicFactory;
		setupModel();
		setupButtonText();
		setupButtonMnemonic();
	}

	private void setupButtonText() {
		String title = annotationAccess.getValue(BUTTON_TEXT_ELEMENT);
		setButtonText(title);
	}

	private void setupButtonMnemonic() {
		String mnemonic = annotationAccess.getValue(BUTTON_MNEMONIC_ELEMENT);
		setButtonMnemonicString(mnemonic);
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
			fileTextField.setInputValid(false);
			throw e;
		}
		try {
			super.setValue(value);
			fileTextField.setValue(value);
		} catch (PropertyVetoException e) {
			model.setFile(oldFile);
			fileTextField.setInputValid(false);
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

	/**
	 * Sets the text of the button that opens the file chooser dialog. The text
	 * can also be a resource name that is queried in the supplied texts
	 * resource.
	 * 
	 * @param buttonText
	 *            the button text or the resource name.
	 */
	public void setButtonText(String buttonText) {
		this.buttonTextResource = buttonText;
		this.buttonText = buttonText;
		log.buttonTextSet(buttonText, this);
		updateButtonTextResource();
	}

	/**
	 * Returns the text of the button that opens the file chooser dialog.
	 * 
	 * @return the button text.
	 */
	public String getButtonText() {
		return buttonText;
	}

	/**
	 * Sets the mnemonic character with an optimal mnemonic index for the button
	 * that opens the file chooser dialog. The string can contain a key code
	 * name or the character. The mnemonic can also be a resource name that is
	 * queried in the supplied texts resource.
	 * 
	 * @param string
	 *            the mnemonic string or the resource name.
	 */
	public void setButtonMnemonicString(String string) {
		this.buttonMnemonicResource = string;
		Mnemonic mnemonic = mnemonicFactory.create(string);
		Integer code = mnemonic(mnemonic);
		if (code != null) {
			setButtonMnemonic(code);
			setButtonMnemonicIndex(mnemonic.getMnemonicIndex());
		} else {
			updateButtonMnemonicResource();
		}
		log.buttonMnemonicSet(this, string);
	}

	private Integer mnemonic(Mnemonic mnemonic) {
		return mnemonic.isValid() ? mnemonic.getMnemonic() : null;
	}

	/**
	 * Sets the mnemonic character code for the open the file chooser dialog
	 * button.
	 * 
	 * @param mnemonic
	 *            the mnemonic character code.
	 */
	public void setButtonMnemonic(int mnemonic) {
		this.buttonMnemonic = mnemonic;
		getOpenFileChooser().setMnemonic(mnemonic);
	}

	/**
	 * Returns the mnemonic character code for the open the file chooser dialog
	 * button.
	 * 
	 * @return the mnemonic character code or {@code null} if no mnemonic was
	 *         set.
	 */
	public Integer getButtonMnemonic() {
		return buttonMnemonic;
	}

	/**
	 * Sets the mnemonic index for the open the file chooser dialog button.
	 * 
	 * @param index
	 *            the mnemonic index.
	 */
	public void setButtonMnemonicIndex(int index) {
		this.buttonMnemonicIndex = index;
		getOpenFileChooser().setDisplayedMnemonicIndex(index);
	}

	/**
	 * Returns the mnemonic index for the open the file chooser dialog button.
	 * 
	 * @param index
	 *            the mnemonic index.
	 */
	public int getButtonMnemonicIndex() {
		return buttonMnemonicIndex;
	}

	@Override
	public void setTexts(Texts texts) {
		super.setTexts(texts);
		updateTextsResources();
	}

	private void updateTextsResources() {
		updateButtonTextResource();
		updateButtonMnemonicResource();
	}

	private void updateButtonTextResource() {
		if (haveTextResource(buttonTextResource)) {
			buttonText = getTextResource(buttonTextResource, buttonText);
		}
		getOpenFileChooser().setText(buttonText);
		updateButtonMnemonicResource();
	}

	private void updateButtonMnemonicResource() {
		if (!haveTextResource(buttonMnemonicResource)) {
			return;
		}
		String string = getTextResource(buttonMnemonicResource, null);
		if (string == null) {
			return;
		}
		Mnemonic mnemonic = mnemonicFactory.create(string);
		setButtonMnemonic(mnemonic.getMnemonic());
		int index = mnemonic.getMnemonicIndex();
		if (index != -1) {
			setButtonMnemonicIndex(index);
		}
	}

	/**
	 * Returns the open file chooser button.
	 * 
	 * @return the {@link JButton}.
	 */
	public JButton getOpenFileChooser() {
		return panel.getOpenFileChooser();
	}

	@Override
	public void requestFocus() {
		panel.getFileField().requestFocusInWindow();
	}

}
